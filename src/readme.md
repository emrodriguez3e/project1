Programming Objective: When executed, your program should prompt to ask

1. the width x and length y of the sensor network (e.x., 50meter x 50meter)

2. number of sensor nodes: N

3. transmission range in meters: Tr

4. number of DNs: p, and number of data packets each DN has: q. Here we assume each DN has the same number of data packets.

5. Storage capacity of each storage node: m. Here we assume each storage node has the same storage capacity

First, your program still needs to check if the sensor network graph is connected (i.e.. connectivity). 
If not, it prints out a message “the network is not connected”, and asks the user to input again.

Second, your program should check if the user inputs satisfy: p  q <= (N-p)  m (i.e., feasibility). 
That is, there should have enough storage spaces in the entire network to store all the data packets generated. 
If not, it prints out a message “there is not enough storage in the network’, and asks the user to input again


Note: You can’t use the priority queue/heap libraries that are commonly available in all the programming languages.
You must create your own data structure (i.e., an array storing the heap), 
create your own priority queue, and implement all the required operations of priority queue. 
I will take a close look of your codes to check this.

If you use any of the available libraries, at most 50 points out of 100 points will be given.

Extra Credit:

1. Implement the Prim’s algorithm to find the minimum spanning tree (30 points)

If you implement both shortest path and minimum spanning tree, 
please ask the user to make choice when executing your program: 1 for shortest path; 2 for minimum spanning tree.

2. Visualize the shortest path and minimum spanning tree (20 points)