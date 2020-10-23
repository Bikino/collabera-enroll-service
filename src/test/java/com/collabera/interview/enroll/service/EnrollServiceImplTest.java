package com.collabera.interview.enroll.service;

import com.collabera.interview.enroll.model.Enroll;
import com.collabera.interview.enroll.repository.EnrollRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class EnrollServiceImplTest {

    @InjectMocks
    private EnrollServiceImpl subject;

    @Mock
    private EnrollRepository enrollRepositoryMock;

    private Enroll enroll;
    private String id;

    @Before
    public void setUp(){
        id = UUID.randomUUID().toString();
        enroll = Enroll.builder()
                .id(id)
                .name("Ildephonse BIKINO")
                .status(Boolean.TRUE)
                .dateOfBirth(LocalDate.of(2000,2, 10))
                .phoneNumber("202-531-0184")
                .build();
    }

    @Test
    public void saveEnroll_savesTheEnrollIntoRepository(){
        when(enrollRepositoryMock.save(any(Enroll.class))).thenReturn(enroll);

        subject.saveEnroll(enroll);

        verify(enrollRepositoryMock).save(enroll);
    }

    @Test
    public void findAll_returnsAllTheEnrolled(){
        when(enrollRepositoryMock.findAll()).thenReturn(singletonList(enroll));

        List<Enroll> actual = subject.findAll();

        verify(enrollRepositoryMock).findAll();
        assertThat(actual.size()).isEqualTo(1);
        assertThat(actual.get(0).getName()).isEqualTo(enroll.getName());
    }

    @Test
    public void findById_returnsUniqueEnrolledById(){
        when(enrollRepositoryMock.findById(anyString())).thenReturn(Optional.of(enroll));

        Enroll actual = subject.findById(id);

        verify(enrollRepositoryMock).findById(id);
        assertThat(actual.getId()).isEqualTo(id);
        assertThat(actual.getName()).isEqualTo(enroll.getName());
    }

//    @Test
//    public void deleteEnroll(){
//        subject.saveEnroll(enroll);
//        subject.deleteEnroll(id);
//        verify(enrollRepositoryMock).deleteById(id);
//    }

}