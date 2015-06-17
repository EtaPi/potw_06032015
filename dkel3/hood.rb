#! /usr/bin/env ruby

def usage
  puts "hood.rb <weight> <item list>"
  exit 1
end

# check correct number of argumnts
if ARGV.length != 2 then
  usage
end

# get weight limit from arguments
limit = ARGV[0].to_i

# get input list of items from arguments
file_path = ARGV[1]

# read in file and get weights/ values
file = File.open(file_path, "r+")

weights = []
values = []

file.each_line do |line|
  matches = line.match /^(\d+)\s(\d+)/
  if matches then
    weights.push matches[1].to_i
    values.push matches[2].to_i
  end
end

# initiaize memoization array to store intermediate results
# this array stores the solution for each subset of items and given weight
#
# eg. memo[2][15] is the soluiton for the first 2 items and weight 15
# the solution we're looking for is memo[n][limit-1], where
#  n is the number of items in the input
#  limit is the given weight limit
#
# the 'solution' is an array of the max value and the items that make up
# value
#
# eg. memo[5][30] = [156, [1,3,4]]
memo = Array.new( weights.length + 1 ) { Array.new( limit ) { Array.new }}
limit.times do |i|
  memo[0][i] = [0, []]
end

# loop through items
(1..weights.length).each do |i|
  # loop through weights up to limit
  limit.times do |j|
    item_weight = weights[i-1]
    item_value = values[i-1]

    if (item_weight <= j) then
      # if item fits into current weight (j)

      old = memo[i-1][j][0]
      current = memo[i-1][j - item_weight][0] + item_value

      if (old >= current) then
        # if including the new (current) item gives a lower value, just use the
        # old solution

        memo[i][j] = Array.new(memo[i-1][j])
      else
        # if including the new item gives a higher value, grab the old set of
        # items and add the current item
        memo[i][j][0] = current # value
        memo[i][j][1] = Array.new(memo[i-1][j - item_weight][1]) # old set
        memo[i][j][1].push i # add new item
      end
    else
      # if item doesnt fit, grab the solution without this item
      memo[i][j] = Array.new(memo[i-1][j])
    end
  end
end

# print solution
items = memo[weights.length][limit-1][1]
items.each do |item|
   puts weights[item-1].to_s + " " + values[item-1].to_s
end
