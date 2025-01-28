# Vertex Cover Solver

> A project for solving the **Weighted Vertex Cover** problem using the **Tabu Search** metaheuristic.

## Description

This project implements a **Tabu Search**-based algorithm to address the Vertex Cover problem on undirected graphs. The goal is to find a **minimum set** of vertices that covers all the graph edges. In the case of weighted graphs, the algorithm aims to minimize the total weight of the selected vertices.

### Key Features

- Configurable algorithm parameters (e.g., `tabuTenure`, `maxIterations`, `maxNoImprovement`).
- Efficient neighborhood management and Tabu List handling.
- Graph export in DOT format for visualization.
- Best solutions saved in output files.

## Project Structure

The project is organized into the following files and directories:

```txt
VertexCoverSolver/
├── README.md                  # Project documentation
├── src/                       # Project source code
│   ├── VertexCoverMain.java   # Project entry point
│   ├── TabuSearch.java        # Tabu Search implementation
│   ├── Graph.java             # Graph representation
│   ├── GraphReader.java       # Graph input reader
│   ├── Solution.java          # Solution representation
│   ├── SolutionWriter.java    # Solution output writer
│   └── Edge.java              # Class for representing edges
├── wvcp-istances/             # Test instances
│   ├── vc_20_60_01.txt        # Small graph example
│   ├── vc_100_500_01.txt      # Medium graph example
│   └── vc_800_10000.txt       # Large graph example
├── solutions/                 # Folder for generated solutions
├── report/                    # Final report (LaTeX)
│   ├── main.tex               # Main LaTeX file
│   ├── Chapters/              # Report chapters
│   └── images/                # Figures and plots for the report
└── scalability_plot.png       # Scalability plot (example)
```

## Installation and Usage

### Prerequisites

- **Java Development Kit (JDK)** version 17 or higher.
- LaTeX compiler (optional, for generating the report).
- (Optional) **Graphviz** for visualizing generated DOT files.

### Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/Kederf-8/VertexCoverSolver.git
   cd VertexCoverSolver
   ```

2. Compile the Java files:

   ```bash
   javac src/*.java
   ```

3. Ensure the `solutions/` folder exists. If not, create it manually:

   ```bash
   mkdir solutions
   ```

### Execution

Run the algorithm on a graph instance:

```bash
java src/VertexCoverMain wvcp-istances/vc_800_10000.txt
```

Where:

- `wvcp-istances/vc_800_10000.txt` is the file containing the graph representation in a supported format.

### Input File Format

The input file format is as follows:

```txt
<N>
<Node Weights>
<Adjacency Matrix>
```

Example (a graph with 5 nodes, random weights, and an adjacency matrix):

```txt
5
  10 15 20 25 30
0 1 0 1 0
1 0 1 0 1
0 1 0 0 0
1 0 0 0 1
0 1 0 1 0
```

### Output

The program outputs:

1. The best solution found in a text file under the `solutions/` folder.
2. The graph in DOT format (exported in the working directory) for visualization.

Example of a generated DOT file:

```dot
graph G {
  1 [label="Node1 (10)"];
  2 [label="Node2 (15)"];
  1 -- 2;
  2 -- 3;
  ...
}
```

### Graph Visualization

If **Graphviz** is installed, you can generate a PNG image from the DOT file:

```bash
dot -K sfdp -Tpng grafo.dot -o grafo.png
```

## Test and Benchmarks

Tests were conducted on instances of various sizes, including those in the `wvcp-istances/` folder:

- `vc_20_60_01.txt` (20 nodes, 60 edges)
- `vc_100_500_01.txt` (100 nodes, 500 edges)
- `vc_800_10000.txt` (800 nodes, 10000 edges)

For detailed results, refer to the PDF report in the `report/` folder.

## Future Developments

Potential improvements include:

- Integration of a **long-term memory** in Tabu Search.
- Implementation of a parallel algorithm to leverage multicore architectures.
- Addition of new test instances based on standard benchmarks for Vertex Cover problems.

## Author

**Giuseppe Napoli**  
Student ID: 1000012802  
Degree Program: Computer Science LM-18  
Course: Artificial Intelligence and Laboratory  
Academic Year: 2024/25

## Contact

For feedback or suggestions:

- **Email:** <napolig05@gmail.com>
- **GitHub Repository:** [Vertex Cover Solver](https://github.com/Kederf-8/VertexCoverSolver)
