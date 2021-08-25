import { Component, OnInit } from '@angular/core';
import { Book } from 'src/Book';
import { ManageService } from '../manage.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  // addForm
  addBk: any = new Book();
  // updateForm
  book: any = new Book();

  // list
  books: any = [];
  
  // alert
  error: boolean = false;
  success: boolean = false;
  msg = "";

  constructor(private manageService:ManageService) { }

  ngOnInit(): void {
    this.getBooks();
  }
  
  // apis
  getBooks(){
    this.manageService.getBooks().subscribe( data => {
      this.books = data
    });
  }
  addBook(){
    this.manageService.addBook(this.addBk).subscribe( data =>{
      console.log(data);
    }, 
    error => {
      console.log(error);
    });

    // refresh page, close modal
    location.reload();
  }
  updateBook( id: number ){

    this.setBook(id);

    console.log(this.book);
    this.manageService.updateBook(id, this.book).subscribe( data => {
      this.getBooks();
    })

    // refresh page, close modal
    location.reload();
  }
  deleteBook( id: number ){
    this.manageService.deleteBook(id).subscribe( data => {
      this.getBooks();
    })
  }

  setBook( id: number){
    var inputRow = document.querySelectorAll("[id$='_" + id + "']");
    for( var i = 0; i < inputRow.length; i++){
      var value = (<HTMLInputElement>inputRow[i])?.value;
      switch(i){
        case 0:
          this.book.isbn = parseInt(value);
          break;
        case 1:
          this.book.title = value;
          break;
        case 2:
          this.book.authors = value;
          break;
        case 3:
          this.book.year = value;
          break;
        case 4:
          this.book.price = value;
          break;
      }
    }
  }

  // update delete form
  editBook(id:number){
    // show and hide buttons
    this.manageService.editState(id);

    // activate input
    var inputRow = document.querySelectorAll("[id$='_" + id + "']");
    for( var i = 0; i < inputRow.length; i++){
      inputRow[i].removeAttribute("disabled");
    }
  }

  cancelEdit(id:number){
    // show and hide buttons
    this.manageService.cancelState(id);

    // disable input
    var inputRow = document.querySelectorAll("[id$='_" + id + "']");
    for( var i = 0; i < inputRow.length; i++){
      inputRow[i].setAttribute("disabled", "true");
    }
  }

  validateUpdateForm(id: number):boolean{
    var empty = false;

    this.setBook(id);

    if( this.manageService.isEmpty(this.book.isbn) ||
        this.manageService.isEmpty(this.book.title) ||
        this.manageService.isEmpty(this.book.authors) ||
        this.manageService.isEmpty(this.book.year) || 
        this.manageService.isEmpty(this.book.price)){
      empty = true;
    }
    return empty;
  }

  // add form
  validateAddForm():boolean{
    // input empty
    var empty = false;
    if( this.manageService.isEmpty(this.addBk.isbn) ||
        this.manageService.isEmpty(this.addBk.title) ||
        this.manageService.isEmpty(this.addBk.authors) ||
        this.manageService.isEmpty(this.addBk.year) || 
        this.manageService.isEmpty(this.addBk.price)){
      empty = true;
    }
    return empty;
  }

  checkForm():boolean{
    var show = false;
    // isbn
    for( var bk of this.books){
      if(this.addBk.isbn == bk.isbn){
        show = true;
        this.msg = "ISBN already exists";
        break;
      }
    }

    // new isbn -- check length
    if(!show){
      if(String(this.addBk.isbn).length != 8 ){
        show = true;
        this.msg = "ISBN must be of 8 characters";
      }
    }
    return show;
  }

}
