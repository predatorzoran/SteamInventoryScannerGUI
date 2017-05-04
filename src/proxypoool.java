import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class proxypoool implements Runnable {
	
	private BlockingQueue<String> queueid;
	private ArrayList<String> profiles;
	
	public proxypoool ( ArrayList<String> pro,BlockingQueue<String> qid){
		
		this.profiles=pro;
		this.queueid=qid;
		
	}
	public void run()  {
		
		while(profiles.size()!=0){
			try {
				queueid.put(profiles.get(0));
				
				profiles.remove(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	}
	
}	

