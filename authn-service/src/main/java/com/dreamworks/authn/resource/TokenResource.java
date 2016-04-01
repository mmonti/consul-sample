package com.dreamworks.authn.resource;

import com.dreamworks.authn.exception.BadRequestException;
import com.dreamworks.authn.model.Identity;
import com.dreamworks.authn.repository.IdentityRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by mmonti on 3/30/16.
 */
@RestController
@RefreshScope
public class TokenResource {

    private static final String X_NOVA_TOKEN = "X-NOVA-TOKEN";

    private IdentityRepository identityRepository;

    @Autowired
    public TokenResource(IdentityRepository identityRepository) {
        this.identityRepository = identityRepository;
    }

    /**
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public ResponseEntity token(@RequestParam(value = "email") final String email) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(1, Calendar.DATE);

        final Identity identity = identityRepository.identityByEmail(email);

        final Key key = MacProvider.generateKey();
        return ResponseEntity.ok(new HashMap(){{
           put(X_NOVA_TOKEN, Jwts
                   .builder()
                   .setSubject(identity.getEmailAddress())
                   .setExpiration(calendar.getTime())
                   .signWith(SignatureAlgorithm.HS512, key)
                   .compact());
        }});
    }

    @Value("${message}")
    private String message;

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ResponseEntity message() {
        return ResponseEntity.ok(message);
    }
}
