package org.example.controller;

import org.example.config.SpringConfig;
import org.example.service.SingerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class SingerControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetAllSingers() throws Exception {
        // Настройка мок-объекта SingerService
        SingerService singerService = Mockito.mock(SingerService.class);
        Mockito.when(singerService.getAllSingers()).thenReturn(new ArrayList<>());

        // Настройка контроллера с использованием мок-объекта
        SingerController singerController = new SingerController(singerService);

        // Выполнение запроса и проверка результатов
        mockMvc.perform(get("/singer"))
                .andExpect(status().isOk());
    }

}