package com.sapient.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MovieShow {

    private long showId;
    private long movieId;
    private String showTime;
    // add , update or delete
    private String operation;
}
