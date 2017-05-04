import java.util.ArrayList;

public class giftcouponobj {
     ArrayList <String> data;
     String id ;
     int identifier;
     public giftcouponobj(ArrayList <String> coll, String profileid , int idef){
    	 	 this.data =coll;
    	 	 this.id =profileid;
    	 	 this.identifier=idef;
    	 
     }
     
     public giftcouponobj(giftcouponobj obj){
	 	 this.data =obj.data;
	 	 this.id =obj.id;
	 	 this.identifier =obj.identifier;
	 
 }
   
     public ArrayList<String> getdata(){
		
    	  return data;
    }
     
     public String getid(){
 		
   	  return id;
   }
     public int identify(){
 		
   	  return identifier;
   }
     
     
}
