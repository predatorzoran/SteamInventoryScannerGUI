
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


public class fetchinvcoupon {
	
		public giftcouponobj fetch(String profileid,String proxy , String port) throws URISyntaxException, MalformedURLException,  InterruptedException, JSONException {
			log lg = new log();
			ArrayList<String> coll = new ArrayList<String>();
			long unixTime = System.currentTimeMillis() ;
			boolean cflag = true;     // connection checking flag
			boolean jflag =true;	// JSon object checking flag
			int counter = 0;
			giftcouponobj obj = null;
			String urlcoupon ="http://steamcommunity.com/profiles/"+profileid+"/inventory/json/753/3";	
			
			if (proxy!=null&&port!=null){
				System.setProperty("https.proxyHost", proxy);
				System.setProperty("https.proxyPort", port);
				lg.writef(" Proxy Set :"+proxy+":"+port);
			}
			
			
			URI uri = new URI(urlcoupon);
			JSONTokener tokener = null;
			do{
				
				 
			try {
				
				tokener = new JSONTokener(uri.toURL().openStream());
				} catch (IOException  |JSONException e) {
					 counter++;
				//	System.out.println("Proxy Slept :"+proxy+":"+port); //>>>>>>>>>>>>>>>>>
					 lg.writef(" Proxy Slept for  :"+counter+" time(s) "+proxy+":"+port+" of ID :"+profileid);
					 Thread.sleep(20000*counter);             //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
				}
			
			    if(counter==3){
			    	cflag=false;
			    //	System.out.println(counter);   //<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			    	break;
			    }
			    	
			    
			}while(tokener==null);
		//	System.out.println("cflag :"+cflag+" jflag :"+jflag+" tokener "+tokener); //>>>>>>>>>>>>>>>>>>>>>
			JSONObject json = null;
			if(tokener!=null){
			try {
				json = new JSONObject(tokener);
			} catch (JSONException e) {
				jflag =false;
			}
		}
			
			
			boolean flag = true;
			boolean datechk = false;
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
						
							  checkdate ts = new checkdate();
							  datechk = ts.fetchd(tag,unixTime);
							  if(datechk )
							   coll.add(name);
						
						    } while (iterator.hasNext());
						 
					 
					
				}
			    
			}
				
	
			    
			if(flag && success&&coll.size()!=0&& jflag && cflag) {
				 obj = new giftcouponobj(coll,profileid,2);
			//	 System.out.println("Checked :"+obj.getid());
				
			}
			else if(cflag==false){
				obj = new giftcouponobj(null,profileid,4);      //????????????????????????????
			//	System.out.println("Checked :"+obj.getid());
			
			}
			else if(jflag==false)
			{
				obj = new giftcouponobj(null,profileid,3);
			//	System.out.println("Checked :"+obj.getid());
				
			}
			
			else if(success==false){
				obj = new giftcouponobj(null,profileid,5);
			}
			else if(flag==false||coll.size()==0)
			{
				obj = new giftcouponobj(null,profileid,3);
			//	System.out.println("Checked :"+obj.getid());
				
			}
			if(cflag==false||jflag==false){
				
				lg.writef("This profile is making unusual behaviour :"+urlcoupon);
				//	System.out.println("Checked :"+obj.getid());
			
			}
			if(obj==null)
			System.out.println("http://steamcommunity.com/profiles/"+profileid); ///>>>>>>>>>>>>>>>>>>>>>
			
			return obj;
			
			

		}
		
	
}
	

