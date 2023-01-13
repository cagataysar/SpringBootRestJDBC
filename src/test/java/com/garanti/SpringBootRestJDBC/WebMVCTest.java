package com.garanti.SpringBootRestJDBC;

// sadece bu controller ayağa kalksın diye
//@WebMvcTest(controllers = OgretmenController.class)
// junit4 kullanıyorsanız ve mvc testi yazıyorsanız
// @RunWith(SpringRunner.class)
// spring framework için başka annotation 'lar gerekebilir @ContextConfiguration gibi
/*public class WebMVCTest
{
    @Autowired
    public MockMvc mock;

    // servis varmış gibi
    @MockBean
    public OgretmenService ogretmenService;

    @Test
    public void findById() throws Exception
    {
        // System.err.println(ogretmenService.getAll().size()); // bu 0 döndürür çünkü sahte
        // ogretmenService.getById(4)
        // mock lanan bean üzerindeki getbyid 4 çalıştırıldığında dönmesi gereken sonuç, yani davranışı da mock ladık
        Mockito.when(ogretmenService.getById(4)).thenReturn(new Ogretmen(4, "Çağatay", true));
        RequestBuilder request = MockMvcRequestBuilders.get("/ogretmen/getById/4");
        ResultMatcher numberMatcher = MockMvcResultMatchers.jsonPath("$.is_GICIK").value(true);
        ResultMatcher nameMatcher = MockMvcResultMatchers.jsonPath("$.name").value("Çağatay");
        ResultMatcher yearMatcher = MockMvcResultMatchers.jsonPath("$.id").value(4);
        ResultMatcher okMatcer = MockMvcResultMatchers.status().isOk();
        mock.perform(request).andExpectAll(numberMatcher, nameMatcher, yearMatcher, okMatcer);
    }
}*/
