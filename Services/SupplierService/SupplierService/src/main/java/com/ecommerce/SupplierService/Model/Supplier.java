package com.ecommerce.SupplierService.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(generator = "custom-generator")
    @GenericGenerator(name = "custom-generator", strategy = "com.ecommerce.SupplierService.IdGenerator.CustomIdGenerator")
    private String supplierId;
    @NotNull(message="Name can't be null!")
    String Fname;
    String Lname;
    @NotNull(message ="Enter Valid Number!" )
    private int phoneNo;
    @NotNull(message = "Email Id is mandatory!")
    @Email(message = "Enter the valid emailId!")
    @Column(unique = true)
    private String supplierEmail;
    @NotNull(message = "BusinessId can't be null!")
    private String businessId;
    @NotNull(message = "Enter the valid address!")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "phoneNumber", column = @Column(name = "contact_phone")),
    })
    private Address supplierAddress;
    @NotNull(message="Password can't be null!")
    private String password;
}
