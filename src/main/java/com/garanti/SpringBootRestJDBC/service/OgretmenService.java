package com.garanti.SpringBootRestJDBC.service;


import com.garanti.SpringBootRestJDBC.model.Ogretmen;
import com.garanti.SpringBootRestJDBC.repo.OgretmenRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
// burası hem business hem de proxy katmanı
public class OgretmenService {

    @Autowired
    private OgretmenRepo repo;

    public boolean save ( Ogretmen ogretmen)
    {
        try
        {
            return  repo.save(ogretmen);
        }
        catch (Exception e) {
            return false;
        }
    }

    public List< Ogretmen > getAll() {
        return repo.getAll();
    }

    public Ogretmen getById(int id) {
        return repo.getById(id);
    }

    public boolean deleteById( int id) {
        return repo.deleteById(id);
    }

    public List<Ogretmen> getAllLike(String name) {
        return repo.getAllLike(name);
    }

    public boolean update(Ogretmen ogretmen) {
        return repo.update(ogretmen);
    }
}
