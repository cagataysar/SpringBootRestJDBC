package com.garanti.SpringBootRestJDBC.repo;

import com.garanti.SpringBootRestJDBC.model.Konu;
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
public class KonuRepo
{
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List< Konu > getAll()
    {
        String sql = "select * from BILGE.KONU";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Konu.class));
    }

    public Konu getById(int id)
    {
        String sql = "select * from BILGE.KONU where ID = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(Konu.class));
    }

    public boolean deleteById(int id)
    {
        String sql = "delete from BILGE.KONU where ID = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

    public boolean save(Konu konu)
    {
        String sql = "Insert into BILGE.KONU (NAME) values (:NAME)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("NAME", konu.getNAME());
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

    public boolean update(Konu konu) {
        String sql = "update KONU set NAME = :NAME where ID = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",konu.getID());
        paramMap.put("NAME",konu.getNAME());
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
}
