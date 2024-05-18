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
 * HubInputFile name is passed through the command line as args[2]
 * Read from the HubInputFile with the format:
 * One integer
 * i. The dimensional number of the starting hub (int)
 * 
 * Step 4:
 * CollectedOutputFile name is passed in through the command line as args[3]
 * Output to CollectedOutputFile with the format:
 * 1. e Lines, listing the Name of the anomaly collected with the Spider who
 * is at the same Dimension (if one exists, space separated) followed by
 * the Dimension number for each Dimension in the route (space separated)
 * 
 * @author Seth Kelley
 */

public class CollectAnomalies {

   
    HashMap<Integer, ArrayList<DimensionNode>> graph = new HashMap<>();
    HashMap<Integer, ArrayList<Integer>> BFSmap = new HashMap<>();
    // SpiderDimensionNode[] SpiderDimensionNodes;
    private DimensionNode[] clusters;

    public ArrayList<Integer> BFS(int hub, int dimension) {
        ArrayList<Integer> visited = new ArrayList<>();
        HashMap <Integer,Integer> parents = new HashMap<>();
        ArrayList<Integer> shortestPath = new ArrayList<>();
        // Now we check what has been added rather than visited
        Queue<Integer> q = new LinkedList<>();
        // Add our initial element to our queue and mark as added
        q.add(hub);
        visited.add(hub);
        // Keep going until there are no more vertices to look at
        int current = hub;
        while (!q.isEmpty()) {
            current = q.remove();
            if(current==dimension){
                break;
            }
            // Go through neighbors, and if they're unvisited add to queue and mark as added
            // for (int i = 0; i < numVertices; i++) {
            for (DimensionNode neighbornode : graph.get(current)) {
                if (!visited.contains(neighbornode.getDimension())) {
                    visited.add(neighbornode.getDimension());
                    q.add(neighbornode.getDimension());
                    parents.put(neighbornode.getDimension(), current);
               
                }
            }
        }
        shortestPath.add(hub);
        while(current!=hub){
            shortestPath.add(1, current);
            current=parents.get(current);

        }
        return shortestPath;
    }

    public ArrayList<SpiderDimensionNode> findAnomalys(ArrayList<SpiderDimensionNode> chars) {
        ArrayList<SpiderDimensionNode> anomaly = new ArrayList<>();
        for (int i = 0; i < chars.size(); i++) {
            if (chars.get(i).GetDimension() != 928
                    && chars.get(i).GetDimension() != chars.get(i).getHomeDimensionNode()) {
                anomaly.add(chars.get(i));
            }
        }
        return anomaly;
    }

    public SpiderDimensionNode checkForSpider(SpiderDimensionNode anomaly, ArrayList<SpiderDimensionNode> chars) {
        for( SpiderDimensionNode node : chars){
        
            if(node.GetDimension()== anomaly.GetDimension() && node.getHomeDimensionNode() == node.GetDimension()){
                return node;
        
            }


        }      
         
        return null;
    }

    public String collectedAnomalyToString(SpiderDimensionNode Anomaly, SpiderDimensionNode spiderperson,
            ArrayList<Integer> shortestpath) {
                String result = "";
                result += Anomaly.Spiderperson();
                if(spiderperson!=null){
                    result+= " "+ spiderperson.Spiderperson();
                } else{
                    for(int i =0; i <shortestpath.size()-1; i++){
                        result+= " " + shortestpath.get(i);
                    }
                }
                for(int i = shortestpath.size()-1; i >= 0; i-- ){
                    result+= " " + shortestpath.get(i);
                }
                return result;
    }

    public void adjlist() {
    
        for (int i = 0; i < clusters.length; i++) {
            DimensionNode ref = new DimensionNode(clusters[i].getDimension(), null);
            DimensionNode testing = new DimensionNode(clusters[0].getDimension(), null);
            DimensionNode ptr = clusters[i].getNextDimensionNode();
            graph.putIfAbsent(ref.getDimension(), new ArrayList<>());
            // if(graph.containsKey(ref.getDimension())){
            // }
            while (ptr != null) {
                if (ptr.getDimension() != ref.getDimension()) { // if
                    if (graph.containsKey(ptr.getDimension())) {
                        graph.get(ptr.getDimension()).add(ref);
                    } else {

                        graph.put(ptr.getDimension(), new ArrayList<>());
                        graph.get(ptr.getDimension()).add(ref);
                    }
                    if (graph.containsKey(ref.getDimension())) {
                        graph.get(ref.getDimension()).add(ptr); // add new num
                    }
                }
                ptr = ptr.getNextDimensionNode();

            }

        }
        // WEIRD PRINTING
        // for (Map.Entry<Integer, ArrayList<DimensionNode>> entry : graph.entrySet()) {
        // int key = entry.getKey();
        // ArrayList<DimensionNode> test = entry.getValue();

        // StdOut.print( key + " " );
        // for( DimensionNode x : test){
        // StdOut.print(x.getDimension() + " ");
        // }
        // StdOut.println();
        // }

    }

    public void storechars(String filename, ArrayList<SpiderDimensionNode> chars) {
        // ArrayList<SpiderDimensionNode> f = new ArrayList<>();
        StdIn.setFile(filename);
        int filesize = StdIn.readInt();
        // // WRITE YOUR CODE HERE
        for (int i = 0; i < filesize; i++) {
            // = new SpiderDimensionNode[filesize];

            int dimensionnum = StdIn.readInt();
            String character = StdIn.readString();
            int dimensiontheybelongin = StdIn.readInt();
            SpiderDimensionNode test = new SpiderDimensionNode(dimensionnum, character, dimensiontheybelongin);
            // SpiderDimensionNode holder = new SpiderDimensionNode(dimensionnum, character,
            // dimensiontheybelongin);
            // test[i] = new SpiderDimensionNode(dimensionnum, character,
            // dimensiontheybelongin);
            chars.add(test);
            // System.out.println(test[i].getHomeDimensionNode());
            // System.out.println(SpiderDimensionNodes[i].getHomeDimensionNode());

        }
    }

    public void createClusters() {
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

        if (args.length < 4) {
            StdOut.println(
                    "Execute: java -cp bin spiderman.CollectAnomalies <dimension INput file> <spiderverse INput file> <hub INput file> <collected OUTput file>");
            return;
        }

        StdIn.setFile(args[1]); // <- reads spiderverse.in and this makes arrayn for storing characters
        ArrayList<SpiderDimensionNode> chars = new ArrayList<>();
        ArrayList<Integer> visited = new ArrayList<>();
        CollectAnomalies test2 = new CollectAnomalies();
        StdIn.setFile(args[0]); // <- reads first file in json(dimensions.in)
        test2.createClusters(); // <- calls first method
        StdOut.setFile(args[3]); // <- sets output file to third file in json (collectanomalies.out)
        test2.adjlist(); // calls 2nd method and makes adj list
        test2.storechars(args[1], chars);// <- stores characters in previous array

        StdIn.setFile(args[2]); // <- reads third file hub.in
        int hub = StdIn.readInt();
        //extracted all info and created adjacency list for BFS traversal
        ArrayList<SpiderDimensionNode> anomalies = test2.findAnomalys(chars);
        for(SpiderDimensionNode anomaly : anomalies ){
           SpiderDimensionNode guard = test2.checkForSpider(anomaly, chars);
           int dimension = anomaly.GetDimension();
           ArrayList<Integer> path = test2.BFS(hub, dimension);
           String collectAnomalyStr = test2.collectedAnomalyToString(anomaly, guard, path);
           StdOut.println(collectAnomalyStr);
        }
    }
}
