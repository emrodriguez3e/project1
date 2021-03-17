
public class Axis {

	String job;
	int priority;

	private double xAxis;
	private double yAxis;
	private double storage;
	private double data;
	private Boolean store;


	public Axis(){

	}

    public Axis(String job, int priority){
			this.job = job;
			this.priority = priority;
		}// end of constructor

	
	public double getxAxis() {
		return xAxis;
	}
	public void setxAxis(double xAxis) {
		this.xAxis = xAxis;
	}

	public double getyAxis() {
		return yAxis;
	}
	public void setyAxis(double yAxis) {
		this.yAxis = yAxis;
	}

	public double getStorage(){
		return storage;
	}
	public void setStorage(double storage){
		this.storage = storage;
	}

	public double getData(){
		return data;
	}
	public void setData(double data){
		this.data = data;
	}

	public boolean isStorageType(){return store;}
	public void setStorageType(Boolean set){this.store = set;}

	public String toString(){
		return "Job Name : "+ job +"\nPriority : "+ priority;
	}
}//end of file

