package com.collabera.interview.enroll.integration;

import com.collabera.interview.enroll.model.Enroll;
import com.collabera.interview.enroll.repository.EnrollRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerIt {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EnrollRepository enrollRepository;

    @Test
    public void saveEnroll_savesIntoRepository() throws Exception {

        mockMvc.perform(post("/api/enroll")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(Enroll.builder()
                        .id(UUID.randomUUID().toString())
                        .phoneNumber("312-123-1234")
                        .dateOfBirth(LocalDate.of(1990,07, 10))
                        .status(true)
                        .name("Fabrice Nduwayo")
                        .build())))
                .andExpect(status().isOk());

        List<Enroll> enrollList = enrollRepository.findAll();
        assertThat(enrollList.get(0).getName()).isEqualTo("Fabrice Nduwayo");
        enrollRepository.deleteAll();
    }
}
