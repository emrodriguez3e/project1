/** Class PriorityQueue **/
class PriorityQueue {
    private Axis[] heap;
    private int heapSize, capacity;

    /** Constructor **/
    public PriorityQueue(int capacity){
        this.capacity = capacity + 1;
        heap = new Axis[this.capacity];
        heapSize = 0;
    }//end of constructor
    //TODO This should accept the number of nodes


    /** function to clear **/
    public void clear(){
        heap = new Axis[capacity];
        heapSize = 0;
    }
    //TODO This method should probably be deleted

    public boolean isFull(){
        return heapSize == capacity - 1;
    }//end of full method
    //TODO This method should probably be deleted


    public void insert(String job, int priority) {
        Axis newJob = new Axis(job, priority);
        heap[++heapSize] = newJob;

        int pos = heapSize;

        while (pos != 1 && newJob.priority > heap[pos/2].priority) {
            heap[pos] = heap[pos/2];
            pos /=2;
        }
        heap[pos] = newJob;
    }//end of insert
    //TODO: I want to say selection sort is being performed here but I don't know

    /** function to remove task **/
    public Axis remove() {
        int parent, child;
        Axis item, temp;
        if (isEmpty() ) {
            System.out.println("Heap is empty");
            return null;
        }
        item = heap[1];
        temp = heap[heapSize--];
        parent = 1;
        child = 2;

        while (child <= heapSize){
            if (child < heapSize && heap[child].priority < heap[child + 1].priority)
                child++;

            if (temp.priority >= heap[child].priority)

                break;
            heap[parent] = heap[child];
            parent = child;
            child *= 2;
        }//end of while loop
        heap[parent] = temp;
        return item;
    }//end of remove
    //TODO: Is remove doing a min or max?

    /** function to get Size **/
    public int size(){
        return heap.length;
    }//end of size method

    /** function to check if empty **/
    public boolean isEmpty(){
        return heapSize == 0;
    }

}