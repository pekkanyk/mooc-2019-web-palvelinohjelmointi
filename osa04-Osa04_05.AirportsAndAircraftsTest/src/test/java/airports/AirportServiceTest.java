/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airports;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Pekka
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AirportServiceTest {
    
    @Autowired
    private AirportService airportService;
    @Autowired
    private AirportRepository airportRepository;
    
    @Test
    public void testCreate(){
        airportRepository.deleteAll();
        airportService.create("HEL", "Helsinki-Vantaa");
        assertEquals("Helsinki-Vantaa",airportService.findByIdentifier("HEL").getName());
        
    }
    
    @Test
    public void testList(){
        airportRepository.deleteAll();
        airportService.create("STO", "Stockholm");
        airportService.create("BER", "Berlin");
        airportService.create("TKU", "Turku");
        assertEquals(3,airportService.list().size());
    }
    
    @Test
    public void testNoDuplicates(){
        airportRepository.deleteAll();
        airportService.create("STO", "Stockholm");
        airportService.create("STO", "Stockholm");
        airportService.create("HEL", "Stockholm");
        airportService.create("HEL", "Helsinki-Vantaa");
        assertEquals(2,airportService.list().size());
        
    }
    
}
