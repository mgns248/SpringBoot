package de.myproject.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.RequestBuilder;

import de.myproject.demo.LandingPageController.examResult;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException; 

@WebMvcTest
public class LandingPageControllerTests {

    @Autowired
    protected MockMvc mvc;

    @Test
    void testStart() throws Exception {
        // Arrange
        var request = get("/");

        // Act
        String html = mvc.perform(request).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        // Assert
        assertThat(html).contains("<div class=\"ergebnis \">\n" + //
                "            <div class=\"ergebnisDiv\">\n" + //
                "                <p>Ergebnis</p>\n" + //
                "                <p> \n" + //
                "\n" + //
                "                        <em>Gebe die Ergebnisse ein</em>\n" + //
                "                </p>\n" + //
                "                <p>\n" + //
                "\n" + //
                "                        <em>Gebe die Ergebnisse ein</em>\n" + //
                "                </p>\n" + //
                "            </div>\n" + //
                "        </div>");

    }
    
    @Test
    void testResultPageGreen() throws Exception {

        RequestBuilder request = post("/result")
            .param("r1", "50")
            .param("r2", "50")
            .param("r3", "50");
        

        // Act
        String html = mvc.perform(request).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        
        // Assert
        assertThat("green-background");
        assertThat(html).contains(
                "             <p> \n" + //
                "                        50.0\n" + //
                "\n" + //
                "                </p>\n" + //
                "                <p>\n" + //
                "                        BESTANDEN!!!\n" + //
                "\n" + //
                "                </p>");

    }

    @Test
    void testResultPageRed() throws Exception {

        RequestBuilder request = post("/result")
            .param("r1", "20")
            .param("r2", "20")
            .param("r3", "20");
        

        // Act
        String html = mvc.perform(request).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        
        // Assert
        assertThat("green-background");
        assertThat(html).contains(
                "             <p> \n" + //
                "                        20.0\n" + //
                "\n" + //
                "                </p>\n" + //
                "                <p>\n" + //
                "                        NICHT bestanden...\n" + //
                "\n" + //
                "                </p>");

    }

}
