package pe.com.codecomparator.model.command.core.fdtw;

public class Node{ 
	//Items contained in FDTW - Path 
	public int q, c;  //index in Q and C Sequence 
	public boolean IsSub; 
	public int nsub; 
	
	public Node() {
		IsSub = false;
		nsub = -1;
	}//Default Constructor 
}
