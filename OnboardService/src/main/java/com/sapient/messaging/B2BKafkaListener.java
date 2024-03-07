package com.sapient.messaging;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.domain.CityTheatre;
import com.sapient.domain.MovieShow;
import com.sapient.model.City;
import com.sapient.model.Movie;
import com.sapient.model.Show;
import com.sapient.model.Theater;
import com.sapient.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Slf4j
public class B2BKafkaListener {

    @Autowired
    @Qualifier("cityService")
    IService theCityService;

    @Autowired
    @Qualifier("movieService")
    IService theMovieService;

    @Autowired
    @Qualifier("theaterService")
    IService theTheaterService;

    @Autowired
    @Qualifier("showService")
    IService theShowService;

    @KafkaListener(topics = "city", groupId = "city-group", concurrency = "2")
    public void createCity(ConsumerRecord<String, String> record) throws JsonProcessingException {
        // Your business logic here
        log.info("Received city message: " + record.value());
        City city = ((CityService)theCityService).save(new ObjectMapper().readValue(record.value(), City.class));
        log.info("city Saved message: " + city);
    }

    @KafkaListener(topics = "movie", groupId = "movie-group", concurrency = "2")
    public void createMovie(ConsumerRecord<String, String> record) throws JsonProcessingException {
        // Your business logic here
        log.info("Received movie message: " + record.value());
        ObjectMapper objectMapper = new ObjectMapper();
        Movie m = ((MovieService)theMovieService).save(objectMapper.readValue(record.value(), Movie.class));
        log.info("Movie Saved message: " + m);
    }

    @KafkaListener(topics = "city_theatre", groupId = "city-theatre-group", concurrency = "2")
    public void addTheatreToCity(ConsumerRecord<String, String> record)  {
        try {
            // Your business logic here
            log.info("Received addTheatreToCity message: " + record.value());
            CityTheatre t = new ObjectMapper().readValue(record.value(), CityTheatre.class);
            ((TheaterService)theTheaterService).save(t);
            log.info("city Saved addTheatreToCity message: " + t);
        } catch (Exception e) {
            log.error("Error while consuming the message: " + e.getMessage());
            // send to deadLetter queue
        }
    }


    @KafkaListener(topics = "show", groupId = "show-group", concurrency = "2")
    public void createOrupdateShow(ConsumerRecord<String, String> record) throws JsonProcessingException {
        // Your business logic here
        log.info("Received show message: " + record.value());
        MovieShow movieShow = new ObjectMapper().readValue(record.value(), MovieShow.class);
        updateShow(movieShow);
        log.info("show updated message: " + movieShow);
    }

    private void updateShow(MovieShow movieShow) {
        switch (movieShow.getOperation()) {
            case "create":
                ((ShowService)theShowService).createShow(movieShow.getShowId(), movieShow.getMovieId(), movieShow.getShowTime());
                break;
            case "update":
                ((ShowService)theShowService).updateShow(movieShow.getShowId(), movieShow.getMovieId(), movieShow.getShowTime());
                break;
            case "delete":
                ((ShowService)theShowService).deleteShow(movieShow.getShowId(), movieShow.getMovieId());
                break;
        }
    }

}
