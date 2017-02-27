package com.vetty.repository;

import com.vetty.model.Veterinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
public class VeterinaryRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly=true)
    public List<Veterinary> findAll() {
        return jdbcTemplate.query("select * from veterinary",
                new VeterinaryRowMapper());
    }

    @Transactional(readOnly=true)
    public Veterinary findUserById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from veterinary where id=?",
                new Object[]{id}, new VeterinaryRowMapper());
    }

    public Veterinary create(final Veterinary user)
    {
        final String sql = "insert into users(name,email) values(?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                return ps;
            }
        }, holder);

        int newUserId = holder.getKey().intValue();
        user.setId(newUserId);
        return user;
    }
    private class VeterinaryRowMapper implements RowMapper<Veterinary>
    {
        @Override
        public Veterinary mapRow(ResultSet rs, int rowNum) throws SQLException {
            Veterinary veterinary = new Veterinary();
            veterinary.setId(rs.getInt("id"));
            veterinary.setName(rs.getString("name"));
            return veterinary;
        }
    }
}

