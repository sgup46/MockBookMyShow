package com.sapient.domain;

import lombok.Data;

@Data
public class ReserveSeat {

    private long seatId;
    private long showId;
    private long theatreId;
}
