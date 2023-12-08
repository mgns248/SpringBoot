package de.myproject.demo.i18n;

import java.io.IOException;
import java.io.Writer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

/**
 * Apply this ControllerAdvice to all controllers using I18n in this
 * application.
 */
@ConditionalOnBean(name = "i18n")
@ControllerAdvice
final class MustacheI18nAdvice {

    private I18n i18n;

    MustacheI18nAdvice(I18n i18n) {
        this.i18n = i18n;
    }

    /**
     * Add a new i18n tag to Mustache files. The tag ist replaced with the value
     * from the key found in the current message*.properties file.
     * 
     * EXAMPLE:
     * File: messages.properties (located in resources/i18n)
     * myKey=Hallo!
     * 
     * File: any.mustache (located in resources/templates)
     * <h1>{{#i18n}}myKey{{/i18n}}</h1>
     * 
     * @see: https://github.com/samskivert/jmustache
     */
    @ModelAttribute("i18n")
    Mustache.Lambda i18n() {
        return new Mustache.Lambda() {
            public void execute(Template.Fragment frag, Writer out) throws IOException {
                String messageKey = frag.execute();
                var locale = LocaleContextHolder.getLocale();
                String localizedMessageValue = i18n.msg(messageKey, locale);
                out.write(localizedMessageValue);
            }
        };
    }
}