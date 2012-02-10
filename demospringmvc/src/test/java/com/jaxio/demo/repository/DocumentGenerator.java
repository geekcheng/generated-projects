/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-sd:src/test/java/repository/ModelGenerator.e.vm.java
 */
package com.jaxio.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jaxio.demo.domain.Account;
import com.jaxio.demo.domain.Document;
import com.jaxio.demo.repository.AccountGenerator;
import com.jaxio.demo.repository.AccountRepository;
import com.jaxio.demo.util.ValueGenerator;

/**
 * Helper class to create transient entities instance for testing purposes.
 * Simple properties are pre-filled with random values.
 */
@Service
public class DocumentGenerator {

    /**
     * Returns a new Document instance filled with random values.
     */
    public Document getDocument() {
        Document document = new Document();

        // simple attributes follows
        document.setDocumentContentType("a");
        document.setDocumentSize(1);
        document.setDocumentFileName("a");
        document.setDocumentBinary("d".getBytes());
        // mandatory relation
        Account account = accountGenerator.getAccount();
        accountRepository.save(account);
        document.setAccount(account);
        return document;
    }

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountGenerator accountGenerator;
}