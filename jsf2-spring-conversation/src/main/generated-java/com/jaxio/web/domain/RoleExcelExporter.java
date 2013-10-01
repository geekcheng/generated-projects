/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-jsf2-spring-conversation:src/main/java/domain/ExcelExporter.e.vm.java
 */
package com.jaxio.web.domain;

import javax.inject.Inject;
import javax.inject.Named;

import com.jaxio.domain.Role;
import com.jaxio.web.domain.support.GenericExcelExporter;
import com.jaxio.web.faces.ConversationContextScoped;

/**
 * Exports to excel document {@link Role} search criteria and result. 
 */
@Named
@ConversationContextScoped
public class RoleExcelExporter extends GenericExcelExporter<Role> {
    @Inject
    protected RoleSearchForm sf;

    public RoleExcelExporter() {
        super("role_roleName");
    }

    @Override
    protected void fillResultItem(int row, Role item) {
        int col = 0;
        setValue(row, col++, item.getRoleName());
    }

    @Override
    public void fillSearchCriteria(int row) {
        useCriteriaSheet();
        setTermSelector(row++, 0, "role_roleName", sf.getRoleNameTermSelector());

        setSelector(row++, 0, "role_roleName", sf.getRoleNameSelector());
    }
}