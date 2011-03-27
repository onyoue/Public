module ActsAsCsv
  
  def self.included(base)
    base.extend ClassMethods
  end
  
  module ClassMethods
    def acts_as_csv
      include InstanceMethods
    end
  end
  
  module InstanceMethods
    
    def read
      @csv_contents = []
      filename = self.class.to_s.downcase + '.txt'
      file = File.new(filename)
      @headers = file.gets.chomp.split(', ')
      
      file.each do |row|
        @csv_contents << row.chomp.split(', ')
      end
    end
    
    def each
      @csv_contents.each { |row| yield CsvRow.new(headers, row) }
    end    
    
    attr_accessor :headers, :csv_contents
    
    def initialize
      read
    end
  end
end

class RubyCsv
  include ActsAsCsv
  acts_as_csv
end

class CsvRow
  attr_accessor :headers, :csv_contents
  
  def initialize(headers, csv_contents)
    @headers = headers
    @csv_contents = csv_contents
  end
  
  def method_missing(name, *args)
    id = headers.index name.to_s
    csv_contents[id.to_i]
  end
end

m = RubyCsv.new
puts m.headers.inspect
puts m.csv_contents.inspect

m.each {|row| puts row.one}
m.each {|row| puts row.two}