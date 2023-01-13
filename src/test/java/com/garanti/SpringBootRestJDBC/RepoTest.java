package com.garanti.SpringBootRestJDBC;

//// @repository 'leri tanıması için
//@JdbcTest
//@Import(OgretmenRepo.class)
//// @DataJpaTest // hibernate ve jpa için
//// otomatik embedded h2 database falan kullanıyorlar ama bizim kapı gibi oracle 'ımız var
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class RepoTest
//{
//    @Autowired
//    public OgretmenRepo ogretmenRepo;
//
//    @Test
//    // rollback olmazsa otomatik rollback yapıyor, default
//    // @Rollback(value = false)
//    public void getById()
//    {
//        // ogretmen içindeki equals ı override ettik
//        Ogretmen actual = ogretmenRepo.getById(4);
//        Ogretmen expected = new Ogretmen(4, "Çağatay", true);
//        Assertions.assertEquals(expected, actual);
//    }
//}