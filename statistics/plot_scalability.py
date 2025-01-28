import matplotlib.pyplot as plt
import pandas as pd


def main():
    # Legge il CSV con le colonne:
    # nNodes, nArcs, maxIterations, tabuTenure, maxNoImprovement, bestCost, timeSec
    df = pd.read_csv("statistics/scalability_results.csv")

    # Raggruppa i dati per coppia (nNodes, nArcs)
    grouped = df.groupby(["nNodes", "nArcs"])

    # Convertiamo in lista per poter iterare e creare i subplots
    groups_list = list(grouped)  # elenco di ((nNodes, nArcs), subset)
    num_groups = len(groups_list)

    # Creiamo una figura con num_groups subplots affiancati
    fig, axs = plt.subplots(1, num_groups, figsize=(6 * num_groups, 4))

    # Se c'è solo 1 gruppo, axs non è una lista ma un singolo oggetto
    # per rendere il codice uniforme, trasformiamolo in una lista
    if num_groups == 1:
        axs = [axs]

    # Iteriamo sui gruppi
    for i, ((nodes, arcs), subset) in enumerate(groups_list):
        # Ordiniamo subset per maggiore coerenza
        subset = subset.sort_values(["maxIterations", "tabuTenure", "maxNoImprovement"])

        labels = []
        time_values = []

        for idx, row in subset.iterrows():
            config_label = f"it={int(row['maxIterations'])},T={int(row['tabuTenure'])},NI={int(row['maxNoImprovement'])}"
            labels.append(config_label)
            time_values.append(row["timeSec"])

        ax = axs[i]
        ax.bar(labels, time_values, color="skyblue")
        ax.set_title(f"Time at n={nodes}, a={arcs}")
        ax.set_xlabel("Config Parametres")
        ax.set_ylabel("Time (s)")
        ax.tick_params(axis="x", rotation=45)
        ax.grid(True)

    plt.tight_layout()

    # Salviamo su un unico file PNG
    plt.savefig("statistics/scalability_plot.png", dpi=150)
    plt.close()


if __name__ == "__main__":
    main()
