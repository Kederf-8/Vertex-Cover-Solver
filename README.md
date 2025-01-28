# Vertex Cover Solver

> Un progetto per risolvere il problema del **(Weighted) Vertex Cover** utilizzando la metaeuristica **Tabu Search**.

## Descrizione

Questo progetto implementa un algoritmo basato su **Tabu Search** per affrontare il problema del **Vertex Cover** su grafi non orientati. L'obiettivo è trovare un **insieme minimo** di vertici che copra tutti gli archi del grafo. Nel caso di grafi pesati, l'algoritmo mira a minimizzare la somma dei pesi dei vertici selezionati.

### Caratteristiche principali

- Configurabilità dei principali parametri dell'algoritmo (ad es. `tabuTenure`, `maxIterations`, `maxNoImprovement`).
- Gestione efficiente del vicinato e della Tabu List.
- Esportazione del grafo in formato DOT per la visualizzazione.
- Salvataggio delle migliori soluzioni trovate in file di output.

## Struttura del Progetto

Il progetto è organizzato nei seguenti file e directory:

```txt
VertexCoverSolver/
├── README.md                  # Documentazione del progetto
├── src/                       # Codice sorgente del progetto
│   ├── VertexCoverMain.java   # Entry point del progetto
│   ├── TabuSearch.java        # Implementazione della Tabu Search
│   ├── Graph.java             # Rappresentazione del grafo
│   ├── GraphReader.java       # Lettura del grafo da file
│   ├── Solution.java          # Rappresentazione della soluzione
│   ├── SolutionWriter.java    # Salvataggio della soluzione
│   └── Edge.java              # Classe per rappresentare gli archi
├── wvcp-istances/             # Istanze di test
│   ├── vc_20_60_01.txt        # Esempio di grafo piccolo
│   ├── vc_100_500_01.txt      # Esempio di grafo medio
│   └── vc_800_10000.txt       # Esempio di grafo grande
├── solutions/                 # Cartella per le soluzioni generate
├── report/                    # Relazione finale (LaTeX)
│   ├── main.tex               # File LaTeX principale
│   ├── Capitoli/              # Capitoli del report
│   └── immagini/              # Figure e grafici del report
└── scalability_plot.png       # Grafico della scalabilità (esempio)
```

## Installazione e Utilizzo

### Prerequisiti

- **Java Development Kit (JDK)** versione 17 o superiore.
- Compilatore LaTeX (opzionale, per generare la relazione).
- (Facoltativo) **Graphviz** per visualizzare i file DOT generati.

### Configurazione

1. Clona il repository:

   ```bash
   git clone https://github.com/username/VertexCoverSolver.git
   cd VertexCoverSolver
   ```

2. Compila i file Java:

   ```bash
   javac src/*.java
   ```

3. Assicurati che la cartella `solutions/` esista. In caso contrario, creala manualmente:

   ```bash
   mkdir solutions
   ```

### Esecuzione

Per eseguire l'algoritmo su un'istanza di grafo:

```bash
java src/VertexCoverMain wvcp-istances/vc_800_10000.txt
```

Dove:

- `wvcp-istances/vc_800_10000.txt` è il file che contiene la rappresentazione del grafo in un formato accettato dal programma.

### Formato dei File di Input

Il formato dei file di input è il seguente:

```txt
<N>
<Pesi dei Nodi>
<Matrice di Adiacenza>
```

Esempio (grafo con 5 nodi, pesi casuali, e una matrice di adiacenza):

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

Il programma salva:

1. La migliore soluzione trovata in un file di testo nella cartella `solutions/`.
2. Il grafo in formato DOT (esportato nella directory di lavoro) per la visualizzazione.

Esempio di file DOT generato:

```dot
graph G {
  1 [label="Node1 (10)"];
  2 [label="Node2 (15)"];
  1 -- 2;
  2 -- 3;
  ...
}
```

### Visualizzazione del Grafo

Se hai installato **Graphviz**, puoi generare un’immagine PNG dal file DOT:

```bash
dot -K sfdp -Tpng grafo.dot -o grafo.png
```

## Test e Benchmark

Sono stati eseguiti test su istanze di diverse dimensioni, incluse quelle nella cartella `wvcp-istances/`:

- `vc_20_60_01.txt` (20 nodi, 60 archi)
- `vc_100_500_01.txt` (100 nodi, 500 archi)
- `vc_800_10000.txt` (800 nodi, 10000 archi)

Per i dettagli sui risultati, consultare la relazione PDF nella cartella `report/`.

## Sviluppi Futuri

Possibili miglioramenti includono:

- Integrazione di una **long-term memory** nella Tabu Search.
- Implementazione di una versione parallela dell'algoritmo per sfruttare architetture multicore.
- Aggiunta di nuove istanze di test basate su benchmark standard per problemi di Vertex Cover.

## Autore

**Giuseppe Napoli**  
Matricola: 1000012802  
Corso di Laurea: Informatica LM-18  
Insegnamento: Intelligenza Artificiale e Laboratorio  
Anno Accademico: 2024/25

## Contatti

Per segnalazioni o suggerimenti:

- **Email:** <napolig05@gmail.com>
- **GitHub Repository:** [Vertex Cover Solver](https://github.com/Kederf-8/VertexCoverSolver)
