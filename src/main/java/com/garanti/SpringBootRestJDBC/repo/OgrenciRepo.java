package com.garanti.SpringBootRestJDBC.repo;

import com.garanti.SpringBootRestJDBC.model.Ogrenci;
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
public class OgrenciRepo
{
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List< Ogrenci > getAll()
    {
        String sql = "select * from BILGE.OGRENCI";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ogrenci.class));

//        Yine lambda ile tek satırda yazdık;
//        return jdbcTemplate.query("select * from BILGE.OGRENCI", (ResultSet result, int rowNum) -> new Ogretmen(result.getInt("ID"), result.getString("NAME"), result.getBoolean("IS_GICIK")));

    }

    public Ogrenci getById(int id)
    {
        String sql = "select * from BILGE.OGRENCI where ID = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",id);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(Ogrenci.class));
    }

    public boolean deleteById( int id)
    {
        String sql = "delete from BILGE.OGRENCI where ID = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

    public boolean save ( Ogrenci ogrenci)
    {
        String sql = "Insert into BILGE.OGRENCI (NAME,OGR_NUMBER,YEAR) values (:NAME, :OGR, :YEAR)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("NAME", ogrenci.getNAME());
        paramMap.put("OGR",ogrenci.getOGR_NUMBER());
        paramMap.put("YEAR",ogrenci.getYEAR());
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

    public List<Ogrenci> getAllLike(String name) {
        String sql = "select * from BILGE.OGRENCI where NAME LIKE :NAME";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("NAME","%" + name + "%");
        return namedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(Ogrenci.class));
    }

    public boolean update(Ogrenci ogrenci) {
        String sql = "update OGRENCI set NAME = :NAME where ID = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",ogrenci.getID());
        paramMap.put("NAME",ogrenci.getNAME());
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
}
