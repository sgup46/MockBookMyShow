package com.sapient.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "BOOKINGS")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Booking {
	
	@Id
	private long booking_id;


	private Date show_date;

	private String show_time;

	private String movie_name;

	private String seat_id;
	
}
