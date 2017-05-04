import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.concurrent.BlockingQueue;

import org.json.JSONException;
//producer process
public class searchpattern implements Runnable {
	private BlockingQueue<proxy> queue;
	private BlockingQueue<giftcouponobj> gcobj;
	private BlockingQueue<String> queueid;
	int choice;
	String proxy;
	String port;
	String id;
	int size;
	
	static int counter =0;
	public searchpattern(BlockingQueue<proxy> q,BlockingQueue<giftcouponobj> gc,BlockingQueue<String> qid,int choice,String id,String poxy , String prt,int sz){
        this.queue=q;
        this.proxy =poxy;
        this.choice =choice;
        this.port=prt;
        this.id=id;
        this.gcobj=gc;
        this.queueid=qid;
        this.size=sz;
        
	}
	public void run( ) {
		
	 
		 
		//System.out.println(id);
		   // Thread thread = Thread.currentThread();
			//System.out.println("Thread Started :"+thread.getId());
			fetchinvgift gift = new fetchinvgift();
			fetchinvcoupon coupon = new fetchinvcoupon();
			boolean marker=false;
			log lg = new log();
			
			 switch (choice){
			 case  1:
			 			 
				 try {
					 giftcouponobj writer= new giftcouponobj(gift.fetch(id,proxy,port));
					 if(writer.identify()==4){
						 marker =true;
					 }
					 else
					 gcobj.put(writer);
				} catch (MalformedURLException | URISyntaxException | InterruptedException | JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 case 2 :
				 
				 try {
					 giftcouponobj	 writer1= new giftcouponobj(coupon.fetch(id,proxy,port)); //resolve in fetchhgiftinv or fetchcouponinv
					 if(writer1.identify()==4){
						 marker =true;
					 }
					 else
					 gcobj.put(writer1);
					
				} catch (MalformedURLException | URISyntaxException | InterruptedException | JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			 
			 case 3 :
			
				 try {
					 giftcouponobj writer2= new giftcouponobj(gift.fetch(id,proxy,port));
					 if(writer2.identify()==4){
						 marker =true;
						 break;
					 }
					 else
					 gcobj.put(writer2);
									
				} catch (MalformedURLException | URISyntaxException | InterruptedException | JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			
				 try {
					 giftcouponobj	 writer3= new giftcouponobj(coupon.fetch(id,proxy,port));
					 gcobj.put(writer3);
					
					} catch (MalformedURLException | URISyntaxException | InterruptedException | JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 break;
			
				
			 
		}
			
			
			 proxy prx = new proxy(proxy,port);
			 
			 try {
				 
				queue.put(prx);
			//	System.out.println("Returned :"+prx.proxyread()+":"+prx.portread()); //>>>>>>>>>>>>>>>>
				lg.writef("Returned :"+prx.proxyread()+":"+prx.portread());
			//	System.out.println("Added"+proxy+port);				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(marker){
				 try {
					 lg.writef(id+" :this Profile Search Failed by "+prx.proxyread()+":"+prx.portread()); //>>>>>>>>>
					queueid.put(id);
					lg.writef(id+" :this Profile Added to IDQueue Again "); //>>>>>>>>>>
				} catch (InterruptedException e) {
							e.printStackTrace();
				}
				 
			 }
			 
		/*    try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
			if(!marker){
				counter++;	
				System.out.println("Total Profiles Completed :"+counter );
				lg.writef("Total Profiles Completed :"+counter ); //>>>>>>>>>>>>>>>>>>
							
			}
			
			if(counter == size){
				try {
					lg.writef("Total Profile Searched Complete .. It will Sleep now"); //>>>>>>>>>>>>>>>>>>>
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lg.writef("Now IT Will Exit"); //>>>>>>>>>>>>>>>>
				System.exit(0);
			}
		}
	
	
	
}

