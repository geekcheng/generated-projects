/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-jsf2-spring-conversation:src/main/java/domain/ExcelExporter.e.vm.java
 */
package com.jaxio.web.domain;

import static org.apache.commons.lang.StringUtils.join;

import javax.inject.Inject;
import javax.inject.Named;

import com.jaxio.domain.User;
import com.jaxio.web.domain.support.GenericExcelExporter;
import com.jaxio.web.faces.ConversationContextScoped;

/**
 * Exports to excel document {@link User} search criteria and result. 
 */
@Named
@ConversationContextScoped
public class UserExcelExporter extends GenericExcelExporter<User> {
    @Inject
    protected UserSearchForm sf;

    public UserExcelExporter() {
        super("user_username", //
                "user_email", //
                "user_isEnabled");
    }

    @Override
    protected void fillResultItem(int row, User item) {
        int col = 0;
        setValue(row, col++, item.getUsername());
        setValue(row, col++, item.getEmail());
        setValue(row, col++, item.getIsEnabled());
    }

    @Override
    public void fillSearchCriteria(int row) {
        useCriteriaSheet();
        setLeftHeader(row, 0, "search_full_text");
        setValue(row++, 1, join(sf.getTermsOnAll().getSelected(), ' '));
        setTermSelector(row++, 0, "user_username", sf.getUsernameTermSelector());
        setTermSelector(row++, 0, "user_email", sf.getEmailTermSelector());
        setTermSelector(row++, 0, "user_firstName", sf.getFirstNameTermSelector());
        setTermSelector(row++, 0, "user_lastName", sf.getLastNameTermSelector());

        setSelector(row++, 0, "user_username", sf.getUsernameSelector());
        setSelector(row++, 0, "user_password", sf.getPasswordSelector());
        setSelector(row++, 0, "user_email", sf.getEmailSelector());
        setSelector(row++, 0, "user_isEnabled", sf.getIsEnabledSelector());
        setSelector(row++, 0, "user_civility", sf.getCivilitySelector());
        setSelector(row++, 0, "user_firstName", sf.getFirstNameSelector());
        setSelector(row++, 0, "user_lastName", sf.getLastNameSelector());
        setSelectedEntities(row++, 0, "user_securityRoles", sf.getUser().getSecurityRoles());
    }
}