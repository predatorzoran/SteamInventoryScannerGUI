import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
	 
class steamapi
	
{
	ArrayList<String> api = new ArrayList<String>();
	steamapi(ArrayList<String> ap){
		this.api=ap;
	}

		  public void  readsteamapikey() {
		  	 
		try{
		   File f = new File(System.getProperty("user.dir"),"steamapi.txt");
			  if(!f.exists())
			  {
				  log l0g =new log();
				  l0g.writef("SteamApi File does not Exist");
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
				  
				while ( scan.hasNextLine())   {
					
					  strLine = scan.nextLine();
					  if(!strLine.isEmpty()){
					  strLine= strLine.replaceAll("\\s","");
					  api.add(strLine);
					  }
	          }
	   } catch(Exception e){
	      // System.err.println("Error: " + e.getMessage());
	    }
	
	 }
		
	}


