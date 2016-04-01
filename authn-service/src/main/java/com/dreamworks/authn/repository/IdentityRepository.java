package com.dreamworks.authn.repository;

import com.dreamworks.authn.model.Identity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by mmonti on 3/30/16.
 */
@FeignClient(name = "identity-service")
public interface IdentityRepository {

    @RequestMapping(value = "/identity", method = RequestMethod.GET)
    Identity identityByEmail(@RequestParam(value = "email") final String email);
}
