package com.sapient.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="SHOW")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Show {
	
	@Id
	@GeneratedValue
	private long show_Id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Theater theTheater;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Movie theMovie;


	private Date show_date;

	private String show_time;

	@OneToMany(mappedBy = "theShow")
	private List<Seat> seats;

}
