/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-backend-jpa:src/main/java/repository/Repository.e.vm.java
 */
package com.jaxio.repository;

import javax.inject.Named;
import javax.inject.Singleton;

import org.hibernate.LazyInitializationException;
import org.springframework.transaction.annotation.Transactional;

import com.jaxio.domain.Customer;
import com.jaxio.repository.support.GenericRepository;

/**
 * {@link GenericRepository} for {@link Customer} 
 */
@Named
@Singleton
public class CustomerRepository extends GenericRepository<Customer, Integer> {

    public CustomerRepository() {
        super(Customer.class);
    }

    @Override
    public Customer getNew() {
        return new Customer();
    }

    @Override
    public Customer getNewWithDefaults() {
        return getNew().withDefaults();
    }

    /**
     * Safe way to load the contractBinary content. 
     */
    @Transactional(readOnly = true)
    public byte[] getContractBinary(Customer customer) {
        if (!customer.isIdSet()) {
            return customer.getContractBinary();
        }

        try {
            return customer.getContractBinary();
        } catch (LazyInitializationException lie) { // _HACK_ as we still rely on hibernate here
            return get(customer).getContractBinary();
        }
    }
}