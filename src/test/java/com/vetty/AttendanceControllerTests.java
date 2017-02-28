package com.vetty;

import com.vetty.model.*;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by devfacotry on 2/28/17.
 */
public class AttendanceControllerTests extends BaseTests {
    @Test
    public void createAttendance() throws Exception {

        Attendance attendance = new Attendance();
        Animal animal = new Animal();
        animal.setId(1);

        DateTime date = new DateTime();
        date = date.plusHours(2);

        Veterinary veterinary = new Veterinary();
        veterinary.setId(1);

        AttendanceType attendanceType = new AttendanceType();
        attendanceType.setId(1);

        attendance.setDate(date.toDate());
        attendance.setVeterinary(veterinary);
        attendance.setAnimal(animal);
        attendance.setAttendanceType(attendanceType);

        this.mockMvc.perform(post("/attendance")
                .content(asJsonString(attendance))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
