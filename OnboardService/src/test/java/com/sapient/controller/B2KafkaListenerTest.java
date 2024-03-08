package com.sapient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.domain.MovieShow;
import com.sapient.messaging.B2BKafkaListener;
import com.sapient.model.Show;
import com.sapient.service.CityService;
import com.sapient.service.MovieService;
import com.sapient.service.ShowService;
import com.sapient.service.TheaterService;
import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
@Data
public class B2KafkaListenerTest {

    @InjectMocks
    B2BKafkaListener b2BKafkaListener;

    @Mock
    CityService theCityService;

    @Mock
    MovieService theMovieService;

    @Mock
    TheaterService theTheaterService;

    @Mock
    ShowService theShowService;

    @Test
    public void testB2BContollerCreateShow() throws JsonProcessingException {
        MovieShow movieShow = new MovieShow();
        movieShow.setShowId(1L);
        movieShow.setMovieId(1L);
        movieShow.setShowTime("\"3:30 PM\"");
        movieShow.setOperation("create");

        Show s = new Show();
        s.setShow_Id(1L);

        HashMap request = new HashMap();
        request.put("show_time", "\"3:30 PM\"");
        s.setShow_date(new Date());

        ConsumerRecord<String, String> record = Mockito.mock(ConsumerRecord.class);
        Mockito.when(record.value()).thenReturn(new ObjectMapper().writeValueAsString(movieShow));
        Mockito.when(theShowService.createShow(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString())).thenReturn(s);
        b2BKafkaListener.setTheShowService(theShowService);
        MovieShow show =b2BKafkaListener.createOrupdateShow(record);
        Assertions.assertNotNull(show);
        Assertions.assertNotNull(show.getShowId());
        Assertions.assertNotNull(show.getShowTime());
    }

    @Test
    public void testB2BContollerUpdateShow() throws JsonProcessingException {

        MovieShow movieShow = new MovieShow();
        movieShow.setShowId(1L);
        movieShow.setMovieId(1L);
        movieShow.setShowTime("\"3:30 PM\"");
        movieShow.setOperation("update");

        Show s = new Show();
        s.setShow_Id(1L);

        HashMap request = new HashMap();
        request.put("show_time", "\"3:30 PM\"");
        s.setShow_date(new Date());

        ConsumerRecord<String, String> record = Mockito.mock(ConsumerRecord.class);
        Mockito.when(record.value()).thenReturn(new ObjectMapper().writeValueAsString(movieShow));
        Mockito.when(theShowService.updateShow(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString())).thenReturn(s);
        b2BKafkaListener.setTheShowService(theShowService);
        MovieShow show =b2BKafkaListener.createOrupdateShow(record);
        Assertions.assertNotNull(show);
        Assertions.assertNotNull(show.getShowId());
        Assertions.assertNotNull(show.getShowTime());
    }

    @Test
    public void testB2BContolleDeleteShow() throws JsonProcessingException {
        MovieShow movieShow = new MovieShow();
        movieShow.setShowId(1L);
        movieShow.setMovieId(1L);
        movieShow.setShowTime("\"3:30 PM\"");
        movieShow.setOperation("delete");

        Show s = new Show();
        s.setShow_Id(1L);

        HashMap request = new HashMap();
        request.put("show_time", "\"3:30 PM\"");
        s.setShow_date(new Date());

        ConsumerRecord<String, String> record = Mockito.mock(ConsumerRecord.class);
        Mockito.when(record.value()).thenReturn(new ObjectMapper().writeValueAsString(movieShow));

        b2BKafkaListener.setTheShowService(theShowService);
        MovieShow show =b2BKafkaListener.createOrupdateShow(record);
        Assertions.assertNotNull(show);
        Assertions.assertNotNull(show.getShowId());
        Assertions.assertNotNull(show.getShowTime());

    }
}
