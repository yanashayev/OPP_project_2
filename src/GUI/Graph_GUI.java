package GUI;
import utils.*;
import java.awt.*;
import javax.swing.*;
import algorithms.*;
import dataStructure.*;

public class Graph_GUI {
    public static void main(String arg[]) {
        int width=1400;
        int height=1400;
        int r=200;
        DGraph g = new DGraph();
        Range rx=new Range(-10 ,10);
        Range ry=new Range(-10,10);
        DrawGraph(width,height,rx,ry,r,g);

    }
    public static void DrawGraph(int width, int height, Range rx, Range ry, int resolution, graph g) {
        JFrame frame = new JFrame(" draw graph");
        frame.setSize(width,height);
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());
        double delta = rx.get_max()-rx.get_min()/resolution;











    }
}