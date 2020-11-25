package Template;

public class BookTemplate {
	String title;
	String author;
	String ISBN;
	String genre;
	
	boolean checkedOut = false;
	String outID = null;

	public BookTemplate(String title, String ISBN, String author, String genre, Boolean checkedOut) {
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
		this.genre = genre;
		this.checkedOut = checkedOut;
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
}
