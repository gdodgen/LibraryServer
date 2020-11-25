# Product Description
Backend API for a library service that allows users to search for books to see if the library has them and and whether 
they are checked out. Both Librarians and Patrons can check books in and out, but only Librarians can add and remove books or remove users.

# API Description


for each url, the action variable tells the API what action you wish to take.  
## LoginServlet
Get actions:
* /login takes an id and password and logs the user in
    * Example: (http://localhost:8080/LibraryServer/servlet.Login?action=/login&id=16&password=password16) logs in the user who's id is 16.  
* /logout logs the current user out of the program.  
    * Example: (http://localhost:8080/LibraryServer/servlet.Login?action=/logout)

      
Post actions:   
   
Post is used to create a new account. it takes a userName, email, and role and creates a new user and generates an id for them.   
* Example: (http://localhost:8080/LibraryServer/servlet.UserServlet?action=/addUser&userName=Steve3000&role=patron&email=test3@email.com) 
      will create a new user with userName Steve3000, role of patron, and email test3@emailcom.

## UserServlet   
Get lists every user in the system.
* Example: (http://localhost:8080/LibraryServer/servlet.UserServlet)

Post actions:  
* /delete takes an id and deletes a user. it can only be called if logged in as a librarian
    * Example: if Steve3000's generated id was 16, 
     (http://localhost:8080/LibraryServer/servlet.UserServlet?action=/delete&id=16) will delete Steve3000  
* /newEmail takes an id and a new email and changes the email of the user with that id. 
    * Example: (http://localhost:8080/LibraryServer/servlet.UserServlet?action=/newEmail&email=new@email.com&id=16) 
      will change the email of user with id = 16 to new@email.com

## BookServlet 

Get actions:  
the action variable tells the book servlet how you want to filter results  
the checkStatus variable toggles whether the search results include books that have been checked out of the library  
the keyword variable is what you are passing into search. searches are case sensitive.

* /showAll shows every book in the system
    * Example: (http://localhost:8080/LibraryServer/servlet.BookServlet?action=/showAll)
    
the search actions:
* /searchGenre
* /searchTitle
* /searchAuthor
* /searchISBN  

Examples: 
* (http://localhost:8080/LibraryServer/servlet.BookServlet?action=/searchAuthor&keyword=Mary Shelly) returns every book by Mary Shelly
* (http://localhost:8080/LibraryServer/servlet.BookServlet?action=/searchGenre&checkStatus=true&keyword=SciFi) returns all SciFi books, excluding ones that have been checked out.

Push actions:
* /checkin takes a book's isbn and unmarks a book as checked out
* /checkout takes a books isbn, marks it as checked out and records the id of the user who checked it out
* /deleteBook deletes a book from the system permanently. it can only be called by a librarian.
* /newBook  creates a book in the database. it can also only be called by a librarian.

# setup

## software requirements

* apache tomcat
* jdk8

write section later, after moving program to laptop
