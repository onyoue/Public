puts "Reading #{ARGV[0]}"
File.open(ARGV[0]).each do |line|
  puts line if line.index(ARGV[1])
end
