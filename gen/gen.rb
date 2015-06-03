#! /usr/bin/env ruby

if ARGV.length != 1 then
  puts "Usage: gen.rb <number of items>"
  exit 1
end

num = ARGV[0].to_i
num.times do |i|
  weight = rand(19) + 1
  value = rand(100)
  puts weight.to_s + " " + value.to_s
end

puts

