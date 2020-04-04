package manager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends AppUser {

	@Id
	@Column(name = "id_student", length = 11, nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id", referencedColumnName = "project_id")
	private Integer id_project;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "students", cascade = CascadeType.ALL)
	private List<Professor> professors;

	public Student(Integer id, String firstName, String lastName, String email, String password, boolean enabled,
			Integer id2, Integer id_project) {
		super(id, firstName, lastName, email, password, enabled);
		id = id2;
		this.id_project = id_project;
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
	 * @return the id_project
	 */
	public Integer getId_project() {
		return id_project;
	}

	/**
	 * @param id_project the id_project to set
	 */
	public void setId_project(Integer id_project) {
		this.id_project = id_project;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_project == null) ? 0 : id_project.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_project == null) {
			if (other.id_project != null)
				return false;
		} else if (!id_project.equals(other.id_project))
			return false;
		return true;
	}

}
