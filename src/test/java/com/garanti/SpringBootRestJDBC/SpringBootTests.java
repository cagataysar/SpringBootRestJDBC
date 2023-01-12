package com.garanti.SpringBootRestJDBC;

/*
// test paketine yazdığım için maven ile otomatik entegre oldu
// webEnvironment yazılmazsa tomcat ayağa kalkamadı
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // spring framework olursa @ExtendWith({SpringExtension.class}) gerekebilir
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class SpringBootTestRestTemplateTest
{
    @Autowired
    // constructor 'dan yazınca jupiter den hata verdi
    public TestRestTemplate restTemplate;

    @Test
    @DisplayName (value = "Öğretmen get all testi") // junit 4 te olmayabilir
    @org.junit.jupiter.api.Order(value = 2)
    void ogretmenGetAll()
    {
        ResponseEntity< Ogretmen[]> res = restTemplate.getForEntity("http://localhost:9090/ogretmen/getAll", Ogretmen[].class);
        System.err.println("Spring boot testi başlıyor " + res.getBody().length);
    }

    @Test
    @DisplayName(value = "Öğretmen get by id testi") // junit 4 te olmayabilir
    @org.junit.jupiter.api.Order(value = 1)
    void ogretmenGetById()
    {
        ResponseEntity<Ogretmen> res = restTemplate.getForEntity("http://localhost:9090/ogretmen/getById/4", Ogretmen.class);
        System.err.println("Spring boot testi başlıyor " + res.getBody());
    }
}*/
