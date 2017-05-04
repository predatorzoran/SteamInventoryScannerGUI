import java.io.*;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
	 
class proxy
	
{
	   String proxy;
	   String port;
	  
	   
	   
   
      
	    public void assign(String first, String last){
	       this.proxy = first;
	       this.port = last;
	       
	       
	  }
	    proxy(proxy p){
	    	this.proxy =p.proxy;
	    	this.port =p.port;
	    	
	    	
	    }
	    proxy(){
	    	
	    }
	    proxy (String proxy , String port ){
	    	this.proxy =proxy;
	    	this.port=port;
	    }
	    public void read(){
	    	System.out.println(proxy+":"+port);
	    }
	    public String proxyread(){
			return proxy;
	    	
	    }
	    public String portread(){
			return port;
	    	
	    }
	    

}	  
class proxyread  {
	   
	private BlockingQueue<proxy> queue;
	
		  public proxyread(BlockingQueue<proxy> q){
			  this.queue=q;
		  }

		
		public void read() {
			
			  {
				  proxyfileread pr = new proxyfileread();
				  proxychecker chk = new proxychecker();
				  log l0g= new log();
				 
				  int i = 0;
				  int j = 0;
				if(pr.fileline()!=0){
					  i = pr.fileline();
				
				 
				 proxy[] proxyobj= new proxy [i];
				 
			   try{
				   File f = new File(System.getProperty("user.dir"),"proxy.txt");
					  if(!f.exists())
					  {
						  log l0g1 =new log();
						  l0g1.writef("Proxy File does not Exist");
						  System.exit(0);
					  }
				  
				          Scanner scan = null;
						try {
							scan = new Scanner(f);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						  String strLine;
						  while ( scan.hasNextLine()&&i>=0)   {
							  strLine = scan.nextLine();
							  if(!strLine.isEmpty()){
							  strLine= strLine.replaceAll("\\s","");
							  String[] tokens = strLine.split(":");
							  
							  proxyobj[j]=new proxy();
							  proxyobj[j].assign(tokens[0],tokens[1]);//process record , etc
							  chk.assign(proxyobj[j]);
							  if(chk.checkproxies()){
								  queue.put(proxyobj[j]);
								  l0g.writef("Proxy Added to QUEUE :"+proxyobj[j].proxyread()+":"+proxyobj[j].portread());
							  }
							  else
								  l0g.writef("Proxy Not Working :"+proxyobj[j].proxyread()+":"+proxyobj[j].portread());
							 //  System.out.print("Proxy Assigned :");proxyobj[j].read();
							  j++;
							  }
							  i--;
							  
			          }
			   } catch(Exception e){
			       System.err.println("Error: " + e.getMessage());
			    }
			  
			 }
				
			}
			
		}
}

