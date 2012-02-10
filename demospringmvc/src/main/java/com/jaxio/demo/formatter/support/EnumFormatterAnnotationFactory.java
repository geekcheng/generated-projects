/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-mvc-3-sd:src/main/java/formatter/support/EnumFormatterAnnotationFactory.p.vm.java
 */
package com.jaxio.demo.formatter.support;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.unmodifiableSet;

import java.util.Formatter;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import com.jaxio.demo.formatter.EnumFormatter;

/**
 * Use the {@link AnnotationFormatterFactory} to build a generic {@link Formatter} for all methods or fields that have the {@link EnumFormater} annotation.
 * 
 * <pre>
 * &#064;EnumFormatter
 * public SpecificEnum getValue() {
 *     //
 * }
 * </pre>
 * 
 * @see EnumFormatter
 * @see AnnotationFormatterFactory
 * @see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/validation.html#format-CustomFormatAnnotations
 */
@Component
public class EnumFormatterAnnotationFactory implements AnnotationFormatterFactory<EnumFormatter> {

    @Autowired
    private MessageSource messageSource;

    private final Set<Class<?>> fieldTypes;

    public EnumFormatterAnnotationFactory() {
        Set<Class<?>> rawFieldTypes = newHashSet();
        rawFieldTypes.add(Enum.class);
        this.fieldTypes = unmodifiableSet(rawFieldTypes);
    }

    @Override
    public Set<Class<?>> getFieldTypes() {
        return fieldTypes;
    }

    @Override
    public Parser<?> getParser(EnumFormatter annotation, Class<?> fieldType) {
        return new DefaultEnumFormatter(fieldType, messageSource);
    }

    @Override
    public Printer<?> getPrinter(EnumFormatter annotation, Class<?> fieldType) {
        return new DefaultEnumFormatter(fieldType, messageSource);
    }
}
