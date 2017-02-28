package com.vetty.repository;

import com.google.common.hash.Hashing;
import com.vetty.model.Animal;
import com.vetty.model.AnimalType;
import com.vetty.model.Client;
import com.vetty.model.Veterinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.List;

@Repository
public class ClientRepository
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

    public Client create(final Client client)
    {
        final String sql = "insert into client(name, username, password) values(?, ?, ?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, client.getName());
                ps.setString(2, client.getUsername());
                ps.setString(3, Hashing.sha256().hashString(client.getPassword(), StandardCharsets.UTF_8).toString());
                return ps;
            }
        }, holder);

        int newClientId = holder.getKey().intValue();
        client.setId(newClientId);
        return client;
    }

    public void update(Veterinary veterinary) {
        jdbcTemplate.update("update veterinary set name = ? where id = ?",
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1, veterinary.getName());
                        preparedStatement.setInt(2, veterinary.getId());
                    }
                });
    }

    public void updateVeterinaryAnimalTypes(Veterinary veterinary) {

        jdbcTemplate.update("delete from veterinary_animal_types where veterinary_id = ?", veterinary.getId());
        jdbcTemplate.batchUpdate("insert into veterinary_animal_types (animal_type_id, veterinary_id) values (?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        AnimalType animalType = veterinary.getAnimalTypes().get(i);
                        preparedStatement.setInt(1, animalType.getId());
                        preparedStatement.setInt(2, veterinary.getId());
                    }

                    @Override
                    public int getBatchSize() {
                        return veterinary.getAnimalTypes().size();
                    }
                });

    }

    public void createClientAnimal(Client client) {
        jdbcTemplate.batchUpdate("insert into animal (animal_type, client_id, name, birth_date) values (?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        Animal animal = client.getAnimals().get(i);
                        preparedStatement.setInt(1, animal.getAnimalType().getId());
                        preparedStatement.setInt(2, client.getId());
                        preparedStatement.setString(3, animal.getName());
                        preparedStatement.setDate(4, new Date(animal.getBirthDate().getTime()));
                    }

                    @Override
                    public int getBatchSize() {
                        return client.getAnimals().size();
                    }
                });
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

