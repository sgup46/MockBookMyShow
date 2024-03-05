 package com.sapient.entity;

 import lombok.Data;

 import javax.persistence.*;
 import java.util.List;

@Entity
@Table(name="CITY")
//@EntityListeners(AuditingEntityListener.class)
@Data
public class City {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long city_id;
	
	@Column(nullable=false)
	private String city_name;
	
	private String city_pincode;
	

	private String city_state;
	
	@OneToMany(mappedBy="city")
	private List<Theater> theTheaters;
//

}
