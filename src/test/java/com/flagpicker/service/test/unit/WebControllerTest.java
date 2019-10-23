package com.flagpicker.service.test.unit;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.flagpicker.config.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@WebAppConfiguration
public class WebControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void getCountryMetricsTest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/search?keyword=India").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andDo(print());
  }

}
