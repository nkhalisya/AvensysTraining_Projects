import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookComponent } from './book/book.component';
import { ManageService } from './manage.service';
import { SearchComponent } from './search/search.component';

@NgModule({
  declarations: [
    AppComponent,
    BookComponent,
    routingComponents,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ManageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
