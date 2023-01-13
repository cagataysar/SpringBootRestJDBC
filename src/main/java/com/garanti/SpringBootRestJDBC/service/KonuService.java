package com.garanti.SpringBootRestJDBC.service;

import com.garanti.SpringBootRestJDBC.model.Konu;
import com.garanti.SpringBootRestJDBC.repo.KonuRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KonuService {

    @Autowired
    private KonuRepo repo;

    public List< Konu > getAll() {
        return repo.getAll();
    }

    public Konu getById(int id) {
        return repo.getById(id);
    }

    public boolean deleteById( int id) {
        return repo.deleteById(id);
    }

    public boolean update(Konu konu) {
        return repo.update(konu);
    }

    public boolean save ( Konu konu)
    {
        try {
            return  repo.save(konu);
        }
        catch (Exception e) {
            return false;
        }
    }
}
