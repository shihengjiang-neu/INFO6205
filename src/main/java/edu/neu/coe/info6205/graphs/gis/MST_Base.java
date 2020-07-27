package edu.neu.coe.info6205.graphs.gis;

import java.util.Comparator;

import edu.neu.coe.info6205.SizedIterable;
import edu.neu.coe.info6205.bqs.Queue;
import edu.neu.coe.info6205.bqs.Queue_Elements;
import edu.neu.coe.info6205.graphs.undirected.Edge;
import edu.neu.coe.info6205.graphs.undirected.EdgeGraph;
import edu.neu.coe.info6205.pq.PQException;
import edu.neu.coe.info6205.pq.PriorityQueue;
import edu.neu.coe.info6205.union_find.UFException;

public abstract class MST_Base<V, X extends Comparable<X> & Sequenced> implements Iterable<Edge<V, X>> {
	public MST_Base(EdgeGraph<V, X> graph){
		this.queue = new Queue_Elements<>();
		this.pq = createPQ(graph.edges());
	}
	
	protected PriorityQueue<Edge<V, X>> createPQ(SizedIterable<Edge<V, X>> edges) {
        PriorityQueue<Edge<V, X>> result = new PriorityQueue<>(edges.size(), false, Comparator.comparing(Edge::getAttribute));
        for (Edge<V, X> e : edges) result.give(e);
        return result;
    }
	
	public void showEdgesInSequence(EdgeGraph<V, X> graph) {
        // TODO remove this debugging code
        PriorityQueue<Edge<V, X>> tempPQ = createPQ(graph.edges());
        while (!tempPQ.isEmpty()) {
            try {
                System.out.println(tempPQ.take());
            } catch (PQException e) {
                e.printStackTrace();
            }
        }
    }
	
	protected Queue<Edge<V, X>> queue;
    protected PriorityQueue<Edge<V, X>> pq;
    protected Iterable<Edge<V, X>> mst;
    protected abstract Iterable<Edge<V, X>> runAlgorithm() throws PQException, UFException;
    
    public static <V, X extends Comparable<X>> Edge<V, X> createEdge(V v1, V v2, X x) {
        return new Edge<>(v1, v2, x);
    }
}
