package com.sapient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "SEAT")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Seat {
	
	@Id
	private long seat_id;
	private String matrix_x;
	private String matrix_y;
	private int status;


	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Show theShow;

}
