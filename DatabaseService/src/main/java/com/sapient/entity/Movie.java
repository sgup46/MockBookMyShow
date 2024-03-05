package com.sapient.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="MOVIE")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Movie {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long movie_id;
	
	private String movie_name;
	
	private String movie_director;

	private String movie_description;
	
	@OneToMany(mappedBy = "theMovie")
	public List<Show> theShows;

}
