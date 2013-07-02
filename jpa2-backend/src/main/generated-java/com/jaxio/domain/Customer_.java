/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-backend-jpa:src/main/java/project/domain/EntityMeta_.e.vm.java
 */
package com.jaxio.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.jaxio.domain.Account;
import com.jaxio.domain.Address;

@StaticMetamodel(Customer.class)
public abstract class Customer_ {

    // Raw attributes
    public static volatile SingularAttribute<Customer, Integer> id;
    public static volatile SingularAttribute<Customer, String> companyName;
    public static volatile SingularAttribute<Customer, byte[]> contractBinary;
    public static volatile SingularAttribute<Customer, String> contractFileName;
    public static volatile SingularAttribute<Customer, String> contractContentType;
    public static volatile SingularAttribute<Customer, Integer> contractSize;
    public static volatile SingularAttribute<Customer, Integer> version;

    // Many to one
    public static volatile SingularAttribute<Customer, Address> address;

    // One to many
    public static volatile ListAttribute<Customer, Account> accounts;
}