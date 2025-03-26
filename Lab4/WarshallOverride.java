//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.graph4j.shortestpath;

import java.util.Arrays;
import org.graph4j.Graph;
import org.graph4j.GraphAlgorithm;
import org.graph4j.NeighborIterator;
import org.graph4j.util.Cycle;
import org.graph4j.util.Path;
import org.graph4j.util.Validator;

public class WarshallOverride extends GraphAlgorithm implements AllPairsShortestPath {
    private double[][] cost;
    private int[][] before;

    public  WarshallOverride(Graph graph) {
        super(graph);
    }

    public Path findPath(int source, int target) {
        Validator.containsVertex(this.graph, source);
        Validator.containsVertex(this.graph, target);
        if (this.before == null) {
            this.computeAll();
        }

        int si = this.graph.indexOf(source);
        int ti = this.graph.indexOf(target);
        return this.cost[si][ti] == Double.POSITIVE_INFINITY ? new Path(this.graph, new int[0]) : this.createPathBetween(si, ti);
    }

    public double getPathWeight(int source, int target) {

        Validator.containsVertex(this.graph, source);
        Validator.containsVertex(this.graph, target);

        this.computeWeights();
        return this.cost[this.graph.indexOf(source)][this.graph.indexOf(target)];
    }

    public void printMat()
    {

        for (int i = 0; i < this.cost.length; i++) {
            for (int j = 0; j < this.cost[i].length; j++) {
                System.out.print(this.cost[i][j] + " ");
            }
            System.out.println('\n');
        }

    }

   private void initBefore() {
        int n = this.graph.numVertices();
        this.before = new int[n][n];

        for(int i = 0; i < n; ++i) {
            Arrays.fill(this.before[i], -1);
        }

        for(int v : this.graph.vertices()) {
            int vi = this.graph.indexOf(v);

            int ui;
            for(NeighborIterator it = this.graph.neighborIterator(v); it.hasNext(); this.before[vi][ui] = vi) {
                ui = this.graph.indexOf(it.next());
            }
        }

        if (this.graph.isAllowingSelfLoops()) {
            for(int i = 0; i < n; ++i) {
                this.before[i][i] = -1;
            }
        }

    }

    public void  computeMatProb()
    {
        this.cost = this.graph.weightMatrix();
        int n = this.graph.numVertices();
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                if (i == j) {
                    this.cost[i][j] = 0.0;
                } else if (this.cost[i][j] !=Double.POSITIVE_INFINITY) {
                    this.cost[i][j] = Math.log(this.cost[i][j]);
                } else {
                    this.cost[i][j] = Double.NEGATIVE_INFINITY;
                }
            }


        }


    }
    private void computeAll() {
        this.cost = this.graph.weightMatrix();
        this.initBefore();
        int n = this.graph.numVertices();
        computeMatProb();

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (this.cost[i][k] != Double.NEGATIVE_INFINITY) {
                    for (int j = 0; j < n; j++) {
                        if (this.cost[k][j] != Double.NEGATIVE_INFINITY) {
                            double newProb = this.cost[i][k] + this.cost[k][j];
                            if (this.cost[i][j] < newProb) {
                                this.cost[i][j] = newProb;
                                this.before[i][j] = this.before[k][j];
                            }
                        }
                    }
                }
            }

        }
    }


    private void computeWeights() {

        this.cost = this.graph.weightMatrix();
        int n = this.graph.numVertices();
        computeMatProb();

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (i!=k&& this.cost[i][k] != Double.NEGATIVE_INFINITY) {
                    for (int j = 0; j < n; j++) {
                        if (this.cost[k][j] != Double.NEGATIVE_INFINITY) {
                            double newProb = this.cost[i][k] + this.cost[k][j]; // log(A * B) = log(A) + log(B)
                            if (this.cost[i][j] < newProb) {
                                this.cost[i][j] = newProb; // Maximizăm
                            }
                        }
                    }
                }

            }
        }

        for (int i = 0; i < n; i++) {
            this.cost[i][i] = 1.0;
            for (int j = 0; j < n; j++) {
                if (this.cost[i][j] > Double.NEGATIVE_INFINITY) {
                    this.cost[i][j] = Math.exp(this.cost[i][j]); // exp(log(P)) = P
                } else {
                    this.cost[i][j] = 0.0; // Probabilitate zero dacă nu există drum
                }
            }
            this.cost[i][i] = 1.0;

        }
    }


    private Path createPathBetween(int vi, int ui) {
        Path path;
        for(path = new Path(this.graph); ui != vi; ui = this.before[vi][ui]) {
            path.add(this.graph.vertexAt(ui));
        }

        path.add(this.graph.vertexAt(vi));
        path.reverse();
        return path;
    }

    private Cycle createCycleBetween(int vi, int ui) {
        Cycle cycle;
        for(cycle = new Cycle(this.graph); !cycle.contains(this.graph.vertexAt(ui)); ui = this.before[vi][ui]) {
            cycle.add(this.graph.vertexAt(ui));
        }

        return cycle;
    }
}
