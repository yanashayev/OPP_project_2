package GUI;
import utils.*;
import java.awt.*;
import javax.swing.*;
import algorithms.*;
import dataStructure.*;

public class Graph_GUI {
    public Graph_GUI(){

    }
    public static void main(String arg[]) {
        Range rx=new Range(-20 ,20);
        Range ry=new Range(-20,20);
        DGraph x=new DGraph();
        Point3D p=new Point3D(1,4,0);
            NodeData n=new NodeData(1,p);//num 1
            x.addNode(n);
            p=new Point3D(3,-2,0);
            NodeData m=new NodeData(2, p);//num 2
            x.addNode(m);
            p=new Point3D(9,-10,0);
            NodeData v=new NodeData(3,p);//num 3
            x.addNode(v);
            p=new Point3D(14,12,0);
            NodeData z=new NodeData(4,p);//num 4
            x.addNode(z);
            x.connect(1, 2, 10);
            x.connect(2, 1, 8);
            x.connect(1, 3, 10);
            x.connect(2, 3, 1);
            x.connect(4, 3, 11);
            x.connect(2, 4, 9);
            Graph_Algo g = new Graph_Algo();
            g.init(x);
            Graph_GUI p1 = new Graph_GUI();
            p1.DrawGraph(750,750,rx,ry,x);
        }


    public static void DrawGraph(int width, int height, Range rx, Range ry, graph g) {
        JFrame frame = new JFrame(" draw graph");
        frame.setSize(width,height);
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());
        for(node_data vertex:g.getV())
        {

            double x0=vertex.getLocation().x();
            double y0=vertex.getLocation().y();
            if(g.getE(vertex.getKey())!=null)
            {
                for(edge_data edge:g.getE(vertex.getKey()))
                {
                    StdDraw.setPenRadius(0.005);
                    StdDraw.setPenColor(Color.BLUE);
                    Font f=new Font("BOLD", Font.BOLD, 20);
                    StdDraw.setFont(f);
                    double x1=g.getNode(edge.getDest()).getLocation().x();
                    double y1=g.getNode(edge.getDest()).getLocation().y();

                    //draw edges
                    StdDraw.line(x0, y0, x1, y1);
                    StdDraw.setPenRadius(0.025);

                    //draw direction points
                    StdDraw.setPenColor(Color.GREEN);
                    StdDraw.point(x0*0.1+x1*0.9, y0*0.1+y1*0.9);

                    //draw dst vertex
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.point(x1, y1);


                    //draw vertices weights


                    //draw edges weight
                    StdDraw.setPenColor(Color.darkGray);
                    StdDraw.text((x0+x1)/2, (y0+y1)/2,edge.getWeight()+"");
                }
            }
            StdDraw.setPenRadius(0.025);
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(x0, y0);
        }











    }
}