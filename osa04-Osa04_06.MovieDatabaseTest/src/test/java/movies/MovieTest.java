/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies;

import static org.fluentlenium.core.filter.FilterConstructor.containingText;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Pekka
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class MovieTest extends org.fluentlenium.adapter.junit.FluentTest {
    @LocalServerPort
    private Integer port;
    
    @Test
    public void elokuvanMuokkaus(){
        goTo("http://localhost:" + port + "/movies");
        assertFalse(pageSource().contains("Uuno Epsanjassa"));
        assertFalse(pageSource().contains("Uuno Turhapuro"));
        find("#name").fill().with("Uuno Epsanjassa");
        find("#lengthInMinutes").fill().with("92");
        find("form").first().submit();
        assertTrue(pageSource().contains("Uuno Epsanjassa"));
        assertFalse(pageSource().contains("Uuno Turhapuro"));
        goTo("http://localhost:" + port + "/actors");
        assertFalse(pageSource().contains("Uuno Turhapuro"));
        find("#name").fill().with("Uuno Turhapuro");
        find("form").first().submit();
        assertTrue(pageSource().contains("Uuno Turhapuro"));
        find("a", containingText("Uuno Turhapuro")).click();
        find ("#add-to-movie").first().click();
        goTo("http://localhost:" + port + "/movies");
        assertTrue(pageSource().contains("Uuno Epsanjassa"));
        assertTrue(pageSource().contains("Uuno Turhapuro"));
    }
}
