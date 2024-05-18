package spiderman;

import java.util.*;
/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * DimensionInputFile name is passed through the command line as args[0]
 * Read from the DimensionsInputFile with the format:
 * 1. The first line with three numbers:
 *      i.    a (int): number of dimensions in the graph
 *      ii.   b (int): the initial size of the cluster table prior to rehashing
 *      iii.  c (double): the capacity(threshold) used to rehash the cluster table 
 * 2. a lines, each with:
 *      i.    The dimension number (int)
 *      ii.   The number of canon events for the dimension (int)
 *      iii.  The dimension weight (int)
 * 
 * Step 2:
 * SpiderverseInputFile name is passed through the command line as args[1]
 * Read from the SpiderverseInputFile with the format:
 * 1. d (int): number of people in the file
 * 2. d lines, each with:
 *      i.    The dimension they are currently at (int)
 *      ii.   The name of the person (String)
 *      iii.  The dimensional signature of the person (int)
 * 
 * Step 3:
 * SpotInputFile name is passed through the command line as args[2]
 * Read from the SpotInputFile with the format:
 * Two integers (line seperated)
 *      i.    Line one: The starting dimension of Spot (int)
 *      ii.   Line two: The dimension Spot wants to go to (int)
 * 
 * Step 4:
 * TrackSpotOutputFile name is passed in through the command line as args[3]
 * Output to TrackSpotOutputFile with the format:
 * 1. One line, listing the dimenstional number of each dimension Spot has visited (space separated)
 * 
 * @author Seth Kelley
 */

public class TrackSpot {
    List<Integer> visited = new ArrayList<>();
    HashMap<Integer, ArrayList<DimensionNode>> list = new HashMap<>();
    public SpiderDimensionNode[] SpiderDimensionNodes;
    private DimensionNode[] clusters;
   
   // private DimensionNode first;
   // ArrayList<DimensionNode> adjacentlist = new ArrayList<>();

   // visit point, add to array, find next head with has key, then check if that LL was already visited (.contains),
   // -> if not go to head. 
   //set this to head value after every iteration and then compare 
 public void DFShelp( int startingpoint, ArrayList<Integer> visited, int endpoint){
    // DimensionNode compare = new DimensionNode(startingpoint, null);
//     if(startingpoint == endpoint){
//    return;
//     }
    int sizeofgraph = list.get(startingpoint).size();
    visited.add(startingpoint);
   // System.out.println(visited + " ");
    for(int i = 0; i< sizeofgraph; i++){
        if(!visited.contains(list.get(startingpoint).get(i).getDimension())){
            DFShelp(list.get(startingpoint).get(i).getDimension(), visited, endpoint);
        }
        }

    }
public void printm3 (ArrayList<Integer> insert, int endpoint){
for(int i = 0; i < insert.size(); i++){
    StdOut.print(insert.get(i)+ " ");
    if(insert.get(i) == endpoint){
        //StdOut.print(endpoint);
        break;
    }
}
    }

    public boolean hasvisited(List<Integer> x, int value, int compareto){ 

        return false; 
    }
    public void adjlist() {
        // }
        // ATTEMPT 2 BELOW SFHDEJÔJG©Ôgjf 
        //DimensionNode first = clusters[0];
        for (int i = 0; i < clusters.length; i++) {
            DimensionNode ref = new DimensionNode(clusters[i].getDimension(), null);
            DimensionNode testing = new DimensionNode(clusters[0].getDimension(), null);
            DimensionNode ptr = clusters[i].getNextDimensionNode();
           list.putIfAbsent(ref.getDimension(),new ArrayList<>());
        //    if(list.containsKey(ref.getDimension())){
        //    }
            while (ptr != null) {
                if (ptr.getDimension() != ref.getDimension()) { // if
                    if(list.containsKey(ptr.getDimension())){
                    list.get(ptr.getDimension()).add(ref);
                    } 
                    else{   

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
//WEIRD PRINTING
        // for (Map.Entry<Integer, ArrayList<DimensionNode>> entry : list.entrySet()) {
        //     int key = entry.getKey();
        //     ArrayList<DimensionNode> test = entry.getValue();

        //     StdOut.print( key + " " );
        //     for( DimensionNode x : test){
        //         StdOut.print(x.getDimension() + " ");
        //     }
        //     StdOut.println();
        // }

    }
    

    public void storechars(String filename) {
        StdIn.setFile(filename);
        int filesize = StdIn.readInt();
        // StdIn.setFile("spiderverse.in");
        // // WRITE YOUR CODE HERE
        for (int i = 0; i < filesize; i++) {
            SpiderDimensionNodes = new SpiderDimensionNode[filesize];
            int dimensionnum = StdIn.readInt();
            String character = StdIn.readString();
            int dimensiontheybelongin = StdIn.readInt();
            //SpiderDimensionNode holder = new SpiderDimensionNode(dimensionnum, character, dimensiontheybelongin);
            SpiderDimensionNodes[i] = new SpiderDimensionNode(dimensionnum, character, dimensiontheybelongin);
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
//System.out.println(args.length);

        if ( args.length < 4 ) {
            StdOut.println(
                "Execute: java -cp bin spiderman.TrackSpot <dimension INput file> <spiderverse INput file> <spot INput file> <trackspot OUTput file>");
                return;
        }
       // System.out.println(args.length);
      ArrayList<Integer> visited = new ArrayList<>();
        TrackSpot test2 = new TrackSpot();
        StdIn.setFile(args[0]);
        test2.method1();
        StdOut.setFile(args[3]);
        test2.adjlist();
        
        //StdIn.setFile(args[1]);
        test2.storechars(args[1]);
 
        StdIn.setFile(args[2]);

        int startpoint = StdIn.readInt();
        int endpoint =StdIn.readInt();
        test2.DFShelp(startpoint, visited, endpoint);
        test2.printm3(visited,endpoint );
        
   
        // WRITE YOUR CODE HERE
        //StdOut.print("HELLO");
        
    }
}
