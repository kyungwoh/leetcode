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
I used Prim's algorithm to find the least expensive way from the city 0 to every city, but modified that all visited cities produce new messengers. To reduce time and space, I check duplicate cases not to make the same messenger.

## Performance
```
V = # of vertices (n)
E = # of edges (n^2)
```
- Time complexity: O(ElogV) using binary heap (one of implementations of Priority Queue)
- Space complexity: O(V^2) using adjacency matrix

## Test
To ensure that this program works correctly, I made 5 test cases `imperialMessengers_input*.txt` and a Windows batch file `test.bat`. This enables to automate test processes for further modifications and maintenance.
