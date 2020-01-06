package utils;

import play.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {

    private static String toHexString(byte[] bytes){
        StringBuilder builder = new StringBuilder();

        for (byte b: bytes){
            builder.append(Integer.toHexString(b & 0xff));
        }
        return builder.toString();
    }
    public static String md5Hash(String clear){
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            byte[] actionIdBytes = clear.getBytes();
            byte[] actionIdHashBytes = digester.digest(actionIdBytes);
            return toHexString(actionIdHashBytes);
        } catch (NoSuchAlgorithmException e){
            Logger.debug(e.getMessage());
            return "";
        }
    }
}
