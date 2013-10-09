/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-selenium-primefaces:src/test/java/selenium/pages/entity/SearchPage.e.vm.java
 */
package com.jaxio.appli.web.selenium.page.legacy;

import com.jaxio.appli.web.selenium.support.Page;
import com.jaxio.appli.web.selenium.support.elements.Autocomplete;
import com.jaxio.appli.web.selenium.support.elements.CustomWebElement;
import com.jaxio.appli.web.selenium.support.elements.EntityAction;
import com.jaxio.appli.web.selenium.support.elements.Messages;
import com.jaxio.appli.web.selenium.support.elements.OrderBy;
import com.jaxio.appli.web.selenium.support.elements.SearchForm;
import com.jaxio.appli.web.selenium.support.elements.StringInput;
import com.jaxio.appli.web.selenium.support.elements.Table;

@Page
public class LegacySearch {
    public Table table;
    public EntityAction entity;
    public Messages messages;
    public LegacySearchOrders orders;
    public LegacySearchForm form;

    public static class LegacySearchOrders extends CustomWebElement {
        public OrderBy name = new OrderBy("legacy_name");
        public OrderBy code = new OrderBy("legacy_code");
        public OrderBy dept = new OrderBy("legacy_dept");
        public OrderBy extraInfo = new OrderBy("legacy_extraInfo");
    }

    public static class LegacySearchForm extends SearchForm {
        public StringInput name = new StringInput("form:name");
        public StringInput code = new StringInput("form:code");
        public StringInput dept = new StringInput("form:dept");
        public Autocomplete extraInfo = new Autocomplete("form:extraInfo");
    }
}