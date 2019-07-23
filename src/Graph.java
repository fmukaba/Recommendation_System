
/*					
 * 					Original Author : Fatma Serce
 * 					Modified by : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */

import java.util.HashSet;

public class Graph {
	private final int v;
	private int e;
	private HashSet<Integer>[] adj;

	public Graph(int v) {
		this.v = v;
		this.e = 0;
		adj = new HashSet[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new HashSet<Integer>();
		}
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		e++;
	}

	public void removeEdge(int v, int w) {

	}

	public boolean hasEdge(int v, int w) {

		return false;
	}

	public int v() {
		return v;
	}

	public int e() {
		return e;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	public int degree(int v) {
		return adj[v].size();
	}

	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < v; i++) {
			strBuilder.append(v + ":");
			for (int w : adj[i]) {
				strBuilder.append("\n");
			}
		}
		return strBuilder.toString();
	}
}
