package com.dreamworks.identity.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by mmonti on 3/30/16.
 */
@Entity
@Table(name = "identities", uniqueConstraints = {
        @UniqueConstraint(name = "uk_identities", columnNames = {"email_address"})
})
@Data
@EqualsAndHashCode(callSuper = false, of = {"id", "emailAddress"})
public class Identity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "revoked")
    private Boolean revoked;

    public Identity() {}

}
