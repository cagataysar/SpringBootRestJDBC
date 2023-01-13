package com.garanti.SpringBootRestJDBC;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.garanti.SpringBootRestJDBC.model.Ogretmen;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
// AutoConfigureMockMvc yazmazsam aşağıdaki değişkeni inject edemez
@AutoConfigureMockMvc // mvc uygulamam ayaktaymış gibi çalışıyor
public class SpringBootTestMockMVC
{
    @Autowired
    public MockMvc mock;

    @Test
    public void saveOgretmen() throws Exception
    {
        String ogretmen = new ObjectMapper().writeValueAsString(new Ogretmen("junit", true));
        System.err.println("----> " + ogretmen);
        RequestBuilder request = MockMvcRequestBuilders.post("/ogretmen/save").content(ogretmen).contentType(MediaType.APPLICATION_JSON).header("Accept-Language", "en");
        MockHttpServletResponse response = mock.perform(request).andReturn().getResponse();
        // response.getStatus() == 201
        // response.getContentAsString() == "Successfully saved"
        // javanın dafault keyword ü
        // assert response.getContentAsString().equals("Successfully saved");
        // org.assertj.core.api.Assertions
        org.junit.jupiter.api.Assertions.assertEquals("Successfully saved", response.getContentAsString());
        System.err.println("------> " + response.getContentAsString());
    }

    @Test
    public void findByName() throws Exception
    {
        // {"name":"Pelin", "ogr_NUMBER":123, "year" : 3}
        // localhost:8080 'e gerek yok çünkü mock mvc testi
        RequestBuilder request = MockMvcRequestBuilders.get("/ogretmen/getById/4");
        // assertion gibi expectation yazabilirim
        ResultMatcher numberMatcher = MockMvcResultMatchers.jsonPath("$.is_GICIK").value(true);
        ResultMatcher nameMatcher = MockMvcResultMatchers.jsonPath("$.name").value("Çağatay");
        ResultMatcher yearMatcher = MockMvcResultMatchers.jsonPath("$.id").value(4);
        ResultMatcher okMatcer = MockMvcResultMatchers.status().isOk();
        mock.perform(request).andExpect(numberMatcher).andExpect(nameMatcher).andExpect(yearMatcher).andExpect(okMatcer);
    }
}