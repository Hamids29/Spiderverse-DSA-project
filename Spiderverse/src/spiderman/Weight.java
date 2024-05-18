package spiderman;
public class Weight{
    private int weight;
    private int Dimension;
    private Weight Nextw;
    private int Canonevent;
   
   public Weight (int dimension, Weight nextw, int weight,int Canonevent ){
      this.Dimension = dimension;
      this.Nextw = nextw;
      this.weight = weight;
      this.Canonevent =Canonevent;
   }
   public int getDimension2() { return Dimension; }
   public void setDimension2(int dimension) { this.Dimension = dimension; }
   
   public Weight getNextDimensionNode2() { return Nextw; }
   public void setNextDimensionNode2(Weight Nextw) { this.Nextw = Nextw; }
   private int currentdimension;
   private String character;
   private int homedimension;
   
   public int getWeight2(){  return weight;  }
   public int getcurrentDim2(){ return currentdimension;}
   public int getcanonevent2(){ return Canonevent;}
   public String getChar2(){ return character;}
   public int homedimension2(){ return homedimension;}
}