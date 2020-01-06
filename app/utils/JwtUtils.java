package utils;

import io.jsonwebtoken.*;
import play.libs.Json;

import javax.xml.bind.DatatypeConverter;
import java.util.HashMap;

public class JwtUtils {
    public static String generateJWT(final Object object){
        String result = "";
        try {
            result = Jwts.builder().setClaims(new HashMap<String, Object>(){
                {
                    put("json", Json.toJson(object));
                }
            }).signWith(SignatureAlgorithm.HS256,"secret").compact();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean validateJWT(String jwt){
        boolean result = false;
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter
                    .parseBase64Binary("secret"))
                    .parseClaimsJws(jwt).getBody();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return result;
    }
}
