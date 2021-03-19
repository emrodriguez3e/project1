import java.util.*;

public class playground {
    //TODO create a working priority queue
    //TODO What is dijkstra's algorithm

    private Map<Integer, Axis> nodes = new LinkedHashMap<Integer, Axis>();
    Map<Integer, Boolean> discovered = new HashMap<Integer,Boolean>();
    Map<Integer, Boolean> explored = new HashMap<Integer, Boolean>();
    Map<Integer, Integer> parent = new HashMap<Integer, Integer>();

    public List dataNodesList = new ArrayList<>();
    public List storageNodesList = new ArrayList<>();


    double electric = Math.pow(100, -12);
    double amp = Math.pow(100, -9);
    double k = 3200;

    Stack<Integer> s = new Stack<Integer>();


    public static void main(String[] args){

        double width = 1000;
        double height = 1000;
        System.out.println("A " +width+"x"+height+" map has been generated");

        int numberOfNodes = 100;
        System.out.println(numberOfNodes + " has been generated");

        int transmissionRange = 250;
        System.out.println("Setting transmission range to "+transmissionRange+ " meters.");

        int dataNodes = 5;
        System.out.println(dataNodes +" nodes have been set to data nodes.");

        int dataPacket = 200;
        System.out.println("Tuning data output to" +dataPacket+" bits");

        int storage = 100;
        System.out.println("Storage node capacities have been set to store "+storage+" bits.\n");

        double allData = dataNodes * dataPacket;
        double allStorage = (numberOfNodes-dataNodes)*storage;

        if(allData > allStorage){
            System.out.println("There is not enough storage available to store all the data.\nPlease try again.");
            System.exit(1);
        }//feasability check

        playground play = new playground();
        play.populateNodes(numberOfNodes,width, height, dataNodes); //this will give the location of the nodes

        for(int key : play.nodes.keySet()){
            Axis ax = play.nodes.get(key);
            System.out.println("Node: "+key+", xAxis:"+ ax.getxAxis() + ", yAxis:"+ax.getyAxis());
        }

        Map<Integer, Set<Integer>> adjList = new LinkedHashMap();

        play.populateAdjacencyList(numberOfNodes, transmissionRange, adjList);


        for(int i: adjList.keySet()){
            System.out.print(i);
            if(!adjList.isEmpty()){
                for(int j: adjList.get(i)){
                    System.out.print("-> " + j);
                }
            }//end of if
            System.out.println();
        }//end of outer for loop

        play.executeStackDFS(width, height, adjList);

    }//end of main method

    void executeStackDFS(double width, double height, Map<Integer, Set<Integer>> adjList){
        System.out.println("\nExecuting DFS Stack Algorithm");
        List<Set<Integer>> connectedNodes = new ArrayList<Set<Integer>>();

        for(int node: adjList.keySet()){
            Set<Integer> connectedNode = new LinkedHashSet<Integer>();
            stackDFS(node, connectedNode, adjList);


            if(!connectedNode.isEmpty()){
                connectedNodes.add(connectedNode);
            }
        }//end of for loop

        if(connectedNodes.size() == 1) {
            System.out.println("Graph is fully connected with one connected component.");
        } else {
            System.out.println("Graph is not fully connected.\nPlease try again.");

        }

//        System.out.println("\nID's of Data nodes: ");//print out list with all DN
//        System.out.println(dataNodesList);
//        System.out.println("\nID's of storage nodes: ");//print out the list with all SN
//        System.out.println(storageNodesList);



        System.out.println("There are " + connectedNodes.size() + " connected components");
        for(Set<Integer> list: connectedNodes) {
            System.out.println(list);
        }
    }

    void stackDFS(int u, Set<Integer> connectedNode, Map<Integer, Set<Integer>> adjList){
        s.push(u);

        while(!s.isEmpty())
        {
            int v = s.pop();
            if(!discovered.containsKey(v))
            {
                connectedNode.add(v); //add here
                discovered.put(v, true);
            }

            List<Integer> list = new ArrayList<Integer>(adjList.get(v));
            for(int i : list)
            {
                if(!discovered.containsKey(i))
                {
                    s.push(i);
                }
            }
        }
    }

    void DijkstraAlgorithm(int u, Map<Integer, Set<Integer>> adjList) {
        int cost = 0;
        int count = 0;
        discovered.put(u, false);
        explored.put(u, false);

        //maybe change the list to a normal list instead of a list containing a set?
        //List<Set<Integer>> pathCost = new ArrayList<Set<Integer>>();
        List<Set<Integer>> connectedNodes = new ArrayList<Set<Integer>>();
        //create a node for every adjList
        for (int node : adjList.keySet()) {
            Set<Integer> connectedNode = new LinkedHashSet<Integer>();
            if (!connectedNode.isEmpty()) {
                connectedNodes.add(connectedNode);
            }
            for (int j : adjList.get(count)) {
                if (count == 0) {
                    cost = 0;
                    cost++;
                } else if (count != 0) {
                    cost = 1;
                    cost++;
                }
                if (connectedNode == parent) {
                    discovered.put(u, true);
                    explored.put(u, true);
                }
                if (connectedNode != parent) {
                    discovered.put(u, true);
                    explored.put(u, false);
                }
            }
        }
    }//end of dijkstra

    void populateNodes(int nodeCount, double width, double height, int dataNode) {
        Random random = new Random();

        Random nodeType = new Random();
        int dataNodeCount = 0;

        for(int i = 1; i <= nodeCount; i++) {
            Axis axis = new Axis();
            int scale = (int) Math.pow(10, 1);
            double xAxis =(0 + random.nextDouble() * (width - 0));
            double yAxis =(0 + random.nextDouble() * (height - 0));

            xAxis = (double)Math.floor(xAxis * scale) / scale;
            yAxis = (double)Math.floor(yAxis * scale) / scale;

            axis.setxAxis(xAxis);
            axis.setyAxis(yAxis);

            //set a certain amount of nodes to be data
            if((random.nextDouble()) > 0.75 && dataNodeCount < dataNode){
                axis.setPackets(3200);
                dataNodeCount++;
                dataNodesList.add(i);
            }else{
                axis.setCapacity(400);
                storageNodesList.add(i);
            }
            nodes.put(i, axis);
        }
    }//end of populate nodes

    void populateAdjacencyList(int nodeCount, int tr, Map<Integer, Set<Integer>> adjList) {
        for(int i=1; i<= nodeCount; i++) {
            adjList.put(i, new HashSet<Integer>());
        }

        for(int node1: nodes.keySet()) {
            Axis axis1 = nodes.get(node1);

            for(int node2: nodes.keySet()) {
                Axis axis2 = nodes.get(node2);

                if(node1 == node2) {
                    continue;
                }
                double xAxis1 = axis1.getxAxis();
                double yAxis1 = axis1.getyAxis();

                double xAxis2 = axis2.getxAxis();
                double yAxis2 = axis2.getyAxis();

                double distance =  Math.sqrt(((xAxis1-xAxis2)*(xAxis1-xAxis2)) + ((yAxis1-yAxis2)*(yAxis1-yAxis2)));


                //if a node is within range and has a data type

                    double edge = electric * k + amp * k * Math.pow(distance,2) + electric *k;

                    //place all nodes into the adj list
                    if(distance <= tr) {
                        Set<Integer> tempList = adjList.get(node1);
                        tempList.add(node2);
                        adjList.put(node1, tempList);

                        tempList = adjList.get(node2);
                        tempList.add(node1);
                        adjList.put(node2, tempList);

                }//end of distance
            }
        }
    }//end of adjList
}//end of file
