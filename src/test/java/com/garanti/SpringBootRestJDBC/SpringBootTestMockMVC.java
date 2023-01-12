package com.garanti.SpringBootRestJDBC;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
// AutoConfigureMockMvc yazmazsam aşağıdaki değişkeni inject edemez
@AutoConfigureMockMvc // mvc uygualmam ayakta imişçenise
public class SpringBootTestMockMVC
{
    @Autowired
    public MockMvc mock;

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