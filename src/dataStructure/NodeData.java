package dataStructure;

import utils.Point3D;

import java.io.Serializable;

public class NodeData implements node_data, Serializable {
    private int Key;
    private Point3D Location;
    private double Weight;
    private String Info= "";
    private int Tag;
    public NodeData(){
        this.Tag=-1;
    }

    @Override
    public int getKey() {
        return this.Key;
    }

    @Override
    public Point3D getLocation() {
        return this.Location;
    }

    @Override
    public void setLocation(Point3D p) {
        this.Location=new Point3D(p);

    }

    @Override
    public double getWeight() {
        return this.Weight;
    }

    @Override
    public void setWeight(double w) {
        this.Weight=w;

    }

    @Override
    public String getInfo() {
        return this.Info;
    }

    @Override
    public void setInfo(String s) {
        this.Info=s;
    }

    @Override
    public int getTag() {
        return this.Tag;
    }

    @Override
    public void setTag(int t) {
        this.Tag=t;

    }
}
