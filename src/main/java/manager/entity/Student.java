package manager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name ="students")
public class Student 
{

	
	@Id
	@Column(name = "id_student", length = 11, nullable = false, unique =true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

	/*
	 * 
	 * */

}
