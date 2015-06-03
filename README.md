# potw_06032015

Robin Hood steals from the rich to give to the poor, but this career has been hurting his back. (Loot is heavy!) Robin's been wondering if there's a more efficient way to thieve. His knapsack/ back can only hold so much, and each item he steals has a weight and a value... can he optimize what he takes to get the most bang for his back?

Inside this week's repo on the github https://github.com/EtaPi/potw_06032015/tree/master/gen are a set of files containing lists of items. Each line is composed of an items weight, a space, and its value. Your task is to write a program that will take as parameters the weight limit and the file, and output the list of items that will maximize Robin's loot while staying under the weight limit.

For example, given the weight limit **30** and these items in the file example.txt:
```
1 24
8 5
8 52
17 11
18 86
```

your program should output
```
1 24
8 52
18 86
```
