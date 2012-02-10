/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-mvc-3-sd:src/main/java/web/controller/restController.e.vm.java
 */
package com.jaxio.demo.web.controller.more;

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
import com.jaxio.demo.domain.more.MoreTypesDemo;
import com.jaxio.demo.repository.more.MoreTypesDemoRepository;

@Controller
@RequestMapping("domain/rest/moretypesdemo/")
public class MoreTypesDemoRestController {
    @Autowired
    public FormattingConversionService formattingConversionService;

    @Autowired
    private MoreTypesDemoRepository moreTypesDemoRepository;

    @RequestMapping(value = "/", method = POST)
    @ResponseBody
    public MoreTypesDemo create(@Valid MoreTypesDemo moreTypesDemo) {
        moreTypesDemoRepository.save(moreTypesDemo);
        return moreTypesDemo.copy();
    }

    @RequestMapping(value = "/", method = POST, headers = "content-type:application/json")
    @ResponseBody
    public MoreTypesDemo jsonCreate(@Valid @RequestBody MoreTypesDemo moreTypesDemo) {
        moreTypesDemoRepository.save(moreTypesDemo);
        return moreTypesDemo.copy();
    }

    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    public List<MoreTypesDemo> list(MoreTypesDemo moreTypesDemo, SearchParameters searchParameters) {
        Page<MoreTypesDemo> page = moreTypesDemoRepository.findWithExample(moreTypesDemo, searchParameters
                .toPageRequest());
        List<MoreTypesDemo> ret = newArrayList();
        for (MoreTypesDemo _moreTypesDemo : page.getContent()) {
            ret.add(_moreTypesDemo.copy());
        }
        return ret;
    }

    @RequestMapping(value = "/{pk}", method = GET)
    @ResponseBody
    public MoreTypesDemo get(@PathVariable("pk") MoreTypesDemo moreTypesDemo) {
        return moreTypesDemo.copy();
    }

    @RequestMapping(value = "/{pk}", method = PUT)
    @ResponseBody
    public MoreTypesDemo update(@PathVariable("pk") MoreTypesDemo moreTypesDemoToUpdate,
            @Valid MoreTypesDemo moreTypesDemo) {
        moreTypesDemo.copyTo(moreTypesDemoToUpdate);
        moreTypesDemoRepository.save(moreTypesDemoToUpdate);
        return moreTypesDemoToUpdate.copy();
    }

    @RequestMapping(value = "/{pk}", method = DELETE)
    @ResponseBody
    public boolean delete(@PathVariable("pk") MoreTypesDemo moreTypesDemo) {
        moreTypesDemoRepository.delete(moreTypesDemo);
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
        for (MoreTypesDemo moreTypesDemo : moreTypesDemoRepository.find(search.getSearchPattern())) {
            String objectPk = moreTypesDemo.getId().toString();
            String objectLabel = formattingConversionService.convert(moreTypesDemo, String.class);
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