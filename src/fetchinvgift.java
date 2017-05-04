
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;



public class fetchinvgift {
	public giftcouponobj fetch(String profileid,String proxy , String port) throws URISyntaxException, MalformedURLException, InterruptedException, JSONException  {
		log lg = new log();
		ArrayList<String> coll = new ArrayList<String>();
		boolean cflag = true;     // connection checking flag
		boolean jflag =true;	// JSon object checking flag
		int counter=0;
		giftcouponobj obj = null;
		String urlgift ="http://steamcommunity.com/profiles/"+profileid+"/inventory/json/753/1";	
		
		if (proxy!=null&&port!=null){
			System.setProperty("https.proxyHost", proxy);
			System.setProperty("https.proxyPort", port);
			lg.writef(" Proxy Set :"+proxy+":"+port);
		}
		
		URI uri = new URI(urlgift);
		JSONTokener tokener = null;
		do{
							
		
			try {
				tokener = new JSONTokener(uri.toURL().openStream());
				} catch (IOException |JSONException e) {
					 
					 counter++;
					 lg.writef(" Proxy Slept for  :"+(counter)+" time(s) "+proxy+":"+port+" of ID :"+profileid);
					//TimeUnit.MINUTES.sleep(3);
					 Thread.sleep(20000*counter);
				} 
			if(counter==3){
		    	cflag=false;
		    	break;
		    }
		    	
		     
		}while(tokener==null);
		JSONObject json = null;
		if(tokener!=null){
		try {
			json = new JSONObject(tokener);
		} catch (JSONException e) {
			jflag =false;
		}
		}
		
		
		boolean flag = true;
		boolean success = false;
		try {
			if(json!=null&&json.length()!=0)
				success = json.getBoolean("success");
			if (json!=null&&json.length()==0)
				cflag=false;
		} catch (JSONException e) {
			jflag =false;
		}
		
		
		if (success&&jflag==true){
		
		Object rgDescriptions = json.get("rgDescriptions");
		
		if (rgDescriptions instanceof JSONArray) {
			
				
			flag =false;
		    
		}
		else if (rgDescriptions instanceof JSONObject) {
		
		
			JSONObject rginv = json.getJSONObject("rgDescriptions");
			Iterator<?> iterator = rginv.keys();
					String key = null;
				 do {
					  key = (String) iterator.next();
					  JSONObject tag = rginv.getJSONObject(key);
					  String name =  tag.getString("name");
					  int tradable = tag.getInt("tradable");
					  
					  if (tradable!= 0)
					  	   coll.add(name +"               tradable");
					  else
						  coll.add(name +"                Not-tradable");
					  
				    } while (iterator.hasNext());
				 
		}
		
		}
		
	    
		if(flag  && success&&jflag && cflag){ 
			 obj= new giftcouponobj(coll,profileid,1);
			
		}
		else if(cflag==false){
			obj= new giftcouponobj(null,profileid,4);
		
		}
		else if(jflag==false)
		{
			obj= new giftcouponobj(null,profileid,3);
		
		}
		
		else if(success == false){
			obj= new giftcouponobj(null,profileid,5);
		}
		else if(flag==false)
		{
			obj= new giftcouponobj(null,profileid,3);
		
		}
		if(jflag==false||cflag==false){
			lg.writef("This profile is making unusual behaviour :"+urlgift);
		}
		
		return obj;
		

	}
}
	
