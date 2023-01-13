package com.garanti.SpringBootRestJDBC.controller;


import com.garanti.SpringBootRestJDBC.repo.DersDTORepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "dersdto")
public class DersDTOController {

    @Autowired
    private DersDTORepo repo;

    public DersDTOController(DersDTORepo repo) {
        this.repo = repo;
    }

   /* @GetMapping (path = "getAllDTO", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity< List< DersDTO > > getAll()
    {
        // localhost:9090/dersdto/getAllDTO
        List< DersDTO > res = repo.getAll();
        if (res == null || res.size() == 0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else
        {
            return ResponseEntity.ok(res);
        }
    }*/
}
