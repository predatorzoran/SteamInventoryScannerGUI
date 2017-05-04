import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class proxyfileread {
	  @SuppressWarnings("resource")
		public int fileline(){
			  
			  File f = new File(System.getProperty("user.dir"),"proxy.txt");
			  if(!f.exists())
			  {
				  log l0g =new log();
				  l0g.writef("Proxy File does not Exist");
				  System.exit(0);
				  return 0;
			  }
			  int i=0;
			 
			  Scanner scanner;
			try {
				scanner = new Scanner(f);
				 
			} catch (FileNotFoundException e) {
				return 0;
			}
	    	  while(scanner.hasNextLine()){
	    		  if(!(scanner.nextLine().isEmpty()))
	    		     i++;						
			}
			
	    	 
			  return i;
			  
		  }
}
