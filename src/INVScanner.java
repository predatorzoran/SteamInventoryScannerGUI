import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.xml.sax.SAXException;



 

public class INVScanner  {
	
	
	
	
	public  void search(int in,int po, int ch) throws InterruptedException, JSONException, URISyntaxException   {
        
		
		Random randomGen = new Random();
		ArrayList<String> profile = new ArrayList<String>();
		ArrayList<String> processed = new ArrayList<String>();
		ArrayList<String> netprofile = new ArrayList<String>();
		proccessfriends fl = new proccessfriends();
		log lg = new log();
		int input = 0;
		int proxy =0;
		int choice = 0 ;
		int profiles =0;
		String id=null;
		boolean flag = true;
		runonce once = new runonce();
		
		BlockingQueue<proxy> queue = new ArrayBlockingQueue<proxy>(1024); 
		BlockingQueue<String> queueid = new ArrayBlockingQueue<String>(100); 
		BlockingQueue<giftcouponobj> gcqueue = new ArrayBlockingQueue<giftcouponobj>(200);
		
		ArrayList<String> apikeylist = new ArrayList<String>();
		steamapi api = new steamapi(apikeylist);
		api.readsteamapikey();
		int randomkey ;
	//	System.out.println("Press 1 = Proxy || 2 = No proxy" );
		@SuppressWarnings("resource")
		Scanner reader3 = new Scanner(System.in);
		proxy =po; //reader3.nextInt();
		if(proxy==1){
			 
			int line= 0;
			proxyread read =new proxyread(queue);
			proxyfileread  linecount =new proxyfileread();
			line=linecount.fileline();
			if(line==0){
				lg.writef("Either no Proxy Server IP or Proxy File does not Exists");
				System.exit(0);
			}
			
				try{
						 		
					  read.read();
					  
				 }catch(NullPointerException e){
					 lg.writef("Proxy File Contains Malformed Proxy.. It Should represent as format Hostip:portno");
					 System.exit(0);
				 }
			//	System.out.println("Proxy Queue Length :"+queue.size());
			/*	for(int i=0;i<queue.size();i++){
					proxy pxy = new proxy(queue.take());
					pxy.read();
					queue.put(pxy);
				} */
			
		}
		else{
			
			proxy prx =new proxy(null,null);
			queue.add(prx);
			proxy = 0;
		}
	//	System.out.println("Press 1 = Continuous Search || 2 = Friendslist only Search || 3 = Group Search" );
        
        			
        			input =in; //reader3.nextInt();
        			
        	//		System.out.println("Press 1 = Search for gift only || 2 = Search for Coupon only || 3 = Search for Both   ");
        			choice =ch; //reader3.nextInt();
        				
        switch (input){
            case 1:  
            	
            	
            	
            			System.out.println("How Many Profile Do you Want to Search ");
            			profiles = reader3.nextInt();
            			System.out.println("Total no of Profiles :"+profiles);
            			try {
            				profile.add(once.search(0));
     
            			} catch (URISyntaxException | JSONException | IOException e1) {
				
            						lg.writef(e1.toString());
            				}
				
            			for (int i= 0 ;i<profiles;i++)
            			{
            				randomkey= randomGen.nextInt(apikeylist.size());
            				netprofile.add( profile.get(0));				
				
            				processed.add(profile.get(0));
            				profile.remove(0);
            			//	pool.getproxy(queue,choice,id,proxy);
            			if(profile.size() > profiles)
            					flag = false;
            			if(flag)
						try {
								profile=fl.processlist(id,profile,processed,apikeylist.get(randomkey));
							} catch (IOException | URISyntaxException | JSONException e) {
							
										lg.writef(e.toString());
							}
				
					
            			//	System.out.println("Profile Searched :"+processed.size()+" Profiles to Go :"+profile.size());
					
            				}
            			writegiftcoupon gifcou = new writegiftcoupon(gcqueue);
            			Thread t = new Thread(gifcou);                      //>>>>>>>>>>>>>>>
            			t.setDaemon(true);//success is here now             >>>>>>>>>>>>>>>>
            			t.start();                                    //>>>>>>>>>>>>>>>>>
            			
            			proxypoool pool=new proxypoool(netprofile,queueid);
            			new Thread(pool).start();
            			proxythread thread=new proxythread(queue,queueid,gcqueue,choice,netprofile.size());
            			new Thread(thread).start();
                     break;
            case 2:
            		int line =0;
            		int size;
            		File f = new File(System.getProperty("user.dir"),"input.txt");
            		Scanner scanner = null;
            		try {
            			scanner = new Scanner(f);
            			} catch (FileNotFoundException e) {
				
            				lg.writef(e.toString());
				
            			}	
            		while(scanner.hasNextLine()){
            			scanner.nextLine();
            		try {
						profile.add(once.search(line));
						} catch (URISyntaxException | JSONException | IOException e) {
						
								lg.writef(e.toString());
						}
            			id = profile.get(0);
            			processed.add(profile.get(0));
            			profile.remove(0);
            			 netprofile.add(id);   			
					   // pool.getproxy(queue,choice,id,proxy);
            		
					try {
						randomkey= randomGen.nextInt(apikeylist.size());
						profile=fl.processlist(id,profile,processed,apikeylist.get(randomkey));
						} catch (IOException | URISyntaxException | JSONException e) {
						
								lg.writef(e.toString());
						}
					     size=profile.size();
					     System.out.println("Total no of Profiles :"+size);
					for (int i=0;i<size;i++){
						id = profile.get(0);
	            		processed.add(profile.get(0));
						profile.remove(0);
						netprofile.add(id);
						//pool.getproxy(queue,choice,id,proxy);
					//	System.out.println("Profile Searched :"+processed.size()+" of link :"+(line+1)+" Profiles to Go :"+profile.size());
					
						}
								line++;
					
            			}
            		writegiftcoupon gifcou1 = new writegiftcoupon(gcqueue);
            		Thread t1 = new Thread(gifcou1);                      //>>>>>>>>>>>>>>>
        			t1.setDaemon(true);//success is here now             >>>>>>>>>>>>>>>>
        			t1.start();  
        	//		new Thread(gifcou1).start();
            		proxypoool pool1=new proxypoool(netprofile,queueid);
        			new Thread(pool1).start();
        			proxythread thread1=new proxythread(queue,queueid,gcqueue,choice,netprofile.size());
        			new Thread(thread1).start();
            		
            			break;
                     
            case 3 :
            			String gname = null;
            			fetchgroupname grname = new fetchgroupname();
            			int count;
            			processgroup grouplist = new processgroup();
            			try {
            				gname = grname.fetchname();
            				} catch (FileNotFoundException e2) {
				
            					lg.writef(e2.toString());
            				}
            			if(gname != null){
            			try {
            				profile = grouplist.processlist(gname);
            				} catch (URISyntaxException | IOException | ParserConfigurationException | SAXException e1) {
            					
            					lg.writef(e1.toString());
            				}
            			 count=profile.size();
            			 System.out.println("Total no of Profiles :"+count);
    					for (int i=0;i<count;i++){
    						id = profile.get(0);
    	            		processed.add(profile.get(0));
    						profile.remove(0);
    					//	pool.getproxy(queue,choice,id,proxy);
    					//	System.out.println("Profile Searched :"+processed.size()+"    Profiles to Go :"+profile.size());
    					
    						}
    					
    					writegiftcoupon gifcou2 = new writegiftcoupon(gcqueue);
    					Thread t2 = new Thread(gifcou2);                      //>>>>>>>>>>>>>>>
            			t2.setDaemon(true);//success is here now             >>>>>>>>>>>>>>>>
            			t2.start();  
            		//	new Thread(gifcou2).start();
    					proxypoool pool2=new proxypoool(netprofile,queueid);
            			new Thread(pool2).start();
            			proxythread thread2=new proxythread(queue,queueid,gcqueue,choice,netprofile.size());
            			new Thread(thread2).start();
            			}
            			else
            				{
            				System.out.println("Invalid Group Name");
            				lg.writef("Invalid Group Name");
            				}
            			
            			
                 break;
                     
                      
            default: 
            		System.out.println("your input is invalid!");
            		lg.writef("your input is invalid!");
            		break;
        }
        
    }
   
}



