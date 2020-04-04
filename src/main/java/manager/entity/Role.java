package manager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
	
	@Id
	@Column(name="id_role", length=11, nullable=false, unique= true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="role", length=100, nullable=false)
	private String role;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="roles", cascade = CascadeType.ALL)
	private List<AppUser> appUsers;

	public Role(Integer id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the appUsers
	 */
	public List<AppUser> getAppUsers() {
		return appUsers;
	}

	/**
	 * @param appUsers the appUsers to set
	 */
	public void setAppUsers(List<AppUser> appUsers) {
		this.appUsers = appUsers;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + ", appUsers=" + appUsers + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appUsers == null) ? 0 : appUsers.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (appUsers == null) {
			if (other.appUsers != null)
				return false;
		} else if (!appUsers.equals(other.appUsers))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
	
	

}
