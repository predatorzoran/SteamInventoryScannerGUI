import java.util.concurrent.BlockingQueue;

public class proxythread implements Runnable {
	private BlockingQueue<proxy> queue;
	private BlockingQueue<String> queueid;
	private BlockingQueue<giftcouponobj> giftcouponwrite;
	int choice;
	int size;
	
	
	public proxythread(BlockingQueue<proxy> q,BlockingQueue<String> qid,BlockingQueue<giftcouponobj> gc,int choice,int sz){
		this.queue=q;
        this.choice =choice;
        this.queueid=qid;
        this.giftcouponwrite=gc;
        this.size=sz;
        
	}
    public void run( ) {
    	proxy prx = new proxy();
    	log lg = new log();
    	
    	while(queueid.size()!=0){
    		if(queue.size()!= 0){
    			String id = null;
    			try {
					prx=queue.take();
			//	lg.writef("Taken :"+prx.proxyread()+":"+prx.portread()); //>>>>>>>>>>>>>>>>>
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    			try {
    			//	System.out.println("Proxy Queue Size :"+ queue.size()+"Profile Blocking Queue Size"+queueid.size()); ///>>>>>>>>>>>>>>>
					id =queueid.take();
				//	System.out.println(id); //>>>>>>>>>>>>>>>>>>>>>>
					lg.writef("Taken  :"+prx.proxyread()+":"+prx.portread()+"by :"+id); //>>>>>>>>>>>>>>>>>>>>>>>>>
					lg.writef("Remaining proxy Queue Size :"+queue.size()); //>>>>>>>>>>>>>>>>>>
				} catch (InterruptedException e) {
					 e.printStackTrace();
				}
    			if(id!=null){
    			searchpattern sp = new searchpattern( queue,giftcouponwrite,queueid, choice, id  , prx.proxyread(), prx.portread(),size);
    	    	new Thread(sp).start();
    			}
    			//System.out.println(id);
    		}
    		else{
    			try {
					Thread.sleep(1000);
				//	System.out.println("Executed");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    		
    			}
    	//	System.out.println("Proxy Queue Size :"+queue.size()); //>>>>>>>>>>>>>>>>>>>>>>
    	}
        
		
    }

}