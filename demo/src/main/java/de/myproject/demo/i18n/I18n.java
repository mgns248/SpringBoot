package de.myproject.demo.i18n;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Helper class to simplify usage of {@link MessageSource} for resolving i18n
 * messages.
 */
@Component
public class I18n {

    private final MessageSource messages;

    /**
     * Inject the current message*.property file (it depends on the language setting
     * in the browser).
     */
    I18n(MessageSource messages) {
        this.messages = messages;
    }

    /**
     * Resolves a message that is located in a messages*.properties file according
     * to a key.
     * 
     * @param key    of the message to be resolved.
     * @param locale determines the language used for the tranlation.
     *               A corresponding message_countrycode.properties file must exist.
     * @return A translated message or an error message if the key could not be
     *         found.
     */
    public String msg(String key, Locale locale) {
        var keyNotFoundMessage = String.format("Key for message '%s' not found", key);
        return messages.getMessage(key, null, keyNotFoundMessage, locale);
    }
}