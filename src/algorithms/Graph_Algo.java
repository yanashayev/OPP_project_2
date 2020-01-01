package algorithms;

import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms, Serializable {
	private graph graph = new DGraph();

	private Map<node_data, Double> distance = new HashMap<>();

	@Override
	public void init(graph g) {
		this.graph = g;
	}

	@Override
	public void init(String file_name) {
		try {
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);
			graph g = (graph) in.readObject();
			init(g);
			in.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(String file_name) {
		try {
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(graph);
			out.close();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


	}


	public boolean isConnected() {
		for (node_data node: graph.getV()) {
			if (!isCon(node.getKey()))
				return false;
			this.zeroTags();
		}
		return true;
	}

	private boolean isCon (int node_key){
		changeTags(node_key);
		for (node_data node: graph.getV()) {
			if (node.getTag()!= 1)
				return false;
		}
		return true;
	}

	private void changeTags (int node_key){
		for (node_data node: graph.getV()) {
			int key = node.getKey();
			for (edge_data edge: graph.getE(key)) {
				int d = edge.getDest();
				if (graph.getNode(d).getTag() != 1) {
					graph.getNode(d).setTag(1);
					changeTags(d);
				}
			}
		}
	}

	private void zeroTags() {
		Collection<node_data> n = graph.getV();
		Iterator<node_data> it = n.iterator();
		while (it.hasNext()) {
			it.next().setTag(0);
		}
	}


	@Override
	public double shortestPathDist(int src, int dest) {
			List<node_data> path= shortestPath(src,dest);
			double answer=0;
		for (node_data nodesInPath:	path) {
			answer+=nodesInPath.getWeight();
		}
		return answer;
	}
	private void setTheSmallWeight(int dest, double w,int whereW){
		if (graph.getNode(dest).getWeight()>w){
			graph.getNode(dest).setWeight(w);
			graph.getNode(dest).setTag(whereW);
		}

	}
	private double sendTheWeight(int current, edge_data e){
		return graph.getNode(current).getWeight()+e.getWeight();
	}
	private void setTagForSrc(int current){
		for (edge_data e:graph.getE(current)) {
			graph.getNode(e.getDest()).setTag(current);

		}

	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		List<node_data> answer= new ArrayList<>();
		PriorityQueue<node_data> q = new PriorityQueue<node_data>(graph.getV().size(), new v_Comp());
		q.addAll(graph.getV());
		node_data current;
		graph.getNode(src).setWeight(0);
		while (!q.isEmpty()) {
			current=q.remove();
			graph.getNode(current.getKey()).setInfo("true");
			if (graph.getE(current.getKey())!=null){
				setTagForSrc(current.getKey());
				for (edge_data e: graph.getE(current.getKey())) {
					if (graph.getNode(e.getDest()).getInfo() == "false") {
						setTheSmallWeight(e.getDest(), sendTheWeight(current.getKey(), e), current.getKey());
					}
				}

			}

		}
		Stack<node_data> stack = new Stack<node_data>();
		stack.push(graph.getNode(dest));
		int temp=graph.getNode(dest).getTag();//the tag of  node before dest
		stack.push(graph.getNode(temp));
		while (temp!=src){// if we didnt came to src
			int temp2=graph.getNode(temp).getTag();
			temp=temp2;
			stack.push(graph.getNode(temp));
		}
		while (!stack.empty()){
			answer.add(stack.pop());
		}
		return answer;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {

			List<node_data> TSP = new LinkedList<node_data>();
			Iterator<Integer> i = targets.iterator();
			int src=i.next();
			TSP.add(0,graph.getNode(src));
			while(i.hasNext())
			{
				int dest=i.next();
				List<node_data> temp = shortestPath(src,dest);
				if (temp==null) return null;
				temp.remove(0);//avoid duplicates
				TSP.addAll(temp);
				src=dest;
			}
			return TSP;



	}

	@Override
	public graph copy() {
		graph copyGraph = new DGraph();
		if (this.graph != null) {
			Collection<node_data> colleOfNodes = graph.getV();
			for (node_data node : colleOfNodes) {
				copyGraph.addNode(node);
				Collection<edge_data> edges = graph.getE(node.getKey());
				if (edges != null) {
					for (edge_data edge : edges) {
						copyGraph.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
					}
				}
			}

		}
		return copyGraph;

	}


	private class v_Comp implements Comparator<node_data> {
		public v_Comp() {

		}

		@Override
		public int compare(node_data v2, node_data v1) {
			if (v1.getWeight() - v2.getWeight() > 0)
				return -1;
			else return 1;
		}
	}
}