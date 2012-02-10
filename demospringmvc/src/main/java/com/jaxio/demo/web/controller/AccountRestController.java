/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-mvc-3-sd:src/main/java/web/controller/restController.e.vm.java
 */
package com.jaxio.demo.web.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static com.google.common.collect.Lists.newArrayList;

import java.io.PrintWriter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jaxio.demo.web.util.AutoCompleteResult;
import com.jaxio.demo.web.util.SearchParameters;
import com.jaxio.demo.domain.Account;
import com.jaxio.demo.repository.AccountRepository;

@Controller
@RequestMapping("domain/rest/account/")
public class AccountRestController {
    @Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value = "/", method = POST)
    @ResponseBody
    public Account create(@Valid Account account) {
        accountRepository.save(account);
        return account.copy();
    }

    @RequestMapping(value = "/", method = POST, headers = "content-type:application/json")
    @ResponseBody
    public Account jsonCreate(@Valid @RequestBody Account account) {
        accountRepository.save(account);
        return account.copy();
    }

    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public List<Account> list(Account account, SearchParameters searchParameters) {
        Page<Account> page = accountRepository.findWithExample(account, searchParameters.toPageRequest());
        List<Account> ret = newArrayList();
        for (Account _account : page.getContent()) {
            ret.add(_account.copy());
        }
        return ret;
    }

    @RequestMapping(value = "/{pk}", method = GET)
    @ResponseBody
    public Account get(@PathVariable("pk") Account account) {
        return account.copy();
    }

    @RequestMapping(value = "/{pk}", method = PUT)
    @ResponseBody
    public Account update(@PathVariable("pk") Account accountToUpdate, @Valid Account account) {
        account.copyTo(accountToUpdate);
        accountRepository.save(accountToUpdate);
        return accountToUpdate.copy();
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") Account account) {
        accountRepository.delete(account);
        return true;
    }

    @RequestMapping("/autocomplete")
    @ResponseBody
    public List<AutoCompleteResult> autocomplete(@RequestParam(value = "term", required = false) String searchPattern,
            SearchParameters search) {
        if (searchPattern != null && !searchPattern.isEmpty()) {
            search.setSearchPattern(searchPattern);
        }

        List<AutoCompleteResult> ret = newArrayList();
        for (Account account : accountRepository.find(search.getSearchPattern())) {
            String objectPk = account.getId().toString();
            String objectLabel = formattingConversionService.convert(account, String.class);
            ret.add(new AutoCompleteResult(objectPk, objectLabel));
        }
        return ret;
    }

    @ExceptionHandler()
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public void exception(Exception e, PrintWriter out) {
        out.write(e.getMessage());
    }
}