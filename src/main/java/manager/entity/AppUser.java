package manager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class AppUser {

	@Id
	@Column(name="id_user", length=11, nullable=false, unique= true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="first_name", length=100, nullable= false)
	private String firstName;
	
	@Column(name="last_name", length=100, nullable= false)
	private String lastName;
	
	@Column(name="email", length=100, nullable= false, unique = true)
	private String email;
	
	@Column(name="password", length=100, nullable= false)
	private String password;
	
	@Column(name="enabled", nullable = false)
	private boolean enabled; 
	
	
	
}
