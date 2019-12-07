package il.george_nika.phrase2.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    private final static String ISSUER = "il.george_nika";
    private final static String NAME_IN_TOKEN = "name";
    private final static String IS_ADMIN_IN_TOKEN = "isAdmin";

    // The secret key. This should be in a property file NOT under source
    // control and not hard coded in real life. We're putting it here for
    // simplicity.
    private final static String SECRET_KEY = "jJkjkEkUEFUHukfgjyJHvbfleygf";


    public String createJWT(String name, Boolean isAdmin){

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String,Object> claims = new HashMap<>();
        claims.put(NAME_IN_TOKEN, name);
        claims.put(IS_ADMIN_IN_TOKEN, isAdmin);


        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuer(ISSUER)
            .setIssuedAt(now)
            .setClaims(claims)
            .signWith(signatureAlgorithm, signingKey);


        return jwtBuilder.compact();
    }
}
