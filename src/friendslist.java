import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class friendslist {
	public ArrayList<String> getlist(String id , String apikey) throws URISyntaxException, MalformedURLException, JSONException, IOException{
		
	    try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<String> coll = new ArrayList<String>();
	//	System.out.println(id+" "+apikey);
		String url = "http://api.steampowered.com/ISteamUser/GetFriendList/v0001/?key="+apikey+"&steamid="+id+"&relationship=friend";
		URI uri = new URI(url);
		try{
		JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
		JSONObject json = new JSONObject(tokener);
		 
	
		JSONObject friendlist = json.getJSONObject("friendslist");
		JSONArray friends = friendlist.getJSONArray("friends");
		for(int i=0 ;!(friends.isNull(i));i++){
			JSONObject steamid = friends.getJSONObject(i);
			String steamid64 =	steamid.getString("steamid");
			coll.add(steamid64);
			
		}	
		
	}	catch (Exception e) {
				
			}
		 
		return coll;
	}
		
}
		

	
	 