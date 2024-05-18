package spiderman;
public class DimensionNode{
   private int weight;
 private int Dimension;
 private DimensionNode next;
 private int Canonevent;

public DimensionNode (int dimension, DimensionNode next ){
   this.Dimension = dimension;
   this.next = next;

}
public int getDimension() { return Dimension; }
public void setDimension(int dimension) { this.Dimension = dimension; }

public DimensionNode getNextDimensionNode() { return next; }
public void setNextDimensionNode(DimensionNode next) { this.next = next; }
private int currentdimension;
private String character;
private int homedimension;

public int getWeight(){  return weight;  }
public int getcurrentDim(){ return currentdimension;}
public int getcanonevent(){ return Canonevent;}
public String getChar(){ return character;}
public int homedimension(){ return homedimension;}
   //  public void createClusters(String parameter) {
   // StdIn.setFile("dimension.in");
   //  StdIn.setFile("cluster.out");
   //  }

}