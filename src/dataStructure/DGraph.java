package dataStructure;

import java.io.Serializable;
import java.util.*;

public class DGraph implements graph , Serializable {
	private HashMap<Integer, node_data> nodeMap = new LinkedHashMap<>() ;
	private HashMap<Integer, HashMap<Integer,edge_data>> edgeMap = new LinkedHashMap<>() ;
	private int MC=0;
	private int numOfEdge=0;

	public DGraph(){

	}
	public DGraph(graph g){
		if(g.getV()!=null) { // collection of node_data
			for (node_data me : g.getV()) {
				nodeMap.put(me.getKey(), me);
				if(g.getE(me.getKey())!=null){// if the is a coolection of edge_data for this key(src)
					for (edge_data me2:g.getE(me.getKey()) ) {
						HashMap<Integer, edge_data> me3= new HashMap<Integer,edge_data>();
						me3.put(me2.getDest(),me2);
						edgeMap.put(me.getKey(),me3);
					}
				}
			}
		}


	}

	@Override
	public node_data getNode(int key) {

		return nodeMap.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {

		return edgeMap.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		if(!nodeMap.containsKey(n.getKey())) {
			nodeMap.put(n.getKey(), n);
			MC++;
		}
	}

	@Override
	public void connect(int src, int dest, double w) {
		EdgeData m = new EdgeData(src, dest, w);
		if(nodeMap.containsKey(src)&&nodeMap.containsKey(dest)){
			if(!edgeMap.containsKey(src)) {
				HashMap<Integer, edge_data> newEdge = new HashMap<Integer, edge_data>();
				newEdge.put(src, m);
				edgeMap.put(src,newEdge);
			}else {
				edgeMap.get(src).put(dest,m);
		}
	}
numOfEdge++;
		MC++;
	}

	@Override
	public Collection<node_data> getV() {
		return nodeMap.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return edgeMap.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) {
		edgeMap.remove(key);
		for(HashMap<Integer,edge_data> tempE:edgeMap.values()){
			if (tempE.containsValue(key)){
				numOfEdge--;
				tempE.remove(key);
			}
		}

		nodeMap.remove(key);
		MC++;

		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		MC++;
		numOfEdge--;
		return edgeMap.get(src).remove(dest);
	}

	@Override
	public int nodeSize() {

		return nodeMap.size();
	}

	@Override
	public int edgeSize() {
		int num=0;

		for (Integer src: edgeMap.keySet()) {
			num+=edgeMap.get(src).size();
		}
		if (num!=numOfEdge) {

			return numOfEdge;
		}
		else{
			return num;
		}
	}

	@Override
	public int getMC() {

		return MC;
	}

}
