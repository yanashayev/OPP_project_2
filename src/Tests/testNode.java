package Tests;
import dataStructure.NodeData;
import dataStructure.node_data;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import utils.Point3D;

public class testNode {
    @Test
    void getKey(){
            NodeData n= new NodeData(1);
            assertEquals(1, n.getKey());

          NodeData n2= new NodeData(3,6);
        assertEquals(3, n2.getKey());
    }
    @Test
    void getLocation(){
        Point3D p=new Point3D(1,2,0);
        NodeData n= new NodeData(1,p,7,"blabla",2);
        assertEquals( new Point3D(1,2,0), n.getLocation());

    }
    @Test
    void setLocation(){
        Point3D p=new Point3D(1,2,0);
        NodeData n= new NodeData();
        n.setLocation(p);
        assertEquals( new Point3D(1,2,0), n.getLocation());
    }
    @Test
    void getWeight(){
        Point3D p=new Point3D(1,2,0);
        NodeData n= new NodeData(1,p,7,"blabla",2);
        assertEquals(7,n.getWeight());
        NodeData n2= new NodeData(1,6);
        assertEquals(6,n2.getWeight());
    }
    @Test
    void setWeight(){
        Point3D p=new Point3D(1,2,0);
        NodeData n= new NodeData(1,p,7,"blabla",2);
        NodeData m= new NodeData();
        m.setWeight(n.getWeight());
        assertEquals(7, m.getWeight());
    }
    @Test
    void getInfo(){
        Point3D p=new Point3D(1,2,0);
        NodeData n= new NodeData(1,p,7,"blabla",2);
        assertEquals("blabla",n.getInfo());
    }
    @Test
    void setInfo(){
        NodeData n= new NodeData();
        n.setInfo("blabla");
        assertEquals("blabla",n.getInfo());
    }
    @Test
    void getTag(){
        Point3D p=new Point3D(1,2,0);
        NodeData n= new NodeData(1,p,7,"blabla",2);
        assertEquals(2,n.getTag());
        NodeData n2= new NodeData();
        assertEquals(-1,n2.getTag());
    }
    @Test
    void setTag(){
        NodeData n= new NodeData();
        n.setTag(2);
        assertEquals(2,n.getTag());
        NodeData n2= new NodeData();
        n2.setTag(n.getTag());
        assertEquals(2,n2.getTag());
    }

}

