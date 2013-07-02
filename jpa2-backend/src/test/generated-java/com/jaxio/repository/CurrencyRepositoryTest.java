/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-backend-jpa:src/test/java/service/RepositoryTest.e.vm.java
 */
package com.jaxio.repository;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jaxio.dao.CurrencyDao;
import com.jaxio.dao.support.SearchParameters;
import com.jaxio.domain.Currency;
import com.jaxio.repository.CurrencyRepository;

/**
 * Unit test on CurrencyRepository
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrencyRepositoryTest {

    @Mock
    private CurrencyDao currencyDao;

    @InjectMocks
    private CurrencyRepository currencyRepository;

    @Test
    public void findUniqueOrNoneReturnsNull() {
        when(currencyDao.findUniqueOrNone(any(Currency.class), any(SearchParameters.class))).thenReturn(null);

        Currency result = currencyRepository.findUniqueOrNone(new Currency());

        assertThat(result).isNull();
        verify(currencyDao, times(1)).findUniqueOrNone(any(Currency.class), any(SearchParameters.class));
    }

    @Test
    public void findUniqueOrNoneReturnsSingleValue() {
        Currency unique = new Currency();

        when(currencyDao.findUniqueOrNone(any(Currency.class), any(SearchParameters.class))).thenReturn(unique);

        Currency result = currencyRepository.findUniqueOrNone(new Currency());

        assertThat(result).isNotNull().isSameAs(unique);
        verify(currencyDao, times(1)).findUniqueOrNone(any(Currency.class), any(SearchParameters.class));
    }

    @SuppressWarnings("unchecked")
    @Test(expected = NonUniqueResultException.class)
    public void findUniqueOrNoneThrowsExceptionWhenNonUniqueResults() {
        when(currencyDao.findUniqueOrNone(any(Currency.class), any(SearchParameters.class))).thenThrow(NonUniqueResultException.class);

        currencyRepository.findUniqueOrNone(new Currency());
    }

    @SuppressWarnings("unchecked")
    @Test(expected = NoResultException.class)
    public void findUniqueThrowsExceptionWhenNoResult() {
        when(currencyDao.findUnique(any(Currency.class), any(SearchParameters.class))).thenThrow(NoResultException.class);

        currencyRepository.findUnique(new Currency());
    }

    @Test
    public void findUnique() {
        Currency unique = new Currency();

        when(currencyDao.findUnique(any(Currency.class), any(SearchParameters.class))).thenReturn(unique);

        Currency result = currencyRepository.findUnique(new Currency());

        assertThat(result).isNotNull();
        verify(currencyDao, times(1)).findUnique(any(Currency.class), any(SearchParameters.class));
    }

    @SuppressWarnings("unchecked")
    @Test(expected = NonUniqueResultException.class)
    public void findUniqueThrowsExeptionWhenNonUniqueResult() {
        when(currencyDao.findUnique(any(Currency.class), any(SearchParameters.class))).thenThrow(NonUniqueResultException.class);

        currencyRepository.findUnique(new Currency());
    }
}