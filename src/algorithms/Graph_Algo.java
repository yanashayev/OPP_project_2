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

	private void changeTagForConnectedNodes(Collection<edge_data> allEdgeOfThisNode, int tag) {
		for (edge_data check : allEdgeOfThisNode) {// change the tag of edge we been throw
			if (check.getTag() != tag) {
				check.setTag(tag);
			}
			if (graph.getNode(check.getDest()).getTag() != tag) {//change the tag of dest node we been throw
				graph.getNode(check.getDest()).setTag(tag);
			}
			if (graph.getE(check.getDest()) != null) { //if there is a edges for the dest , we check if we been there and if not we send it
				for (edge_data sendIfNotTag : graph.getE(check.getDest())) {
					if (sendIfNotTag.getTag() != tag) {
						changeTagForConnectedNodes(graph.getE(check.getDest()), tag);
					}
				}
			}
		}

	}

	private boolean checkIfAllTagsIsTheSame(Collection<node_data> nodes, int tag) {
		for (node_data node : nodes) { // check if all the tags of the nodes are the same as the tag is given
			if (node.getTag() != tag) {
				return false;
			} else {
				node.setTag(-1);//, and change it back to -1
			}
		}
		return true;
	}

	private void changeBackAllEdgeTags() {
		for (node_data node : graph.getV()) {
			for (edge_data edge : graph.getE(node.getKey())) {
				edge.setTag(-1);

			}

		}
	}


	@Override
	public boolean isConnected() {
		boolean connected = true;
		if (graph.getV() == null) {
			return false;
		}
		for (node_data allNodes : graph.getV()) {// go throw all nodes
			allNodes.setTag(allNodes.getKey());//fix
			if (graph.getE(allNodes.getKey()) == null) {
				return false;
			} else {
				changeTagForConnectedNodes(graph.getE(allNodes.getKey()), allNodes.getKey());// send to recursive function to
				// change all edges and nodes we been throw
				connected = checkIfAllTagsIsTheSame(graph.getV(), allNodes.getKey());// check if all nodes are tag similarly
				if (connected == false) {
					return connected;
				}
			}


		}
		changeBackAllEdgeTags(); //change back all tags to -1

		return connected;
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
			if (graph.getNode(current.getKey())!=null){
				for (edge_data e: graph.getE(current.getKey())) {
					if (graph.getNode(e.getDest()).getInfo() == "false") {
						setTheSmallWeight(e.getDest(), sendTheWeight(current.getKey(), e), current.getKey());
					}
				}

			}

		}
		Stack<node_data> stack = new Stack<node_data>();
		stack.push(graph.getNode(dest));
		int temp=graph.getNode(dest).getTag();//the node before dest
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
		if (!isConnected()) {
			return null;
		}
		else{

		}
		return null;
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