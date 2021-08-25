import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ManageService } from './manage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'bookstore-app';

  constructor() { }

  ngOnInit(): void {
  }

  
}
