package org.example;


import org.example.model.SpinResult;
import org.example.service.SlotMachineService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SlotMachineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SlotMachineService slotMachineService;

    @Test
    void testSpinEndpoint() throws Exception {
        Mockito.when(slotMachineService.spin(1L, BigDecimal.valueOf(10)))
                .thenReturn(new SpinResult(Arrays.asList("🍒", "🍒", "🍒"),
                        BigDecimal.valueOf(50),
                        BigDecimal.valueOf(140)));

        mockMvc.perform(post("/slot/1/spin?bet=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.symbols[0]").value("🍒"))
                .andExpect(jsonPath("$.winAmount").value(50))
                .andExpect(jsonPath("$.newBalance").value(140));
    }
}
