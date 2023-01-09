package com.garanti.SpringBootRestJDBC.repo;

import com.garanti.SpringBootRestJDBC.model.Ders;
import com.garanti.SpringBootRestJDBC.model.Konu;
import com.garanti.SpringBootRestJDBC.model.Ogretmen;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class DersRepo
{
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List< Ders > getAll()
    {
        String sql = "select * from BILGE.DERS ORDER BY ID ASC";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ders.class));
    }

    public Ders getById(int id)
    {
        String sql = "select * from BILGE.DERS where ID = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(Ders.class));
    }

    public boolean deleteById(int id)
    {
        String sql = "delete from BILGE.DERS where ID = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

    public boolean save(Ders ders)
    {
        String sql = "Insert into BILGE.DERS (OGR_ID,KONU_ID) values (:OGR, :KONU)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("OGR", ders.getOGR_ID());
        paramMap.put("KONU",ders.getKONU_ID());
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

    @Transactional
    public boolean saveTransactional(Ogretmen ogretmen, Konu konu)
    {
        // try catch olmayacak !!!!
        String sql = "Insert into BILGE.OGRETMEN (NAME, IS_GICIK) values (:NAME, :GICIK)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("NAME", ogretmen.getNAME());
        paramMap.put("GICIK", ogretmen.isIS_GICIK());
        namedParameterJdbcTemplate.update(sql, paramMap);

        sql = "Insert into BILGE.KONU (NAME) values (:NAME)";
        paramMap = new HashMap<>();
        paramMap.put("NAME", konu.getNAME());
        namedParameterJdbcTemplate.update(sql, paramMap);

        sql = "Insert into BILGE.DERS (OGR_ID, KONU_ID) values (:OGR_ID, :KONU_ID)";
        paramMap = new HashMap<>();
        paramMap.put("OGR_ID", 112233);
        paramMap.put("KONU_ID", 112233);
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
}
