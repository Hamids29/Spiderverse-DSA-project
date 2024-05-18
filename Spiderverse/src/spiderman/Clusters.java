package spiderman;

import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * DimensionInputFile name is passed through the command line as args[0]
 * Read from the DimensionsInputFile with the format:
 * 1. The first line with three numbers:
 * i. a (int): number of dimensions in the graph
 * ii. b (int): the initial size of the cluster table prior to rehashing
 * iii. c (double): the capacity(threshold) used to rehash the cluster table
 * 
 * Step 2:
 * ClusterOutputFile name is passed in through the command line as args[1]
 * Output to ClusterOutputFile with the format:
 * 1. n lines, listing all of the dimension numbers connected to
 * that dimension in order (space separated)
 * n is the size of the cluster table.
 * 
 * @author Seth Kelley
 */

public class Clusters {

public DimensionNode[] clusters;
    public static void main(String[] args) {
        if (args.length < 2) {
            StdOut.println(
                    "Execute: java -cp bin spiderman.Clusters <dimension INput file> <collider OUTput file>");
            return;
        }
        // WRITE YOUR CODE HERE
        Clusters test = new Clusters();
       // DimensionNode test2 = new DimensionNode(0, null);
       StdIn.setFile(args[0]);
       StdOut.setFile(args[1]);
        test.method1();
    }

    public void method1() {
        int counterdim = 0;
        // ArrayList<DimensionNode> clusterarray = new ArrayList<>();
        
        // StdOut.print("hcfbhcf");
        int numberofdimensions = StdIn.readInt();
        int hashtablesize = StdIn.readInt();
        clusters = new DimensionNode[hashtablesize];
        double capacity = StdIn.readDouble();
        for(int i =0; i< numberofdimensions; i++) {

            int dimension = StdIn.readInt();
            int canonevent = StdIn.readInt();
            int dimensionweight = StdIn.readInt();

            DimensionNode ptr = new DimensionNode(dimension, null);
            int index = finddimensionindex(dimension, clusters.length);
            counterdim++;
            if (clusters[index] == null) {
                clusters[index] = ptr;
            } 
            else {
            //traverse and make sure you refer to previous
            //DimensionNode ptr2 = ptr.getNextDimensionNode();
            ptr.setNextDimensionNode(clusters[index]);
            clusters[index] = ptr;
          //  System.out.println(clusters[index].getNextDimensionNode().getDimension());
            // ptr = ptr.getNextDimensionNode();
            }
            if (counterdim / clusters.length >= capacity) {
                // rehash
                clusters = rehash(clusters);
            }
        }
//wrap around starts here
    for(int i =0; i < clusters.length; i++){    
    //     DimensionNode regularprev;
    //     DimensionNode holder2;
    
    // DimensionNode firstprev = clusters[clusters.length -1];
    // //make a copy of first prev and etc with no .next
    // DimensionNode secondprev = clusters[clusters.length -2];
    DimensionNode test = clusters[i];

    if(i >= 2){
       //holder1 =  clusters[i-1];
       while(test.getNextDimensionNode()!=null){
      //  System.out.println(i    );
        test = test.getNextDimensionNode();
       }
       DimensionNode holdvalue1 = new DimensionNode((clusters[i-1]).getDimension(), null);
       test.setNextDimensionNode(holdvalue1);
       test = test.getNextDimensionNode();
       DimensionNode holdvalue2 = new DimensionNode((clusters[i-2]).getDimension(), null);
       test.setNextDimensionNode(holdvalue2);

       //ptr.setnext to set and then get next and then set again

    } 
    if(i ==0){
        while(test.getNextDimensionNode()!=null){
          //  System.out.println("differentone");
            test= test.getNextDimensionNode();
        }
        DimensionNode holdvalue1 = new DimensionNode((clusters[clusters.length-1]).getDimension(), null);
        test.setNextDimensionNode(holdvalue1);
        test = test.getNextDimensionNode();
        DimensionNode holdvalue2 = new DimensionNode((clusters[clusters.length-2]).getDimension(), null);
        test.setNextDimensionNode(holdvalue2);
        // DimensionNode firstprev2 = new DimensionNode(clusters[clusters.length-1].getDimension(), null);
        // DimensionNode secondprev2 = new DimensionNode(clusters[clusters.length-2].getDimension(), null);
        // clusters[i].setNextDimensionNode(firstprev2);
        // clusters[i].setNextDimensionNode(secondprev2);
    } 
    if(i ==1){
        while(test.getNextDimensionNode()!=null){
        //    System.out.println("third loop");
            test =test.getNextDimensionNode();
           }
           DimensionNode holdvalue1= new DimensionNode(clusters[i-1].getDimension(), null);
           test.setNextDimensionNode(holdvalue1);
           test = test.getNextDimensionNode();
           DimensionNode holdvalue2 = new DimensionNode(clusters[clusters.length -1].getDimension(), null);
           test.setNextDimensionNode(holdvalue2);
    }
    }
          
        //StdOut.print("hrdijdjn");
        
        for (int i = 0; i < clusters.length; i++) {
            DimensionNode ptr = clusters[i];
            //new
            //end of new
            while (ptr != null) {
                StdOut.print(ptr.getDimension() + " ");
                ptr = ptr.getNextDimensionNode();
            }
            StdOut.println();
        }
        // StdOut.print("hcfbhcf");
        //System.out.println(clusters);


    }

    public DimensionNode[] rehash(DimensionNode[] x) {
        
        DimensionNode newarray[] = new DimensionNode[clusters.length * 2];
        for (int i = 0; i < clusters.length; i++) {
            DimensionNode ptr = clusters[i];
            DimensionNode test = clusters[i];
            // int index = finddimensionindex(dimension, hashtablesize);
            while (ptr != null) {
                // ptr
                // ptr -> new
                // hash stuff
                // ptr.getDimension(); & ptr.getNextDimensionNode(newarray[i]);
                DimensionNode test2 = new DimensionNode(ptr.getDimension(), null);
               // newarray[finddimensionindex(ptr.getDimension(), clusters.length * 2)] = test2;
                if (newarray[finddimensionindex(ptr.getDimension(), clusters.length * 2)]== null) {
                    newarray[finddimensionindex(ptr.getDimension(), clusters.length * 2)] = test2;
                } 
                else {
                test2.setNextDimensionNode(newarray[finddimensionindex(test2.getDimension(), clusters.length * 2)]);
                newarray[finddimensionindex(ptr.getDimension(), clusters.length * 2)]= test2;
              //  System.out.println(clusters[index].getNextDimensionNode().getDimension());
                // ptr = ptr.getNextDimensionNode();
                }
                ptr = ptr.getNextDimensionNode();
                // }
            }
        }

        return newarray;
    }

    public int finddimensionindex(int dimension, int hashtablesize) {
        int index = dimension % hashtablesize;
        return index;

    }

}
