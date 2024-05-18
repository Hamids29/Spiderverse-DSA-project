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
 * Read from the SpotInputFile with the format:
 * One integer
 * i. The dimensional number of the starting hub (int)
 * 
 * Step 4:
 * AnomaliesInputFile name is passed through the command line as args[3]
 * Read from the AnomaliesInputFile with the format:
 * 1. e (int): number of anomalies in the file
 * 2. e lines, each with:
 * i. The Name of the anomaly which will go from the hub dimension to their home
 * dimension (String)
 * ii. The time allotted to return the anomaly home before a canon event is
 * missed (int)
 * 
 * Step 5:
 * ReportOutputFile name is passed in through the command line as args[4]
 * Output to ReportOutputFile with the format:
 * 1. e Lines (one for each anomaly), listing on the same line:
 * i. The number of canon events at that anomalies home dimensionafter being
 * returned
 * ii. Name of the anomaly being sent home
 * iii. SUCCESS or FAILED in relation to whether that anomaly made it back in
 * time
 * iv. The route the anomaly took to get home
 * 
 * @author Seth Kelley
 */

public class GoHomeMachine {
    HashMap<Integer, ArrayList<Weight>> graph = new HashMap<>();
    HashMap<Integer, ArrayList<Integer>> BFSmap = new HashMap<>();
    // SpiderDimensionNode[] SpiderDimensionNodes;

    private Weight[] clusters;

