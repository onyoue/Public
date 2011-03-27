module ToFile
  def filename
    "object_#{self.object_id}.txt"
  end
  
  def to_f
    File.open(filename, 'w') {|f| f.write(to_s)}
  end
end

class Person
  include ToFile
  attr_accessor :name
  
  def initialize(name)
    @name = name
  end
  
  def to_s
    name
  end
end

# Person.new('matz').to_f

a = (0..15).to_a
puts a
count = 0
a.each do |i|
  count += 1
  if count % 4 == 0
    puts i
  else
    print "#{i} "
  end
end

a.each_slice(4) {|i| puts i}