package com.garanti.SpringBootRestJDBC.repo;

import com.garanti.SpringBootRestJDBC.model.Ders_Ogrenci;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class Ders_OgrenciRepo {
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List< Ders_Ogrenci > getAll()
    {
        String sql = "select * from BILGE.DERS_OGRENCI";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ders_Ogrenci.class));
    }

    public Ders_Ogrenci getById(int id)
    {
        String sql = "select * from BILGE.DERS_OGRENCI where ID = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(Ders_Ogrenci.class));
    }

    public boolean deleteById(int id)
    {

        String sql = "delete from BILGE.DERS_OGRENCI where ID = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

    public boolean save(Ders_Ogrenci ders_ogrenci)
    {

        String sql = "Insert into BILGE.DERS_OGRENCI (DERS_ID,OGRENCI_ID) values (:DERS, :OGRENCI)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("DERS", ders_ogrenci.getDERS_ID());
        paramMap.put("OGRENCI", ders_ogrenci.getOGRENCI_ID());
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

}