    // private Weight[] clusters2;
    public void dijkstra(int source) {

        ArrayList<Weight> done = new ArrayList<>();
        HashMap<Integer, Integer> distance = new HashMap<>();
        PriorityQueue<Integer> fringe = new PriorityQueue<>();
        HashMap<Integer, Integer> pred = new HashMap<>();
        fringe.add(source);
        for (int node : graph.keySet()) {
            pred.put(node, null);
            distance.put(node, Integer.MAX_VALUE);

        }
    distance.put(source, 0);
    while(!fringe.isEmpty()){
         Weight m = closestVertex(distance, fringe, source);
        done.add(m);
        for(Weight neighbor : graph.get(m)){
            if(!done.contains(neighbor)){//maybe
                if(distance.get(neighbor.getWeight2()).equals(Integer.MAX_VALUE)){
                distance.put(neighbor.getDimension2(), distance.get(m.getWeight2()) + calculateWeight(m, neighbor));

            }
            }
        }
        
    }

        // int m = Fringe.get

    }
    private ArrayList <Integer> closestVertex (HashMap<Integer, Integer> distance,PriorityQueue<Integer> fringe, 
    int source){
        ArrayList<Integer> x = new ArrayList<>();
        for(Weight node: graph.get(source)){
           x.add(node.getWeight2());
        }
        return x;
    
    private int calculateWeight (Weight node, Weight node2){

        return node.getWeight2()+node2.getWeight2();
    }

    public ArrayList<SpiderDimensionNode> MoveDimensions(ArrayList<SpiderDimensionNode> chars, int hub2) {
        for (int i = 0; i < chars.size(); i++) {
            if (chars.get(i).GetDimension() != 928
                    && chars.get(i).GetDimension() != chars.get(i).getHomeDimensionNode()) {
                chars.get(i).setDimension(hub2);
            }
        }
        return chars;
    }

    public ArrayList<Integer> BFS(int hub, int dimension) {
        ArrayList<Integer> visited = new ArrayList<>();
        HashMap<Integer, Integer> parents = new HashMap<>();
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
            if (current == dimension) {
                break;
            }
            // Go through neighbors, and if they're unvisited add to queue and mark as added
            // for (int i = 0; i < numVertices; i++) {
            for (Weight neighbornode : graph.get(current)) {
                if (!visited.contains(neighbornode.getDimension2())) {
                    visited.add(neighbornode.getDimension2());
                    q.add(neighbornode.getDimension2());
                    parents.put(neighbornode.getDimension2(), current);

                }
            }
        }
        shortestPath.add(hub);
        while (current != hub) {
            shortestPath.add(1, current);
            current = parents.get(current);

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
        for (SpiderDimensionNode node : chars) {

            if (node.GetDimension() == anomaly.GetDimension() && node.getHomeDimensionNode() == node.GetDimension()) {
                return node;

            }

        }

        return null;
    }

    public String collectedAnomalyToString(SpiderDimensionNode Anomaly, SpiderDimensionNode spiderperson,
            ArrayList<Integer> shortestpath) {
        String result = "";
        result += Anomaly.Spiderperson();
        if (spiderperson != null) {
            result += " " + spiderperson.Spiderperson();
        } else {
            for (int i = 0; i < shortestpath.size() - 1; i++) {
                result += " " + shortestpath.get(i);
            }
        }
        for (int i = shortestpath.size() - 1; i >= 0; i--) {
            result += " " + shortestpath.get(i);
        }
        return result;
    }

    public void adjlist() {

        for (int i = 0; i < clusters.length; i++) {
            Weight ref = new Weight(clusters[i].getDimension2(), null, clusters[i].getWeight2(),
                    clusters[i].getcanonevent2());
            // DimensionNode testing = new DimensionNode(clusters[0].getDimension(), null,
            // clusters[i].getWeight(), clusters[i].getcanonevent());
            Weight ptr = clusters[i].getNextDimensionNode2();
            graph.putIfAbsent(ref.getDimension2(), new ArrayList<>());
            // if(graph.containsKey(ref.getDimension())){
            // }
            while (ptr != null) {
                if (ptr.getDimension2() != ref.getDimension2()) { // if
                    if (graph.containsKey(ptr.getDimension2())) {
                        graph.get(ptr.getDimension2()).add(ref);
                    } else {

                        graph.put(ptr.getDimension2(), new ArrayList<>());
                        graph.get(ptr.getDimension2()).add(ref);
                    }
                    if (graph.containsKey(ref.getDimension2())) {
                        graph.get(ref.getDimension2()).add(ptr); // add new num
                    }
                }
                ptr = ptr.getNextDimensionNode2();

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
        clusters = new Weight[hashtablesize];
        double capacity = StdIn.readDouble();
        for (int i = 0; i < numberofdimensions; i++) {
            int dimension = StdIn.readInt();
            int canonevent = StdIn.readInt();
            int dimensionweight = StdIn.readInt();

            // Weight weight = new Weight(dimensionweight, canonevent);
            Weight ptr = new Weight(dimension, null, dimensionweight, canonevent);
            int index = finddimensionindex(dimension, clusters.length);
            counterdim++;
            if (clusters[index] == null) {
                clusters[index] = ptr;
            } else {
                // traverse and make sure you refer to previous
                ptr.setNextDimensionNode2(clusters[index]);
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
            Weight test = clusters[i];

            if (i >= 2) {
                while (test.getNextDimensionNode2() != null) {
                    test = test.getNextDimensionNode2();
                }
                Weight holdvalue1 = new Weight((clusters[i - 1]).getDimension2(), null, (clusters[i - 1]).getWeight2(),
                        (clusters[i - 1]).getcanonevent2());
                test.setNextDimensionNode2(holdvalue1);
                test = test.getNextDimensionNode2();
                Weight holdvalue2 = new Weight((clusters[i - 2]).getDimension2(), null, (clusters[i - 2]).getWeight2(),
                        (clusters[i - 2]).getcanonevent2());
                test.setNextDimensionNode2(holdvalue2);
            }
            if (i == 0) {
                while (test.getNextDimensionNode2() != null) {
                    test = test.getNextDimensionNode2();
                }
                Weight holdvalue1 = new Weight((clusters[clusters.length - 1]).getDimension2(), null,
                        (clusters[clusters.length - 1]).getWeight2(), (clusters[clusters.length - 1]).getcanonevent2());
                test.setNextDimensionNode2(holdvalue1);
                test = test.getNextDimensionNode2();
                Weight holdvalue2 = new Weight((clusters[clusters.length - 2]).getDimension2(), null,
                        ((clusters[clusters.length - 2]).getWeight2()),
                        (clusters[clusters.length - 2]).getcanonevent2());
                test.setNextDimensionNode2(holdvalue2);
            }
            if (i == 1) {
                while (test.getNextDimensionNode2() != null) {
                    test = test.getNextDimensionNode2();
                }
                Weight holdvalue1 = new Weight((clusters[i - 1]).getDimension2(), null,
                        ((clusters[i - 1]).getWeight2()), (clusters[i - 1]).getcanonevent2());
                test.setNextDimensionNode2(holdvalue1);
                test = test.getNextDimensionNode2();
                Weight holdvalue2 = new Weight(clusters[clusters.length - 1].getDimension2(), null,
                        (clusters[i - 1]).getWeight2(), (clusters[i - 1]).getcanonevent2());
                test.setNextDimensionNode2(holdvalue2);
            }
        }
    }

    public Weight[] rehash(Weight[] x) {

        Weight newarray[] = new Weight[clusters.length * 2];
        for (int i = 0; i < clusters.length; i++) {
            Weight ptr = clusters[i];
            Weight test = clusters[i];
            while (ptr != null) {
                // ptr
                // ptr -> new
                // hash stuff
                Weight test2 = new Weight(ptr.getDimension2(), null, ptr.getWeight2(), ptr.getcanonevent2());
                if (newarray[finddimensionindex(ptr.getDimension2(), clusters.length * 2)] == null) {
                    newarray[finddimensionindex(ptr.getDimension2(), clusters.length * 2)] = test2;
                } else {
                    test2.setNextDimensionNode2(
                            newarray[finddimensionindex(test2.getDimension2(), clusters.length * 2)]);
                    newarray[finddimensionindex(ptr.getDimension2(), clusters.length * 2)] = test2;
                }
                ptr = ptr.getNextDimensionNode2();
            }
        }
        return newarray;
    }

    public int finddimensionindex(int dimension, int hashtablesize) {
        int index = dimension % hashtablesize;
        return index;

    }

    public static void main(String[] args) {

        if (args.length < 5) {
            StdOut.println(
                    "Execute: java -cp bin spiderman.GoHomeMachine <dimension INput file> <spiderverse INput file> <hub INput file> <anomalies INput file> <report OUTput file>");
            return;
        }
        StdIn.setFile(args[1]); // <- reads spiderverse.in and this makes arrayn for storing characters
        ArrayList<SpiderDimensionNode> chars = new ArrayList<>();
        ArrayList<Integer> visited = new ArrayList<>();
        GoHomeMachine test2 = new GoHomeMachine();
        StdIn.setFile(args[0]); // <- reads first file in json(dimensions.in)
        // call weighted adj list
        test2.createClusters(); // <- calls first method
        StdOut.setFile(args[4]); // <- sets output file to third file in json (collectanomalies.out)
        test2.adjlist(); // calls 2nd method and makes adj list
        test2.storechars(args[1], chars);// <- stores characters in previous array
        System.out.println(chars);
        StdIn.setFile(args[2]); // <- reads third file hub.in
        int hub = StdIn.readInt();
        // extracted all info and created adjacency list for BFS traversal
        ArrayList<SpiderDimensionNode> anomalies = test2.findAnomalys(chars);
        for (SpiderDimensionNode anomaly : anomalies) {
            SpiderDimensionNode guard = test2.checkForSpider(anomaly, chars);
            int dimension = anomaly.GetDimension();
            ArrayList<Integer> path = test2.BFS(hub, dimension);
            StdIn.setFile(args[0]);

            String collectAnomalyStr = test2.collectedAnomalyToString(anomaly, guard, path);
            // StdOut.println(collectAnomalyStr);
        }
        StdIn.setFile(args[2]); // <- reads third file hub.in
        int hub2 = StdIn.readInt();
        test2.MoveDimensions(chars, hub2);
        StdIn.setFile(args[3]);
        test2.dijkstra(hub2);

        // DJISTRKA ALGORITHM CALLED HERE

    }

}
