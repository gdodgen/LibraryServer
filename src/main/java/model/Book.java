package model;

public class Book {
	
	String title;
	String author;
	String ISBN;
	String genre;
	
	boolean checkedOut = false;
	String outID = null;

	public Book(String title, String ISBN, String author, String genre, Boolean checkedOut, String outID ) {
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
		this.genre = genre;
		this.checkedOut = checkedOut;
		this.outID = outID;
	}
	
	public void checkout(String id){
		checkedOut = true;
		outID = id;
	}
	public void checkin(String id){
		checkedOut = false;
		outID = null;
	}
	public String getISBN(){
		return ISBN;
	}
	public String getTitle(){
		return title;
	}
	public String getAuthor(){
		return author;
	}	
	public String getGenre(){
		return genre;
	}
	public boolean getCheckedOut() {
		return checkedOut;
	}
	public String getOutID(){
		return outID;
	}
}


