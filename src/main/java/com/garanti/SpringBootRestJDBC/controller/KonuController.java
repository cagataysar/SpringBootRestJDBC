package com.garanti.SpringBootRestJDBC.controller;

import com.garanti.SpringBootRestJDBC.model.Konu;
import com.garanti.SpringBootRestJDBC.repo.KonuRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "konu")
public class KonuController
{
    private KonuRepo repo;

    public KonuController(KonuRepo repo) {
        this.repo = repo;
    }

    @GetMapping (path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity< List< Konu > > getAll()
    {
        // localhost:9090/konu/getAll
        List<Konu> res = repo.getAll();
        if ( res == null ||res.size() == 0 ) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping(path = "getByIdHeader", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Konu> getByIdHeader(@RequestHeader (name = "id") Integer id)
    {
        // localhost:9090/konu/getById?id=1
        Konu res = repo.getById(id);
        if ( res != null ) {
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(path = "getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Konu> getByIdQueryParam(@RequestParam (value = "id", required = true) Integer id)
    {
        // localhost:9090/konu/getById?id=1
        Konu res = repo.getById(id);
        if ( res != null ) {
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Konu> getByIdPathParam(@PathVariable(value = "id") Integer id)
    {
        // localhost:9090/konu/getById/1
        //bütün parametreleri vermek zorundayız
        //consume restful servisin dışardan alacağı data türünü belirtir
        //produce web servisin dışarıya vereceği türü belirtir
        Konu res = repo.getById(id);
        if ( res != null ) {
            return ResponseEntity.ok(res);
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody Konu konu)
    {
        // localhost:9090/konu/save
        // {"name":"RestTest"}
        if (repo.save(konu))
        {
            return ResponseEntity.status(HttpStatus.CREATED).body("Başarı ile kaydedildi");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Başarı ile kaydedildi");
        }
    }

    @DeleteMapping(path = "deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") Integer id)
    {
        // localhost:9090/konu/deleteById/1
        if (repo.deleteById(id))
        {
            return ResponseEntity.ok("Başarı ile silindi");
        }
        else
        {
            return ResponseEntity.internalServerError().body("Başarı ile silinemedi");
        }
    }

    @DeleteMapping(path = "deleteByIdHeader")
    public ResponseEntity<String> deleteByIdHeader(@RequestHeader(value = "id") Integer id)
    {
        // localhost:9090/konu/deleteById/1
        if (repo.deleteById(id))
        {
            return ResponseEntity.ok("Başarı ile silindi");
        }
        else
        {
            return ResponseEntity.internalServerError().body("Başarı ile silinemedi");
        }
    }

    @PostMapping(path = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Konu konu) {
//        localhost:9090/konu/update
//        {"id":16, "name": "React"}
        if (repo.update(konu))
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(konu.getNAME() + " isimli konu başarıyla güncellendi");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(konu.getNAME() + " isimli konu güncellenemedi");
        }
    }
}
