package model;

public class User {
	private String userName;
	//private String password;
	private String email;
	private String role; 
	private int id;
	
	public User(int i, String n, String r, String e) {
		
		setId(i);
		
		userName = n;
		role = r;
		
		email = e;
	}
	public User(String i, String n, String r, String e) {
		
		id = Integer.parseInt(i);
		
		userName = n;
		role = r;
		
		email = e;
	}
	
	public String getEmail(){
		return email;
	}
	public String getUserName() {
		return userName;
	}
	public String getRole() {
		return role;
	}
	/*public boolean isLibrarian() {  //broken code, fix later
		if (role == "librarian") {
			return true;
		}
		else {
			return false;
		}
	}*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}