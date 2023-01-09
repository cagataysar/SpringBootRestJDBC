package com.garanti.SpringBootRestJDBC.repo;

import com.garanti.SpringBootRestJDBC.model.Ogretmen;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class OgretmenRepo
{

    // olası hatalar
    // CannotGetJdbcConnectionException
    // BadSqlGrammarException
    // InvalidDataAccessApiUsageException
    // DataIntegrityViolationException

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /*public OgretmenRepo(JdbcTemplate jdbcTemplate, @Qualifier(value = "person2") Person p)
    {
        System.err.println(p.getIsim());
        this.jdbcTemplate = jdbcTemplate;
        List<Map<String, Object>> res = this.jdbcTemplate.queryForList("select * from BILGE.OGRETMEN");
        System.err.println(res.toString());
    }*/

    public List< Ogretmen > getAll()
    {
        // Incorrect column count: expected 1, actual 3
        // return jdbcTemplate.queryForList("select * from BILGE.OGRETMEN", Ogretmen.class);
        RowMapper<Ogretmen> rowMapper = new RowMapper<Ogretmen>()
        {
            @Override
            public Ogretmen mapRow(ResultSet result, int rowNum) throws SQLException
            {
                return new Ogretmen(result.getInt("ID"), result.getString("NAME"), result.getBoolean("IS_GICIK"));
            }
        };
        return jdbcTemplate.query("select * from BILGE.OGRETMEN", rowMapper);

//        Yukarıdali işlemi tek satırda yapıyor(BeanPropertyRowMapper);
//        return jdbcTemplate.query("select * from BILGE.OGRETMEN", BeanPropertyRowMapper.newInstance(Ogretmen.class));

//        Yine lambda ile tek satırda yazdık;
//        return jdbcTemplate.query("select * from BILGE.OGRETMEN", (ResultSet result, int rowNum) -> new Ogretmen(result.getInt("ID"), result.getString("NAME"), result.getBoolean("IS_GICIK")));

    }

    public Ogretmen getById(int id)
    {
        Ogretmen ogretmen = null;
        String sql = "select * from BILGE.OGRETMEN where ID = :ABUZIDDIN";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ABUZIDDIN", id);
        ogretmen = namedParameterJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(Ogretmen.class));
        // ---------------------------------
        /*ResultSetExtractor<Ogretmen> rse = new ResultSetExtractor<Ogretmen>()
        {
            @Override
            public Ogretmen extractData(ResultSet result) throws SQLException, DataAccessException
            {
                return new Ogretmen(result.getInt("ID"), result.getString("NAME"), result.getBoolean("IS_GICIK"));
            }
        };*/
//        namedParameterJdbcTemplate.query(sql, paramMap, rse);
        // ---------------------------------
        // Incorrect column count: expected 1, actual 3
        // namedParameterJdbcTemplate.queryForObject(sql, paramMap, Ogretmen.class);
        return ogretmen;
    }

    public boolean deleteById( int id){
        String sql = "delete from BILGE.OGRETMEN where ID = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID", id);
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

    public boolean save ( Ogretmen ogretmen)
    {
        String sql = "Insert into BILGE.OGRETMEN (NAME, IS_GICIK) values (:NAME, :GICIK)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("NAME", ogretmen.getNAME());
        paramMap.put("GICIK",ogretmen.isIS_GICIK());
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }

    public List<Ogretmen> getAllLike(String name)
    {
        String sql = "select * from BILGE.OGRETMEN where NAME LIKE :NAME";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("NAME", "%" + name + "%");
        return namedParameterJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(Ogretmen.class));
    }

    public boolean update(Ogretmen ogretmen) {
        String sql = "update OGRETMEN set NAME = :NAME, IS_GICIK = :GICIK  where ID = :ID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ID",ogretmen.getID());
        paramMap.put("NAME",ogretmen.getNAME());
        paramMap.put("GICIK", ogretmen.isIS_GICIK());
        return namedParameterJdbcTemplate.update(sql, paramMap) == 1;
    }
}
