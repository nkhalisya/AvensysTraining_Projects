import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from 'src/Book';

@Injectable({
  providedIn: 'root'
})
export class ManageService {

  constructor(private http:HttpClient) { }

  books: any = [];

  // Employees
  getBooks(){
    return this.http.get("http://localhost:8080/book/getAll");
  }

  getBookBySearch( searchText:string, searchBy:string){
    return this.http.get("http://localhost:8080/book/getBySearch/" + searchText + "/" + searchBy);
  }

  addBook(book: Book){
    return this.http.post("http://localhost:8080/book/insert", book);
  }

  updateBook(id: number, book: Book){
    return this.http.post("http://localhost:8080/book/update/" + id, book);
  }

  deleteBook(id: number){
    return this.http.get("http://localhost:8080/book/delete/" + id);
  }

  // form validate
  isEmpty( word: string): boolean {
    var empty = false;
    if( !word || word === "" || word.length === 0 ){
      empty = true;
    }
    return empty;
  }

  // form icons
  editState(id:number){
    // show update and cancel button
    var updateBtn = document.getElementById("update"+id);
    updateBtn?.setAttribute("style", "display: table-cell;");
    var cancelBtn = document.getElementById("cancel"+id);
    cancelBtn?.setAttribute("style", "display: table-cell;");
    
    // hide edit and delete button
    var editBtn = document.getElementById("edit"+id);
    editBtn?.setAttribute("style", "display: none;");
    var deleteBtn = document.getElementById("delete"+id);
    deleteBtn?.setAttribute("style", "display: none;");

    // disable rows
    this.rest(id, "none");
  }

  cancelState(id:number){
    // show edit and delete button
    var editBtn = document.getElementById("edit"+id);
    editBtn?.setAttribute("style", "display: table-cell;");
    var deleteBtn = document.getElementById("delete"+id);
    deleteBtn?.setAttribute("style", "display: table-cell;");
    
    // hide update and cancel button
    var updateBtn = document.getElementById("update"+id);
    updateBtn?.setAttribute("style", "display: none;");
    var cancelBtn = document.getElementById("cancel"+id);
    cancelBtn?.setAttribute("style", "display: none;");

    // activate rows
    this.rest(id, "table-cell");
  }

  rest( id:number, displayType:string){
    // everything except current --> id
    var editBtn = document.querySelectorAll("[id^='edit']");
    for( var i = 0; i < editBtn.length; i++){
      if(editBtn[i].id != "edit"+id){
        editBtn[i].setAttribute("style", "display: " + displayType );
      }
    }

    var deleteBtn = document.querySelectorAll("[id^='delete']");
    for( var i = 0; i < deleteBtn.length; i++){
      if(deleteBtn[i].id != "delete"+id){
        deleteBtn[i].setAttribute("style", "display: " + displayType );
      }
    }
  }
}
