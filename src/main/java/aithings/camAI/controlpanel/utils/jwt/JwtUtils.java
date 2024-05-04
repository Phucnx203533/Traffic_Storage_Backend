package aithings.camAI.controlpanel.utils.jwt;

import aithings.camAI.controlpanel.entity.DeviceProcessEntity;
import aithings.camAI.controlpanel.repository.DeviceProcessRepository;
import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.print.DocFlavor;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class JwtUtils {
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpiration}")
    private Integer jwtExpiration;

    private static final Gson gson = new Gson();
    private final DeviceProcessRepository deviceProcessRepository;

    public JwtUtils(DeviceProcessRepository deviceProcessRepository) {
        this.deviceProcessRepository = deviceProcessRepository;
    }

    public String generateJwtToken(Map<String, Object> claims, String subject){
        String jwtToken = Jwts.builder()
                .addClaims(claims)
                .setSubject(subject)
                .setExpiration(new Date(new Date().getTime()+jwtExpiration))
                .signWith(key())
                .compact();
        return jwtToken;
    }
    public String getSerialFromToken(String jwtToken){
        String serial = gson.fromJson((Jwts.parserBuilder().setSigningKey(key()).build()).parse(jwtToken).getBody().toString(), DeviceProcessEntity.class).getSerial();
        return serial;
    }


    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
    public boolean validateJwtToken(String authToken) {
        try{
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
    public boolean isTokenValidDeviceProcess(String token){
        final String serial = getSerialFromToken(token);
        return (validateJwtToken(token) && deviceProcessRepository.findBySerial(serial)!=null);
    }
}
