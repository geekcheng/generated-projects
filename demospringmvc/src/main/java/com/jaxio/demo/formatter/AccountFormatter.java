/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-mvc-3-sd:src/main/java/formatter/Formatter.e.vm.java
 */
package com.jaxio.demo.formatter;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.text.ParseException;
import java.util.Locale;

import com.jaxio.demo.domain.Account;
import com.jaxio.demo.domain.Account_;
import com.jaxio.demo.repository.AccountRepository;
import com.jaxio.demo.formatter.support.DiscoverableFormatter;
import com.jaxio.demo.formatter.support.FormatterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * {@link Formatter} for {@link Account} that is picked by automatically by {@link CustomFormattingConversionServiceFactory}
 * 
 * @see Formatter
 * @see CustomFormattingConversionServiceFactory
 */
@Component
public class AccountFormatter implements DiscoverableFormatter<Account> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Class<Account> getTarget() {
        return Account.class;
    }

    @Override
    public String print(Account account, Locale locale) {
        return FormatterUtil.print(Account_.username.getName(), account, locale);
    }

    @Override
    public Account parse(String text, Locale locale) throws ParseException {
        if (isBlank(text)) {
            return new Account();
        }
        Account account = accountRepository.findOne(text);
        return account != null ? account : new Account();
    }
}
