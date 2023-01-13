package com.garanti.SpringBootRestJDBC.service;

import com.garanti.SpringBootRestJDBC.model.Ogrenci;
import com.garanti.SpringBootRestJDBC.repo.OgrenciRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OgrenciService {

    @Autowired
    private OgrenciRepo repo;

    public List< Ogrenci > getAll() {
        return repo.getAll();
    }

    public Ogrenci getById(int id) {
        return repo.getById(id);
    }

    public boolean deleteById( int id) {
        return repo.deleteById(id);
    }

    public List<Ogrenci> getAllLike(String name) {
        return repo.getAllLike(name);
    }

    public boolean update(Ogrenci ogrenci) {
        return repo.update(ogrenci);
    }

    public boolean save ( Ogrenci ogrenci)
    {
        try {
            return  repo.save(ogrenci);
        }
        catch (Exception e) {
            return false;
        }
    }
}
