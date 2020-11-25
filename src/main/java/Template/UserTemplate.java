package Template;

public class UserTemplate {
	private String userName;
	//private String password;
	private String role; 
	private String email;
	private String password;
	
	public UserTemplate() {
		super();
	}
	public UserTemplate(String name, String role, String email, String password) {
		userName = name;
		this.role = role;
		this.email = email;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getEmail() {
		return email;
	}
	
	@Override
	public String toString() {
		return "UserTemplate [userName=" + userName + ", role=" + role + "email:" + email + "]";
	}
	public String getPassword() {
		return password;
	}
	
}
