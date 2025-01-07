package acsse.csc03a3.obj;
import java.io.Serializable;

/*
 * Implementing Serializable allows objects to be saved to a persistent storage medium, such as database
 */
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String userSurname;
	private String userid;
	private String nationality;
	private String userRoles;

	public Person(String username, String userSurname, String id, String nationality, String userRoles) {

		this.username = username;
		this.userSurname = userSurname;
		this.userid = id;
		this.nationality = nationality;
		this.userRoles = userRoles;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}
}
