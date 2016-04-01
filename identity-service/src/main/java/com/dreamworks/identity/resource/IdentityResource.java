package com.dreamworks.identity.resource;

import com.dreamworks.identity.model.Identity;
import com.dreamworks.identity.repository.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by mmonti on 3/30/16.
 */
@RefreshScope
@RestController
public class IdentityResource {

    @Autowired
    private IdentityRepository identityRepository;

    @RequestMapping(value = "/identity", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity identity(@RequestParam(value = "email") final String emailAddress) {
        Optional<Identity> identity = identityRepository.findOneByEmailAddress(emailAddress);
        if (identity.isPresent()) {
            return ResponseEntity.ok(identity.get());
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/identities", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Page<Identity>> identities(final Pageable pageable) {
        return ResponseEntity.ok(identityRepository.findAll(pageable));
    }

    @Value("${message:propertynotset}")
    private String message;

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ResponseEntity message() {
        return ResponseEntity.ok(message);
    }
}
