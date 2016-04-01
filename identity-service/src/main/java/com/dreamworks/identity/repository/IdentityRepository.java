package com.dreamworks.identity.repository;

import com.dreamworks.identity.model.Identity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Created by mmonti on 3/30/16.
 */
public interface IdentityRepository extends PagingAndSortingRepository<Identity, String> {

    /**
     *
     * @param emailAddress
     * @return
     */
    Optional<Identity> findOneByEmailAddress(String emailAddress);

}
