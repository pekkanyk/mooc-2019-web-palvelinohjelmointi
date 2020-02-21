/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package airports;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author Pekka
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AircraftControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AircraftRepository aircraftRepository;
    
    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/aircrafts"))
              .andExpect(status().isOk()).andExpect(model().attributeExists("airports")).andExpect(model().attributeExists("aircrafts"));
    }
    
    @Test
    public void lisaysToimii() throws Exception {
        aircraftRepository.deleteAll();
        mockMvc.perform(post("/aircrafts?name=HA-LOL")).andExpect(status().isFound());
        assertEquals("HA-LOL",aircraftRepository.findAll().get(0).getName());
        
    }
    
    @Test
    public void lisaysToimii2() throws Exception{
        aircraftRepository.deleteAll();
        mockMvc.perform(post("/aircrafts?name=XP-55")).andExpect(status().isFound());
        
        mockMvc.perform(get("/aircrafts"))
                .andExpect(model().attribute("aircrafts", aircraftRepository.findAll()));
    }
}
