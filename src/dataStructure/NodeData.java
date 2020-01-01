package dataStructure;

import utils.Point3D;

import java.io.Serializable;

public class NodeData implements node_data, Serializable {
    private int Key;
    private Point3D Location;
    private double Weight=Double.MAX_VALUE;
    private String Info= "false";
    private int Tag=-1;
    public NodeData(){
        this.setInfo("false");
        this.Tag=-1;
    }
    public NodeData(String s){
        this.Key = 0;
        this.Location = new Point3D(0,0,0);
        this.Weight = Double.MAX_VALUE;
        this.Tag = -1;
        this.setInfo(s);
    }
    public NodeData(int key, double weight){
        this.Key=key;
        this.Weight=weight;
    }
    public NodeData(int key){
        this.Key=key;
    }
    public NodeData(int key, Point3D location ){
        this.Key=key;
        this.Location=location;
    }

    public NodeData(int key, Point3D location , double weight,String info,int tag){
        this.Key=key;
        this.Location=location;
        this.Weight=weight;
        this.Info=info;
        this.Tag=tag;
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
    @Override
    public  String toString(){
        return "key: "+this.getKey()+" Location: "+this.Location+" Weight: "+this.Weight+ " Info: "
                +this.Info +" Tag: "+this.Tag;
    }
}
