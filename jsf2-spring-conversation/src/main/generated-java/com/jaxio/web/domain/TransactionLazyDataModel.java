/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-jsf2-spring-conversation:src/main/java/domain/LazyDataModel.e.vm.java
 */
package com.jaxio.web.domain;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import com.jaxio.domain.Transaction;
import com.jaxio.repository.TransactionRepository;
import com.jaxio.web.domain.TransactionExcelExporter;
import com.jaxio.web.domain.support.GenericLazyDataModel;
import com.jaxio.web.faces.ConversationContextScoped;

/**
 * Provide PrimeFaces {@link LazyDataModel} for {@link Transaction}
 */
@Named
@ConversationContextScoped
public class TransactionLazyDataModel extends GenericLazyDataModel<Transaction, Integer, TransactionSearchForm> {
    private static final long serialVersionUID = 1L;

    @Inject
    public TransactionLazyDataModel(TransactionRepository transactionRepository, TransactionController transactionController,
            TransactionSearchForm transactionSearchForm, TransactionExcelExporter transactionExcelExporter) {
        super(transactionRepository, transactionController, transactionSearchForm, transactionExcelExporter);
    }
}