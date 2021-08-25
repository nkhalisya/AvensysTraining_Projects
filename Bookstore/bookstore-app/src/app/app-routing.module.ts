import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookComponent } from './book/book.component';
import { SearchComponent } from './search/search.component';

const routes: Routes = [
  {path: 'books', component:BookComponent},
  {path: 'search', component:SearchComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [BookComponent , SearchComponent];
