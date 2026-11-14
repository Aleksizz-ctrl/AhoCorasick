# Aho-Corasick Algorithm Implementation in Java

## Project Structure
- `Node.java`: Trie node structure containing children, failure links, and pattern outputs.
- `AhoCorasick.java`: Core implementation including Trie insertion, BFS failure link construction, and text searching.
- `Main.java`: Benchmark runner that tests the implementation across different pattern counts (10, 100, 1,000) and text lengths (1,000, 10,000, 100,000 characters) over 5 runs, reporting average execution times.

## How to Run
1. Compile all Java files:
   ```bash
   javac Node.java AhoCorasick.java Main.java