/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-sd:src/main/java/project/domain/Entity_.e.vm.java
 */
package com.jaxio.demo.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Document.class)
public abstract class Document_ {

    // Raw attributes
    public static volatile SingularAttribute<Document, String> id;
    public static volatile SingularAttribute<Document, String> documentContentType;
    public static volatile SingularAttribute<Document, Integer> documentSize;
    public static volatile SingularAttribute<Document, String> documentFileName;
    public static volatile SingularAttribute<Document, byte[]> documentBinary;
    public static volatile SingularAttribute<Document, Integer> version;

    // Technical attributes for query by example
    public static volatile SingularAttribute<Document, String> accountId;

    // Many to one
    public static volatile SingularAttribute<Document, Account> account;
}