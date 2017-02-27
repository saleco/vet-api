package com.vetty.repository;

import com.vetty.model.Animal;
import com.vetty.model.Attendance;
import com.vetty.model.AttendanceType;
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

/**
 * Created by devfacotry on 2/27/17.
 */
@Repository
public class AttendanceRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(final Attendance attendance)
    {
        final String sql = "insert into attendance (animal_id, veterinary_id, date, attendance_type_id) " +
                           "values(?, ?, ?, ?)";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, attendance.getAnimal().getId());
                ps.setInt(2, attendance.getVeterinary().getId());
                ps.setDate(3, new Date(attendance.getDate().getTime()));
                ps.setInt(4, attendance.getAttendanceType().getId());
                return ps;
            }
        });
    }


    @Transactional(readOnly=true)
    public List<Attendance> findAll() {
        return jdbcTemplate.query("select * from attendance",
                new AttendanceRowMapper());
    }
    private class AttendanceRowMapper implements RowMapper<Attendance>
    {
        @Override
        public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
            Attendance attendance = new Attendance();

            Animal animal = new Animal();
            animal.setId(rs.getInt("animal_id"));
            attendance.setAnimal(animal);

            Veterinary veterinary = new Veterinary();
            veterinary.setId(rs.getInt("veterinary_id"));

            attendance.setVeterinary(veterinary);

            attendance.setDate(rs.getDate("date"));

            AttendanceType attendanceType = new AttendanceType();
            attendanceType.setId(rs.getInt("attendance_type_id"));
            attendance.setAttendanceType(attendanceType);

            return attendance;
        }
    }
}
