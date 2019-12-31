package Tests;
import dataStructure.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import utils.Point3D;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class testDGraph {
   static DGraph graph = new DGraph();

        @Test
    void getNode() {
            NodeData n= new NodeData(1);
       graph.addNode(n);
       assertEquals(n,graph.getNode(n.getKey()));
        }
    @Test
    void getEdge() {// wrong
NodeData y= new NodeData(2);
graph.addNode(y);
        NodeData g= new NodeData(3);
        graph.addNode(g);
EdgeData e= new EdgeData(2,3,10);
graph.connect(y.getKey(),g.getKey(),10);
assertEquals(e.toString(), graph.getEdge(y.getKey(),g.getKey()).toString());



    }
    @Test
    void addnodes(){
//             Point3D p= new Point3D(5,6,0);
//             NodeData a= new NodeData(7,p);
//             Graph.addNode(a);
//             assertEquals(a, Graph.getNode(a.getKey()));

    }
    @Test
    void connect(){// wrong
//        Point3D p= new Point3D(7,6,0);
//        NodeData a= new NodeData(7,p);
//        Point3D p2= new Point3D(8,0,0);
//        NodeData a2= new NodeData(8,p);
//             Graph.addNode(a);
//             Graph.addNode(a2);
//             Graph.connect(a.getKey(),a2.getKey(),3);
//             assertEquals(3,Graph.getEdge(a.getKey(),a2.getKey()).getWeight());
    }
    @Test
    void getv(){
//             assertEquals(6,Graph.getV().size());
    }




}
