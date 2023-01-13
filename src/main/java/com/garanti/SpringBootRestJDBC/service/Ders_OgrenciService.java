package com.garanti.SpringBootRestJDBC.service;

import com.garanti.SpringBootRestJDBC.model.Ders_Ogrenci;
import com.garanti.SpringBootRestJDBC.repo.Ders_OgrenciRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Ders_OgrenciService {

    @Autowired
    private Ders_OgrenciRepo repo;

    public List< Ders_Ogrenci > getAll() {
        return repo.getAll();
    }

    public Ders_Ogrenci getById(int id) {
        return repo.getById(id);
    }

    public boolean deleteById( int id) {
        return repo.deleteById(id);
    }

    public boolean save ( Ders_Ogrenci dersOgrenci)
    {
        try {
            return  repo.save(dersOgrenci);
        }
        catch (Exception e) {
            return false;
        }
    }

}
