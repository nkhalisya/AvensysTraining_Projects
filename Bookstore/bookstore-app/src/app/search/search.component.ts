import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ManageService } from '../manage.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  totalBooks: any = []
  books: any = [];

  searchBy: string = "default";
  searchText: string = "";

  res: boolean = false;
  msg?: string;

  constructor(private manageService:ManageService, private router: Router) { }

  ngOnInit(): void {
    this.getBooks();
  }

  // apis
  searchBook(){
    console.log(this.searchBy + " " + this.searchText);
    this.manageService.getBookBySearch( this.searchText, this.searchBy ).subscribe( data => {
      this.books = data
    });
    this.router.navigate(['/search']);
  }
  getBooks(){
    this.manageService.getBooks().subscribe( data => {
      this.totalBooks = data
    });
  }

  result(state?: string){
    // console.log(this.totalBooks.length + " " + this.books.length);
    if( this.totalBooks.length == this.books.length ){
      this.msg = "No resutls found";
      this.res = true;
    }
    else{
      this.res = false;
    }
    return this.res;
  }

}
