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
 * 2. a lines, each with:
 * i. The dimension number (int)
 * ii. The number of canon events for the dimension (int)
 * iii. The dimension weight (int)
 * 
 * Step 2:
 * SpiderverseInputFile name is passed through the command line as args[1]
 * Read from the SpiderverseInputFile with the format:
 * 1. d (int): number of people in the file
 * 2. d lines, each with:
 * i. The dimension they are currently at (int)
 * ii. The name of the person (String)
 * iii. The dimensional signature of the person (int)
 * 
 * Step 3:
 * ColliderOutputFile name is passed in through the command line as args[2]
 * Output to ColliderOutputFile with the format:
 * 1. e lines, each with a different dimension number, then listing
 * all of the dimension numbers connected to that dimension (space separated)
 * 
 * @author Seth Kelley
 */

public class Collider {
    public SpiderDimensionNode[] SpiderDimensionNodes;
    private DimensionNode[] clusters;
   // private DimensionNode first;
    ArrayList<DimensionNode> adjacentlist = new ArrayList<>();

    public void adjlist() {
        // }
        // ATTEMPT 2 BELOW SFHDEJÔJG©Ôgjfg
        HashMap<Integer, ArrayList<DimensionNode>> list = new HashMap<>();
        //DimensionNode first = clusters[0];
        for (int i = 0; i < clusters.length; i++) {
            DimensionNode ref = new DimensionNode(clusters[i].getDimension(), null);
            DimensionNode testing = new DimensionNode(clusters[0].getDimension(), null);
         //   System.out.println("test" + i);
            DimensionNode ptr = clusters[i].getNextDimensionNode();
           list.putIfAbsent(ref.getDimension(),new ArrayList<>());
        //    if(list.containsKey(ref.getDimension())){
        //    // System.out.println("PASSED");
        //    }
            while (ptr != null) {
                if (ptr.getDimension() != ref.getDimension()) { // if
                    if(list.containsKey(ptr.getDimension())){
                    list.get(ptr.getDimension()).add(ref);
                    } 
                    else{   
                        // if(ptr.getDimension()  == 31){
                        //     System.out.println("HIT 31!" + ref.getDimension());
                        //    }   
                        list.put(ptr.getDimension(), new ArrayList<>()); 
                        list.get(ptr.getDimension()).add(ref);
                    }
                    if(list.containsKey(ref.getDimension())){
                    list.get(ref.getDimension()).add(ptr); // add new num
                    } 
                }
                ptr= ptr.getNextDimensionNode();
            }

        }

        for (Map.Entry<Integer, ArrayList<DimensionNode>> e : list.entrySet()) {
            // StdOut.println(e.getKey().getDimension() + " " + e.getKey().getDimension());
            
        }
        for (Map.Entry<Integer, ArrayList<DimensionNode>> entry : list.entrySet()) {
            int key = entry.getKey();
            ArrayList<DimensionNode> test = entry.getValue();
            //StdOut.println("Size of arraylist " + test.size());
            StdOut.print( key + " " );
            for( DimensionNode x : test){
                StdOut.print(x.getDimension() + " ");
            }
            StdOut.println();
        }

    }
    

    public void storechars() {
        StdIn.setFile("spiderverse.in");
        int filesize = StdIn.readInt();
        // StdIn.setFile("spiderverse.in");
        // // WRITE YOUR CODE HERE
        for (int i = 0; i < filesize; i++) {
            SpiderDimensionNodes = new SpiderDimensionNode[filesize];
            int dimensionnum = StdIn.readInt();
            String character = StdIn.readString();
            int dimensiontheybelongin = StdIn.readInt();
            SpiderDimensionNode holder = new SpiderDimensionNode(dimensionnum, character, dimensiontheybelongin);
            SpiderDimensionNodes[i] = holder;
           // System.out.println(SpiderDimensionNodes[i].getHomeDimensionNode());


        }
    }

