package algorithms;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

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
	private graph graph=new DGraph();

	@Override
	public void init(graph g) {
		this.graph = g;
	}

	@Override
	public void init(String file_name) {
		try {
		FileInputStream file= new FileInputStream(file_name);
		ObjectInputStream in= new ObjectInputStream(file);
		graph g=(graph)in.readObject();
		init(g);
		in.close();
		file.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(String file_name)  {
		try {
			FileOutputStream file= new FileOutputStream(file_name);
			ObjectOutputStream out= new ObjectOutputStream(file);
			out.writeObject(graph);
			out.close();
			file.close();

		}catch (IOException e){
			e.printStackTrace();
		}

		
	}


	@Override
	public boolean isConnected() {
		if(graph.getV()==null){
			return false;
		}
		for (node_data vertical :graph.getV()) {// for each vertical we check if it can go throw all other vertical
			vertical.setTag(vertical.getKey());
			for (edge_data verticalsedges:graph.getE(vertical.getKey())) {// the edges of the vertical
				if (verticalsedges.getTag() != vertical.getKey()) {
					graph.getNode(verticalsedges.getDest()).setTag(vertical.getKey());

				}
			}

			}


		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
	graph copyGraph= new DGraph();
	if(this.graph!=null){
		Collection <node_data> colleOfNodes=graph.getV();
		for (node_data node:colleOfNodes) {
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
}