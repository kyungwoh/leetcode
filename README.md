Imperial Messengers
===
## Description
- `imperialMessengers.c`: Main C file
- `imperialMessengers.exe`: Executable file
- `test.bat`: The file for testing Main C file using files in the `imperialMessengers_input*.txt`
- `imperialMessengers_input*.txt`: Test cases for this program

## Usage
```
imperialMessengers.exe < imperialMessengers_input1.txt
```
This program takes Standard input and gives Standard output as text. You can redirect input from the prepared text file and output to the designated text file too.

## Approach
- I used Prim's algorithm to find the least expensive way from the city 0 to every city, but modified that all visited cities produce new messengers. To reduce time and space, I check duplicate cases not to make the same messenger.

## Performance

The implementation takes O(log(n)) to add or delete entries. Also, it reduces the number of entries to compare with when `accept_packet` function is called. However, it still takes O(n) to query whether it accepts or not. Due to the 90 minutes time constraint, I was not able to reduce the query time to O(log(n)). Please see future optimization section for the detail of possible design.

## Observation
#### Test
Made sure a function works correctly for all the edge cases. First, since IP ranges from 0.0.0.0 to 255.255.255.255 and Port ranges from 0 to 65535, it tests the corner of each range works fine (`fw1.csv` and `test1.csv`). Second, it takes cases when duplicate IP ranges or Port ranges have different rules work fine (`fw2.csv` and `test2.csv`).

#### Design
Table consists of array of tuples consists of 4 elements; `(<IP range start>, <Port range start>, <IP range end>, <Port range end>)`. By using this format, it can binary search the index where a new entry have to be inserted or an existing entry have to be deleted. To reduce the range to query, it finds the point where it have to start query from by using Binary Search (`bisect`).

#### Future Optimization
Even though adding or deleting the entry takes always O(log(n)), judging whether to accept a packet or not takes O(n) in the worst case since it is checking each entry one by one from the index obtained by binary search. The possible optimizations are shown below.
1. Have Trie to query for the IP range it matches. Each Trie node can have a subnet/subnet mask and an array of its accepting port range. It would take O(log(n)) to query whether IP:Port pair is accepted or not. However, to make Trie to work, IP range have to be specified by subnet instead of specific start and end of the range.
2. Have array of IP ranges and dictionary of key as IP range and array of Port ranges it accept as its value. However, as the rules becomes complicated, the number of entries in the IP range array would be big and it consumes large memory space. Deleting the existing rule would be impossible.
