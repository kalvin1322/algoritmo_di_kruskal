package src.datastructure.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * Implementation of graphs using adjacent lists
 * @param <D> type of the data object in the graph vertexes
 */	
@SuppressWarnings("unused")
public class GraphAL<D> implements Graph<D> {

	/** number of vertexes */
	protected int n;
	
	/** number of edges */
	protected int m;
	
	/** list of vertexes */
	protected ArrayList<VertexAL<D>> vertexes;

	/**
   	 * Constructs a graph with implementation using adjacent lists
	 */		
	public GraphAL(){
		n = 0;
		m = 0;
		vertexes = new ArrayList<VertexAL<D>>();
	}

	public int vertexNum() {
		return this.n;
	}

	public int edgeNum() { 
		return this.m;
	}

	public ArrayList<Vertex<D>> vertexes() { 
		ArrayList<Vertex<D>> v =  new ArrayList<>();
		for(int i = 0; i<this.vertexNum();i++){
			v.add(this.vertexes.get(i));
		}
		return v;
	}
	
	public ArrayList<Edge<D>> edges() {
		ArrayList<Edge<D>> l = new ArrayList<>();
		for(int i = 0; i<this.vertexNum();i++){
			l.addAll(this.vertexes.get(i).adjac);
		}
		return l;
		}

	public int outDegree(Vertex<D> v) {	
		VertexAL<D> tmp = (VertexAL<D>) (v);
		return tmp.adjac.size();
	}	
	
	public ArrayList<Edge<D>> outEdges(Vertex<D> v) { 
		ArrayList<Edge<D>> List = new ArrayList<>();
        Iterator<Edge<D>> iter = ((VertexAL<D>) v).adjac.iterator();
        while(iter.hasNext()){
            List.add(iter.next());
        }
        return List;
	}
	
	public Edge<D> areAdjacent(Vertex<D> x, Vertex<D> y) {
		/*int index = this.vertexes.indexOf(x);
		if(index!=-1){
			if(this.vertexes.get(index).adjac.contains(y)){
				return this.vertexes.get(index).adjac.get(this.vertexes.get(index).adjac.indexOf(y));
			}
			else{
				return null;
			}
		}
		return null;*/
		ArrayList<Edge<D>> edge = this.outEdges(x);
		for(int i=0; i<edge.size();i++){
			if(edge.get(i).dest == y){
				return edge.get(i);
			}
		}
		return null;
	 }

	public Vertex<D> addVertex(D data) {
		VertexAL<D> v = new VertexAL<D>(data, n);
		this.vertexes.add(v);
		this.n++;
		return v;
	 }

	public void addEdge(Edge<D> e) {
		/*	
		if(this.vertexes.contains(e.source)){
			
			int index = this.vertexes.indexOf(e.source);
			//System.out.println(this.vertexes.get(index).adjac.size());
			this.vertexes.get(index).adjac.add(e);
			//System.out.println(this.vertexes.get(index).adjac.size());
			this.m++;
		}
		*/
		LinkedList<Edge<D>> l = vertexes.get(((VertexAL<D>)e.source).index).adjac;
		l.add(e);
		this.m++;
		return;
	}
	
	public void removeVertex(Vertex<D> v) {
		VertexAL<D> vAL = (VertexAL<D>)v;
		m = m - vAL.adjac.size();
		n = n - 1;
		if (vAL.index == n) {
			vertexes.remove(n);
		} else {
			VertexAL<D> vert = vertexes.remove(n);
			vert.index = vAL.index;
			vertexes.set(vert.index,vert);
		}
		Edge<D> e;
		for (int i=0; i<vertexes.size(); i++) {
			Iterator<Edge<D>> it = (vertexes.get(i)).adjac.iterator();
			while (it.hasNext()) { 
				e = it.next();
				if (e.dest == v) {
					it.remove();
					m = m - 1;
				}
			}					
		}
	}

	public void removeEdge(Edge<D> e) { }

	/**
	 *  Returns the index of a vertex
     *  @param v vertex
	 *  @return the index of the vertex v
	 */	
	protected int index(VertexAL<D> v) {
		return v.index;
	}

	/**
	 *  Returns the vertex with a given index
     *  @param i the index
	 *  @return the vertex v with index i
	 */		
 	protected VertexAL<D> vertex(int i) {
		if (i < 0 || i >= n) return null;
		return vertexes.get(i);
 	}

}
