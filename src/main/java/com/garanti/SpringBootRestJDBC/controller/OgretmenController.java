package com.garanti.SpringBootRestJDBC.controller;

import com.garanti.SpringBootRestJDBC.model.Ogretmen;
import com.garanti.SpringBootRestJDBC.repo.OgretmenRepo;
import com.garanti.SpringBootRestJDBC.service.OgretmenService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 * Bu anotasyonlar class'ların başına yazılır ve bu class'ın
 * bean yapılarak hafızaya atılmasını sağlar.
 * @Controller
 * @RestController
 * @Component
 * @Configuration
 * @Service
 * @Repository
 *
 * import org.springframework.stereotype; sınıfından gelir.
 */

@RestController
@RequestMapping(path = "ogretmen")
@RestControllerAdvice(basePackageClasses = OgretmenRepo.class) // sadece DersRepo class'ının hatasını yakalamak için yazdık
//@io.swagger.v3.oas.annotations.tags.Tag (description = "ogretmen endpoint", name = "ogretmen") // swagger için isim açıklama yazılabilir
public class OgretmenController
{
    // localhost:9090/ogretmen

    private ResourceBundleMessageSource messageSource;

    @ExceptionHandler(value = BadSqlGrammarException.class)
    public void badSqlGrammerExceptionHandler(Exception e)
    {
        System.err.println(e.getMessage());
    }

    @ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
    @ResponseStatus(code = HttpStatus.IM_USED, reason = "invalid jdbc usage") // reason nerede çıktı veriyor bul
    public String invalidDataAccessApiUsageException(Exception e)
    {
        // isterseniz response entity döndürebilirsiniz
//        ResponseEntity.status(HttpStatus.IM_USED).body("yazılımcı kodu yanlış yazmış");
        System.err.println(e.getMessage());
        return "yazılımcı kodu yanlış yazmış";
    }

    /**
     * OgretmenRepo sınıfının başına @Repository yazdık.
     * @Autowired biz OgretmenRepo'yu new yapmayalım diye @Repository
     * ile buluyor ve new yapıyor.
     */

    // Dependency injection
//    @Autowired // required özelliğini dene iki repodan autowired almaya çalışsın mesela
    private OgretmenService service;

    public OgretmenController (OgretmenService service, ResourceBundleMessageSource messageSource)
    {
        // @Autowired yerine bu şekilde constructor injection yapılabilir.
        // this.repo = new OgretmenRepo(); // yazmak yerine dışardan yani app context ten geliyor
        // Bu kullanımda OgretmenRepo private olmalı.
        this.service = service;
        this.messageSource = messageSource;
    }

    @GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List< Ogretmen >> getAll()
    {
        // localhost:9090/ogretmen/getAll
        List<Ogretmen> res = service.getAll();
        if ( res == null ||res.size() == 0 ) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping(path = "findAllByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ogretmen>> getByIdQueryParam(@RequestParam(value = "name", required = true) String name)
    {
        // localhost:9090/ogretmen/findAllByName?name=a
        return ResponseEntity.ok(this.service.getAllLike(name));
    }

    @GetMapping(path = "getByIdHeader", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ogretmen> getByIdHeader(@RequestHeader (name = "id") Integer id)
    {
        // localhost:9090/ogretmen/getById?id=1
        Ogretmen res = service.getById(id);
        if ( res != null ) {
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(path = "getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ogretmen> getByIdQueryParam(@RequestParam (value = "id", required = true) Integer id)
    {
        // localhost:9090/ogretmen/getById?id=1
        Ogretmen res = service.getById(id);
        if ( res != null ) {
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @io.swagger.v3.oas.annotations.tags.Tag (description = "Bulunursa 200 döndür", name = "ID ile getir")
    @Operation (description = "Bulunursa 200 bulunamazsa 204", summary = "ID ile getir")
    public ResponseEntity<Ogretmen> getByIdPathParam(@PathVariable(value = "id") Integer id)
    {
        // localhost:9090/ogretmen/getById/1
        //bütün parametreleri vermek zorundayız
        //consume restful servisin dışardan alacağı data türünü belirtir
        //produce web servisin dışarıya vereceği türü belirtir
        Ogretmen res = service.getById(id);
        if ( res != null ) {
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody Ogretmen ogretmen, Locale locale)
    {
        // localhost:9090/ogretmen/save
        //
        // {"name":"RestTest", "is_GICIK": true}
        if ( service.save(ogretmen))
        {
            String text = messageSource.getMessage("ogretmen.save.success",null,locale);
            return ResponseEntity.status(HttpStatus.CREATED).body(text);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ogretmen.getNAME() + " isimli öğretmen eklenemedi");
        }
    }

    // delete metodunda 403 forbidden nasıl çalışır

    @DeleteMapping(path = "deleteById/{id}")
    @Hidden // bu metodu sadece swagger arayüzünde gizliyor
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") Integer id)
    {
        // localhost:9090/ogretmen/deleteById/1
        if ( service.deleteById(id))
        {
            return ResponseEntity.ok("Başarı ile silindi");
        }
        else
        {
            return ResponseEntity.internalServerError().body("Başarı ile silinemedi");
        }
    }

    @DeleteMapping(path = "deleteByIdHeader")
    @Hidden // bu metodu sadece swagger arayüzünde gizliyor
    public ResponseEntity<String> deleteByIdHeader(@RequestHeader(value = "id") Integer id)
    {
        // localhost:9090/ogretmen/deleteById/1
        if ( service.deleteById(id))
        {
            return ResponseEntity.ok("Başarı ile silindi");
        }
        else
        {
            return ResponseEntity.internalServerError().body("Başarı ile silinemedi");
        }
    }

    @PostMapping(path = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Ogretmen ogretmen) {
//        localhost:9090/ogretmen/update
//        {"id":16, "name": "Mahmut"}
        if ( service.update(ogretmen))
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(ogretmen.getNAME() + " isimli öğretmen başarıyla güncellendi");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ogretmen.getNAME() + " isimli öğretmen güncellenemedi");
        }
    }
}
