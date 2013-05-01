/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring-conversation:src/main/java/domain/support/GenericController.p.vm.java
 */
package com.jaxio.web.domain.support;

import static com.jaxio.web.conversation.ConversationHolder.getCurrentConversation;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;

import com.jaxio.dao.support.JpaUniqueUtil;
import com.jaxio.dao.support.SearchParameters;
import com.jaxio.domain.Identifiable;
import com.jaxio.printer.TypeAwarePrinter;
import com.jaxio.repository.support.Repository;
import com.jaxio.web.util.MessageUtil;
import com.jaxio.web.conversation.Conversation;
import com.jaxio.web.conversation.ConversationCallBack;
import com.jaxio.web.conversation.ConversationContext;
import com.jaxio.web.conversation.ConversationFactory;
import com.jaxio.web.permission.support.GenericPermission;

/**
 * Base controller for JPA entities.
 */
public abstract class GenericController<E extends Identifiable<PK>, PK extends Serializable> implements ConversationFactory {

    private String selectUri;
    private String editUri;

    @Inject
    protected JpaUniqueUtil jpaUniqueUtil;
    @Inject
    protected MessageUtil messageUtil;
    @Inject
    protected TypeAwarePrinter printer;
    protected Repository<E, PK> repository;
    protected GenericPermission<E> permission;

    public GenericController(Repository<E, PK> repository, GenericPermission<E> permission, String selectUri, String editUri) {
        this.repository = repository;
        this.permission = permission;
        this.selectUri = selectUri;
        this.editUri = editUri;
    }

    public String getEditUri() {
        return editUri;
    }

    public String getSelectUri() {
        return selectUri;
    }

    // -------------------
    // ConversationFactory
    // -------------------

    @Override
    public boolean canCreateConversation(HttpServletRequest request) {
        return getSelectUri().equals(request.getServletPath()) || getEditUri().equals(request.getServletPath());
    }

    @Override
    public Conversation createConversation(HttpServletRequest request) {
        String uri = request.getServletPath();
        E e = repository.getNew();

        if (getSelectUri().equals(uri)) {
            return Conversation.newConversation(request, newSearchContext(e.getClass().getSimpleName()));
        } else if (getEditUri().equals(uri)) {
            return Conversation.newConversation(request, newEditContext(e.getClass().getSimpleName(), e));
        } else {
            throw new IllegalStateException("Unexpected conversation creation demand");
        }
    }

    public List<E> complete(String value) {
        return repository.find(completeSearchParameters(value));
    }

    /**
     * Decision helper used when handling ajax autoComplete event and regular page postback.
     */
    public boolean shouldReplace(E currentEntity, E selectedEntity) {
        if (selectedEntity == null) {
            if (currentEntity == null) {
                // trivial case
                return true;
            }

            if (currentEntity.isIdSet()) {
                // user prefer to set the current entity to null.
                return true;
            }

            // more tricky case (postback)
            // current entity is not yet persisted, so the converter has returned null...
            // therefore we keep the current entity.
            return false;
        }

        if (currentEntity != null) {
            if (selectedEntity.getId().equals(currentEntity.getId())) {
                Comparable<Object> currentVersion = repository.getVersion(currentEntity);
                if (currentVersion == null) {
                    // assume no version at all is available
                    // let's stick with current entity.
                    return false;
                }

                Comparable<Object> selectedVersion = repository.getVersion(selectedEntity);

                if (currentVersion.compareTo(selectedVersion) == 0) {
                    // existing could have been edited and not yet saved, we keep it.
                    return false;
                } else {
                    // we could have an optimistic locking exception at save time
                    // TODO: what should we do here?
                    return false;
                }
            } else {
                // the selected one is not the same entity. We respect user's choice.
                return true;
            }
        } else {
            // current entity was null, so we certainly accept the selected one
            return true;
        }
    }

    public List<String> completeSame(String value) {
        return newArrayList(value);
    }

    public List<String> completeProperty(String value) throws Exception {
        String property = parameter("property");
        E template = repository.getNew();
        PropertyUtils.setProperty(template, property, value);
        Set<String> ret = newHashSet(value);
        for (E object : repository.find(template, searchParameters())) {
            ret.add((String) PropertyUtils.getProperty(object, property));
        }
        return newArrayList(ret);
    }

