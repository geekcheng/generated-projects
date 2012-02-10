/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-mvc-3-sd:src/main/java/web/validation/JpaUnique.p.vm.java
 */
package com.jaxio.demo.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.jaxio.demo.validation.impl.JpaUniqueValidator;

/**
 * Verify that the {@link Entity} is really unique by making a call to the repository
 * <p>
 * Please note that the message key should be present in the <code>ValidationMessages.properties</code>
 * @See JpaUniqueValidator
 * @See http://docs.jboss.org/hibernate/validator/4.0.1/reference/en/html_single/#validator-customconstraints
 */
@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = JpaUniqueValidator.class)
@Documented
public @interface JpaUnique {
    String message() default "{com.jaxio.demo.validation.JpaUnique.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}