package dataStructure;

import java.io.Serializable;

public class EdgeData implements edge_data, Serializable {
   private int Src;
  private   int Dest;
    private double Weight;
   private String Info="";
    private int Tag;
public EdgeData(){
    this.Tag=-1;
}
public EdgeData( int Src, int Dest, double Weight){
    this.Src=Src;
    this.Dest=Dest;
    this.Weight=Weight;
}
    public EdgeData(int src, int dest, double weight, String info, int tag){
    this.Src=src;
    this.Dest=dest;
    this.Weight=weight;
    this.Info=info;
    this.Tag=tag;
    }
    @Override
    public int getSrc() {
        return this.Src;
    }

    @Override
    public int getDest() {
        return this.Dest;
    }

    @Override
    public double getWeight() {
        return this.Weight;
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
    public String toString(){
    return "Src: "+this.Src+ " Weight: "+this.Weight+" Dest: "+this.Dest+" Info: "+this.Info+" Tag: "+this.Tag;
    }
}
