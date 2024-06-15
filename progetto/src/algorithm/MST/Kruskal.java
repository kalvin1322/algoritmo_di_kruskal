package src.algorithm.MST;

import java.lang.reflect.Array;

import src.algorithm.sorting.Sorting;
import src.datastructure.graph.*;
import src.datastructure.unionfind.*;

/**
 * This class contains the implementation of the Kruskal's algorithm for the construction of a Minimum Spanning Tree (MST) of a weighted graph.
 * 
 * @param <D> type of the data stored in the nodes of the graph
 */
public class Kruskal<D> implements MST<D> {

	// The WeightedGraph on which the MST is computed
	private WeightedGraph<D> t;
	
	// The total weight of the MST
	private double weight;

	/** 
	 * Computes the Minimum Spanning Tree (MST) of the specified weighted graph.
	 * 	
	 * @param g the weighted graph
	 */
    public void compute(WeightedGraph<D> g) {
		QuickUnionRank<Vertex<D>> UF = new QuickUnionRank<>();
		
		for(int i=0;i<g.vertexNum();i++){
			UF.makeSet(this.t.vertexes().get(i));
		}
		this.t.edges().get(0);
		WeightedEdge<D>[] tmp = g.edges().toArray(new WeightedEdge [g.edgeNum()]);
		Sorting.mergesort(tmp);
		for(int i=0;i<g.edgeNum();i++){
			QURset Tu = (QURset) UF.find(new QUnode<Vertex<D>>(g.edges().get(i).source, null));
			QURset Tv = (QURset) UF.find(new QUnode<Vertex<D>>(g.edges().get(i).dest, null));
			if(!Tu.equals(Tv)){
				this.t.addEdge(g.edges().get(i));
				this.weight+=((WeightedEdge<D>)g.edges().get(i)).weight;
				UF.union(Tu, Tv);
			}
		}
		
		
		
    }
	
	/**
	 * Returns the Minimum Spanning Tree (MST) of the weighted graph.
	 * 
	 * @return the Minimum Spanning Tree (MST) of the weighted graph
	 */
	public WeightedGraph<D> spanningTree() {
		return this.t;
	}
	
	/**
	 * Returns the total weight of the Minimum Spanning Tree (MST) of the weighted graph.
	 * 
	 * @return the total weight of the Minimum Spanning Tree (MST) of the weighted graph
	 */
	public double totalWeight() {
		return this.weight;
	}
}