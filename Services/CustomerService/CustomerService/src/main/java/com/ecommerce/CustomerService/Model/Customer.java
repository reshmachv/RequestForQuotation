package com.ecommerce.CustomerService.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customerId;
    private String Fname;
    private String Lname;
    @Column(unique = true)
    private String email;
    private String password;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "Company", column = @Column(name = "CompanyName")),
            @AttributeOverride( name = "phoneNumber", column = @Column(name = "contact_phone")),
    })
    private Address Address;

    @PrePersist
    public void generateCustomId() {

        // Append prefix to the numeric ID, e.g:- CID_001
        this.customerId = "CID_" + String.format("%03d", id);
    }
}
