package springmvc.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

	String email;
	String password;
	int userId;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int accId;
	String url;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public int hashCode() {
		return Objects.hash(accId, email, password, url, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return accId == other.accId && Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(url, other.url) && userId == other.userId;
	}
	@Override
	public String toString() {
		return "Account [email=" + email + ", password=" + password + ", userId=" + userId + ", accId=" + accId
				+ ", url=" + url + "]";
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
