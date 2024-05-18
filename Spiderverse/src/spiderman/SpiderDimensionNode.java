package spiderman;

public class SpiderDimensionNode{
private int currentdimension;
private String spiderperson;
private int dimensionHome;


public SpiderDimensionNode(int currentdimension,String Spiderperson, int dimensionHome){
    this.currentdimension = currentdimension;
    this.spiderperson= Spiderperson;
    this.dimensionHome = dimensionHome;

}
public int GetDimension(){ return currentdimension;}
public void setDimension(int currentdimension){this.currentdimension = currentdimension;}

public String Spiderperson(){return spiderperson;}
public void getSpiderperson(String Spiderperson){this.spiderperson = Spiderperson;}

public int getHomeDimensionNode(){return dimensionHome;}
public void getHomeDimensionNode(int dimensionHome){this.dimensionHome = dimensionHome;}



}