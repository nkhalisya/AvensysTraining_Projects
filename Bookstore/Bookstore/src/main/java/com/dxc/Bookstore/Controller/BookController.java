package com.dxc.Bookstore.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.Bookstore.Model.Book;
import com.dxc.Bookstore.Service.IBook;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	public IBook ib;
	
	@PostMapping("/insert")
	public Book insert(@RequestBody Book book){
		
		// check if book exist
		boolean exist = false;
		List<Book> books = getAll();
		for(Book bk: books) {
			if( bk.getIsbn() == book.getIsbn() ) {
				// exist
				exist = true;
				break;
			}
		}
		
		Book bk = new Book();
		if(exist) {
			bk = getByIsbn(book.getIsbn());
		}
		else {
			// add
			bk = ib.save(book);
			System.out.println("book " + book.getTitle() + " inserted");
		}
		return bk;
	}
	
	@GetMapping("/getAll")
	public List<Book> getAll(){
		return (List<Book>) ib.findAll();
	}
	
	@GetMapping("/getByIsbn/{id}")
	public Book getByIsbn(@PathVariable int id){
		return (Book) ib.findById(id).get();
	}
	
	@GetMapping("/getBySearch/{searchText}/{searchBy}")
	public List<Book> getBySearch(@PathVariable String searchText,@PathVariable String searchBy){
		System.out.println(searchBy + " " + searchText);
		
		List<Book> books = getAll();
		List<Book> searchedBooks = new ArrayList<Book>();
		for( Book bk: books) {
			if( searchBy.equals("title")) {
				
				if(bk.getTitle().equalsIgnoreCase(searchText)) {
					// add to list
					searchedBooks.add(bk);
				}
			}
			else if( searchBy.equals("author")) {
				// split authors
				List<String> authors = Arrays.asList(bk.getAuthors().split(","));
				for( String author: authors ) {
					System.out.println(bk.getTitle() + " " + author);
					if(author.equalsIgnoreCase(searchText)) {
						// add to list
						searchedBooks.add(bk);
					}
				}
			}
		}
		
		// if no match, return all
		if(searchedBooks.size() == 0) {
			searchedBooks = books;
		}
		return searchedBooks;
	}
	
	@PostMapping("/update/{id}")
	public Book update(@PathVariable int id, @RequestBody Book book){
		Book b = (Book) ib.findById(id).get();
		b.setIsbn(book.getIsbn());
		b.setTitle(book.getTitle());
		b.setAuthors(book.getAuthors());
		b.setYear(book.getYear());
		b.setPrice(book.getPrice());
		System.out.println("book " + book.getTitle() + " updated");
		return ib.save(b);
	}
	
	@GetMapping("/delete/{id}")
	public List<Book> delete(@PathVariable int id){
		ib.deleteById(id);
		System.out.println("book deleted");
		return (List<Book>) ib.findAll();
	}

}
