package de.myproject.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;


import de.myproject.demo.i18n.MessageSourceSettings;

/**
 * Base helper class for testing web controllers with i18n messages.
 * 
 * @see https://docs.spring.io/spring-framework/reference/testing/spring-mvc-test-framework/server-performing-requests.html
 * @see https://docs.spring.io/spring-framework/reference/testing/spring-mvc-test-framework/server-defining-expectations.html
 */
@Import({ MessageSourceSettings.class })
abstract class BaseMockMvcI18nTest {

    /**
     * Add an instance of Spring's MockMvc utility to enable testing of web pages.
     */
    @Autowired
    protected MockMvc mvc;

    /**
     * Inject the current message*.property file (it depends on the language setting
     * in the browser).
     */
    @Autowired
    private MessageSource messages;

    /**
     * Returns the message value associated with the given key from the current
     * messages*.properties file.
     */
    protected String i18n(String key) {
        var defaultMessage = String.format("Message key not found: %s", key);
        var locale = LocaleContextHolder.getLocale();
        return messages.getMessage(key, null, defaultMessage, locale);
    }

    protected String getHtml(String urlPath) throws Exception {
        return getHtml(urlPath, Locale.ENGLISH);
    }

    protected String getHtml(String urlPath, Locale locale) throws Exception {
        return mvc.perform(getRequest(urlPath, locale))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    protected String getHtml(MockHttpServletRequestBuilder request) throws Exception {
        return mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    protected MockHttpServletRequestBuilder getRequest(String urlPath) {
        return get(urlPath);
    }

    protected MockHttpServletRequestBuilder getRequest(String urlPath, Locale locale) {
        return get(urlPath).header(HttpHeaders.ACCEPT_LANGUAGE, locale);
    }

    protected MockHttpServletRequestBuilder postRequest(String urlPath) {
        return post(urlPath).header(HttpHeaders.ACCEPT_LANGUAGE);
    }

    protected MockHttpServletRequestBuilder postRequest(String urlPath, Locale locale) {
        return post(urlPath).header(HttpHeaders.ACCEPT_LANGUAGE, locale);
    }
}