package rsa;
import java.math.BigInteger;
import java.util.Scanner;
import java.security.SecureRandom;

public class RSA {
	private final static BigInteger one      = new BigInteger("1");
	   private final static SecureRandom random = new SecureRandom();

	   private BigInteger privateKey;
	   private BigInteger publicKey;
	   private BigInteger modulus;

	   // generate an N-bit (roughly) public and private key
	   RSA(int N) {
		   
		      BigInteger p = new BigInteger("8226300218526427578904608583398443065691488851285857053707420239647980600720044648521159983497369940193924807016312926942965941829100238436520621194830502057679984311048901859684958163286269801735080727963463944945290287834821564034145405855084108116324533235715989734728965234314460156650900656780563");
		      BigInteger q = new BigInteger("1152574679987110855605944026722015849292700666320393004202728793244603273319049247166285597617666829774938557558250999526515429413099644164319913699521607551349950515792522695636562052967210129087319503010037376653099872048708732873490447854221516624205170828371900017753916468410494853998172895848339");

		   
	      //BigInteger p = new BigInteger("63697977222409713869437097304671093262295012529600069909066304723455697547013");
	      //BigInteger q = new BigInteger("79553522712930561741266391652589754305856384183282833774564298065898707372879");
	     
		   
		   
		   // BigInteger p = BigInteger.valueOf(139);
	      //BigInteger q = BigInteger.valueOf(119);
		   
		   BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));
	      
	      System.out.println("prime nos are p= "+p+"& q="+q);

	      modulus    = p.multiply(q);         
	      publicKey  = new BigInteger("65537");
	      //publicKey  = BigInteger.valueOf(65537);     // common value in practice = 2^16 + 1
	      privateKey = publicKey.modInverse(phi);
	   }


	   BigInteger encrypt(BigInteger message) {
	      return message.modPow(publicKey, modulus);
	   }

	   BigInteger decrypt(BigInteger encrypted) {
		/*   BigInteger i = new BigInteger("1");
		   BigInteger A = BigInteger.ONE;
		   while(i.compareTo(privateKey)== -1)
		   {
		   if(encrypted.modPow(i,modulus)== A)
		   {
			   privateKey = i;
			   break;
		   }
		   i=i.add(A);
		   }
		   System.out.println("new privatekey is"+privateKey);
	     */ return encrypted.modPow(privateKey, modulus);
	   }
	   
	   public static BigInteger toAscii(String s){
	        StringBuilder sb = new StringBuilder();
	        String ascString = null;
	        BigInteger asciiInt;
	                for (int i = 0; i < s.length(); i++){
	                    sb.append((int)s.charAt(i));
	                    char c = s.charAt(i);
	                }
	                ascString = sb.toString();
	                asciiInt = new BigInteger(ascString);
	                return asciiInt;
	    }


	   public String toString() {
	      String s = "";
	      s += "public  = " + publicKey  + "\n";
	      s += "private = " + privateKey + "\n";
	      s += "modulus = " + modulus;
	      return s;
	   }
	 
	   public static void main(String[] args) {
	      int N = 1000;
	      RSA key = new RSA(N);
	      System.out.println(key);
	 
	      // create random message, encrypt and decrypt
	      //BigInteger message = new BigInteger(N-1, random);
	      //// create message by converting string to integer
	      String s = "vedant vasishtha 1996vedant vasishtha 1996vedant vasishtha 1996";
	      BigInteger message = toAscii(s);
	      BigInteger encrypt = key.encrypt(message);
	      BigInteger decrypt = key.decrypt(encrypt);
	      System.out.println("message   = " + message);
	      System.out.println("encrypted = " + encrypt);
	      System.out.println("decrypted = " + decrypt);
	   }

}
