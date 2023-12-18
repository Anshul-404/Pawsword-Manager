package springmvc.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class utility {
	
    public static String generate_Hash(String password){
        String myhash=null;
        try{
            MessageDigest md=MessageDigest.getInstance("MD5");
            
            md.reset();
            md.update(password.getBytes());
            byte[]digest=md.digest();
            
            BigInteger bigint = new BigInteger(1,digest);
            myhash = bigint.toString(16);
            while(myhash.length()<32)
                myhash="0"+myhash;            
        }
       catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
       }
            return myhash.toUpperCase();
    }
	
}
