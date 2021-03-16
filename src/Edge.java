import java.math.*;

public class Edge {

    double distance = 0;
    double electric = Math.pow(100, -12);
    double amp = Math.pow(100, -9);

    //needs two nodes
    public Edge(Axis node1, Axis node2){

        boolean node1Status = node1.isStorageType();
        boolean node2Status = node2.isStorageType();

        if(node1Status == false && node2Status == false){
            //this is where they are both data nodes

        } else if (node1Status == true && node2Status == true){
            //this is where they are both storage nodes
        }else{
            //get their distance
//            double distance =  Math.sqrt(((xAxis1-xAxis2)*(xAxis1-xAxis2)) + ((yAxis1-yAxis2)*(yAxis1-yAxis2)));
            double distance = Math.sqrt(((node2.getxAxis()-node1.getxAxis()) + (node2.getyAxis() - node2.getyAxis())));
            distance = this.distance;
        }
    }

    void tranmission(){
        double transmission = electric * k + amp * k * Math.pow(distance);
    }

    void receiver(){
        double reciever = electric * k;
    }
}
