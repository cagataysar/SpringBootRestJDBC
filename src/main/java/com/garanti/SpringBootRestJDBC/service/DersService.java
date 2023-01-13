package com.garanti.SpringBootRestJDBC.service;

import com.garanti.SpringBootRestJDBC.model.Ders;
import com.garanti.SpringBootRestJDBC.repo.DersRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DersService {

    @Autowired
    private DersRepo repo;

    public List< Ders > getAll() {
        return repo.getAll();
    }

    public Ders getById(int id) {
        return repo.getById(id);
    }

    public boolean deleteById( int id) {
        return repo.deleteById(id);
    }

    public boolean save ( Ders ders)
    {
        try {
            return  repo.save(ders);
        }
        catch (Exception e) {
            return false;
        }
    }
}