    @SuppressWarnings("unchecked")
    private <T> T parameter(String propertyName) {
        return (T) UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get(propertyName);
    }

    protected SearchParameters completeSearchParameters(String value) {
        return searchParameters().searchPattern(value);
    }

    protected SearchParameters searchParameters() {
        SearchParameters searchParameter = new SearchParameters().anywhere();
        defaultOrder(searchParameter);
        return searchParameter;
    }

    protected void defaultOrder(SearchParameters searchParameters) {
    }

    // ///////////////////////
    // Helper
    // ///////////////////////

    public String editSubReadOnlyView(String labelKey, E e) {
        checkPermission(permission.canView(e));
        ConversationContext<E> ctx = newEditContext(labelKey, e);
        getCurrentConversation().setNextContextSubReadOnly(ctx);
        return ctx.view();
    }

    public String editSubView(String labelKey, E e, ConversationCallBack<E> editCallBack) {
        checkPermission(permission.canEdit(e));
        ConversationContext<E> ctx = newEditContext(labelKey, e, editCallBack);
        getCurrentConversation().setNextContextSub(ctx);
        return ctx.view();
    }

    public String createSubView(String labelKey, ConversationCallBack<E> createCallBack) {
        return createSubView(labelKey, repository.getNew(), createCallBack);
    }

    public String createSubView(String labelKey, E e, ConversationCallBack<E> createCallBack) {
        checkPermission(permission.canCreate());
        ConversationContext<E> ctx = newEditContext(labelKey, e, createCallBack);
        getCurrentConversation().setNextContextSub(ctx);
        return ctx.view();
    }

    public String selectSubView(String labelKey, ConversationCallBack<E> selectCallBack) {
        checkPermission(permission.canSelect());
        ConversationContext<E> ctx = newSearchContext(labelKey, selectCallBack);
        getCurrentConversation().setNextContextSub(ctx);
        return ctx.view();
    }

    public String multiSelectSubView(String labelKey, ConversationCallBack<E> selectCallBack) {
        checkPermission(permission.canSelect());
        ConversationContext<E> ctx = newSearchContext(labelKey, selectCallBack);
        ctx.setVar("multiCheckboxSelection", true);
        getCurrentConversation().setNextContextSub(ctx);
        return ctx.view();
    }

    protected void checkPermission(boolean check) {
        if (!check) {
            throw new IllegalStateException();
        }
    }

    /**
     * Helper to construct a new ConversationContext to edit an entity.
     * @param e the entity to edit.
     */
    public ConversationContext<E> newEditContext(final E e) {
        ConversationContext<E> ctx = new ConversationContext<E>();
        ctx.setEntity(e); // used by GenericEditForm.init()
        ctx.setIsNewEntity(!e.isIdSet());
        ctx.setViewUri(getEditUri());
        return ctx;
    }

    public ConversationContext<E> newEditContext(String labelKey, final E e) {
        ConversationContext<E> ctx = newEditContext(e);
        ctx.setLabelWithKey(labelKey);
        return ctx;
    }

    public ConversationContext<E> newEditContext(String labelKey, final E e, ConversationCallBack<E> callBack) {
        ConversationContext<E> ctx = newEditContext(labelKey, e);
        ctx.setCallBack(callBack);
        return ctx;
    }

    /**
     * Helper to construct a new ConversationContext for search/selection.
     */
    public ConversationContext<E> newSearchContext() {
        ConversationContext<E> ctx = new ConversationContext<E>();
        ctx.setViewUri(getSelectUri());
        return ctx;
    }

    public ConversationContext<E> newSearchContext(String labelKey) {
        ConversationContext<E> ctx = newSearchContext();
        ctx.setLabelWithKey(labelKey);
        return ctx;
    }

    public ConversationContext<E> newSearchContext(String labelKey, ConversationCallBack<E> callBack) {
        ConversationContext<E> ctx = newSearchContext(labelKey);
        ctx.setCallBack(callBack);
        return ctx;
    }
}