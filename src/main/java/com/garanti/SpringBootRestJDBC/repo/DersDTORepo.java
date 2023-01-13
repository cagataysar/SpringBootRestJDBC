package com.garanti.SpringBootRestJDBC.repo;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class DersDTORepo {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

   /* public List< DersDTO > getAll()
    {
        String sql = "select * from BILGE.DERSDTO INNER JOIN  KONU ON KONU.ID = KONU.KONU_ID  ";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(DersDTO.class));
    }*/
}