    public void method1() {
        int counterdim = 0;
        int numberofdimensions = StdIn.readInt();
        int hashtablesize = StdIn.readInt();
        clusters = new DimensionNode[hashtablesize];
        double capacity = StdIn.readDouble();
        for (int i = 0; i < numberofdimensions; i++) {
            int dimension = StdIn.readInt();
            int canonevent = StdIn.readInt();
            int dimensionweight = StdIn.readInt();
            DimensionNode ptr = new DimensionNode(dimension, null);
            int index = finddimensionindex(dimension, clusters.length);
            counterdim++;
            if (clusters[index] == null) {
                clusters[index] = ptr;
            } else {
                // traverse and make sure you refer to previous
                ptr.setNextDimensionNode(clusters[index]);
                clusters[index] = ptr;
            }
            if (counterdim / clusters.length >= capacity) {
                // rehash
                clusters = rehash(clusters);
            }
        }
        // wrap around starts here
        for (int i = 0; i < clusters.length; i++) {
            // DimensionNode firstprev = clusters[clusters.length -1];
            // //make a copy of first prev and etc with no .next
            // DimensionNode secondprev = clusters[clusters.length -2];
            DimensionNode test = clusters[i];

            if (i >= 2) {
                while (test.getNextDimensionNode() != null) {
                    test = test.getNextDimensionNode();
                }
                DimensionNode holdvalue1 = new DimensionNode((clusters[i - 1]).getDimension(), null);
                test.setNextDimensionNode(holdvalue1);
                test = test.getNextDimensionNode();
                DimensionNode holdvalue2 = new DimensionNode((clusters[i - 2]).getDimension(), null);
                test.setNextDimensionNode(holdvalue2);
            }
            if (i == 0) {
                while (test.getNextDimensionNode() != null) {
                    test = test.getNextDimensionNode();
                }
                DimensionNode holdvalue1 = new DimensionNode((clusters[clusters.length - 1]).getDimension(), null);
                test.setNextDimensionNode(holdvalue1);
                test = test.getNextDimensionNode();
                DimensionNode holdvalue2 = new DimensionNode((clusters[clusters.length - 2]).getDimension(), null);
                test.setNextDimensionNode(holdvalue2);
            }
            if (i == 1) {
                while (test.getNextDimensionNode() != null) {
                    test = test.getNextDimensionNode();
                }
                DimensionNode holdvalue1 = new DimensionNode(clusters[i - 1].getDimension(), null);
                test.setNextDimensionNode(holdvalue1);
                test = test.getNextDimensionNode();
                DimensionNode holdvalue2 = new DimensionNode(clusters[clusters.length - 1].getDimension(), null);
                test.setNextDimensionNode(holdvalue2);
            }
        }

    }

    public DimensionNode[] rehash(DimensionNode[] x) {

        DimensionNode newarray[] = new DimensionNode[clusters.length * 2];
        for (int i = 0; i < clusters.length; i++) {
            DimensionNode ptr = clusters[i];
            DimensionNode test = clusters[i];
            while (ptr != null) {
                // ptr
                // ptr -> new
                // hash stuff
                DimensionNode test2 = new DimensionNode(ptr.getDimension(), null);
                if (newarray[finddimensionindex(ptr.getDimension(), clusters.length * 2)] == null) {
                    newarray[finddimensionindex(ptr.getDimension(), clusters.length * 2)] = test2;
                } else {
                    test2.setNextDimensionNode(newarray[finddimensionindex(test2.getDimension(), clusters.length * 2)]);
                    newarray[finddimensionindex(ptr.getDimension(), clusters.length * 2)] = test2;
                }
                ptr = ptr.getNextDimensionNode();
            }
        }
        return newarray;
    }

    public int finddimensionindex(int dimension, int hashtablesize) {
        int index = dimension % hashtablesize;
        return index;

    }

    public static void main(String[] args) {

        if (args.length < 3) {
            StdOut.println(
                    "Execute: java -cp bin spiderman.Collider <dimension INput file> <spiderverse INput file> <collider OUTput file>");
            return;
        }
        StdIn.setFile(args[0]); // <- reads first file in json
        StdOut.setFile(args[2]);
        // Clusters test = new Clusters();
        Collider test2 = new Collider();
        test2.method1();
        test2.adjlist();
        test2.storechars();

        
    }

}