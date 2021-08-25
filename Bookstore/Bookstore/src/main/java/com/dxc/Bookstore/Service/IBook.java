package com.dxc.Bookstore.Service;

import org.springframework.data.repository.CrudRepository;

import com.dxc.Bookstore.Model.Book;

public interface IBook extends CrudRepository<Book, Integer>{

}
