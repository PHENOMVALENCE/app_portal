package application.com.portal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;


@Entity
@Data

public class Application {
	
	@jakarta.persistence.Id
	@GeneratedValue
	
	private Long Id;
	private String FirstName;
	private String LastName;
	private String email;
	private Long RegNo;
	private String University;
	
	

}
