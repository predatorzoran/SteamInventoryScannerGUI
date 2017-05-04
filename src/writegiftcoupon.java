
/// Set it as Daemon

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class writegiftcoupon implements Runnable {
    
     private BlockingQueue <giftcouponobj> giftcoupon;
      public writegiftcoupon(BlockingQueue <giftcouponobj> goc  ){
    	    this.giftcoupon=goc;
      }
	
	public void run() {
				log lg = new log();
				while(true){
				if(giftcoupon.size()!=0){	
					File file = null;
					boolean flag=true;
					String profileid = null ;
				    int identifier =0;
					ArrayList<String> coll = new ArrayList<String>();
					
					try {
						giftcouponobj take= new giftcouponobj(giftcoupon.take());
						coll = take.getdata();
						profileid =take.getid();
						identifier = take.identify();
					} catch (InterruptedException e1) {
						flag = false;
						e1.printStackTrace();
					}
					if(flag){
				try {

					String content = "http://steamcommunity.com/profiles/"+profileid;
					switch (identifier){
					case 1:
						file = new File(System.getProperty("user.dir"),"gift.txt");
						lg.writef(profileid+": This Profile went to gift text");
						break;
					case 2:
						file = new File(System.getProperty("user.dir"),"coupon.txt");
						lg.writef(profileid+": This Profile went to coupon text");
						break;
					case 3:
						file = new File(System.getProperty("user.dir"),"skippeduselessprofile.txt");
						lg.writef(profileid+": This Profile went to useless text");
						break;
					case 5:
						file = new File(System.getProperty("user.dir"),"skippedforprivateprofile.txt");
						lg.writef(profileid+": This Profile went to privateprofile text");
						break;
					
					
					}
					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}

					FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(content);
					bw.write("--------------------------------------------------------");
					bw.newLine();
					bw.newLine();
					if(identifier ==1 || identifier ==2)
					for(int i=0 ;i<coll.size();i++){
						bw.write(coll.get(i));
						bw.newLine();
					}
					bw.newLine();
					bw.newLine();
				
					bw.close();

					

				} catch (IOException e) {
					e.printStackTrace();
				}
				}
					else
						System.out.println("Write Failed for this id : "+profileid);
				}
			
			}
		

	}
	
}

