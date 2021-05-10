package br.com.zup.zupayments.jwt;

import br.com.zup.zupayments.exceptions.TokenNotValidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class ComponenteJWT {

    private String segredo;

    private Long milisegundos;

    public String gerarToken(String username) {
        Date vencimento = new Date(System.currentTimeMillis()+ milisegundos);

        String token = Jwts.builder().setSubject(username).
                setExpiration(vencimento).signWith(SignatureAlgorithm.HS512,segredo.
                getBytes()).compact();

        return token;
    }

    public Claims getClaims(String token){
        try {
            Claims claims = Jwts.parser().setSigningKey(segredo.getBytes()).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception error) {
            throw new TokenNotValidException(error.getMessage());
        }
    }

    public boolean isTokenValid(String token){
        try {
            Claims claims = getClaims(token);
            String username = claims.getSubject();
            Date vencimento = claims.getExpiration();
            Date agora = new Date(System.currentTimeMillis());

            if (username != null && vencimento != null && agora.before(vencimento)){
                return true;
            }
            return false;
        }catch (TokenNotValidException error){
            return false;
        }
    }
}
