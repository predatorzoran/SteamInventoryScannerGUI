import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


 public class proxychecker {
		
		String host;
		String port;
	          
	    public void assign(proxy pro){
	        port=pro.portread();
	        host=pro.proxyread(); 
	    }

	    
	               
	    public boolean checkproxies() throws InterruptedException {
	    	//Set the http proxy to webcache.mydomain.com:8080
	    	log l0g =new log();
	    	System.setProperty("https.proxyHost", host);
	    	System.setProperty("https.proxyPort", port);

	    	// Next connection will be through proxy.
	    	URL url = null;
	    	URLConnection connection = null;
	    	try {
				url = new URL("http://steamcommunity.com/");
	    		
				
			} catch (MalformedURLException e1) {
				
				l0g.writef(e1.toString()+ "for this Proxy :"+host+":"+port);;
			}
			
	    	try {
	    		
			 connection=url.openConnection();  
			 connection.setConnectTimeout(4000);
             connection.setReadTimeout(4000);          //     url.openStream();
          //   System.out.println(connection.getContent());
			} catch (IOException e ) {
				l0g.writef(e.toString()+"for this Proxy :"+host+":"+port);;
			
							
				return false;
			}
  	    
	   		
			return true;
	    	}    
	  
	} 

