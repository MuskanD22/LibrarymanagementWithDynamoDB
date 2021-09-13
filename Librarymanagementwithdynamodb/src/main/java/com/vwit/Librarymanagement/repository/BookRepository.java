package com.vwit.Librarymanagement.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.vwit.Librarymanagement.model.Book;

@EnableScan
public interface BookRepository extends CrudRepository<Book, String>{
	Optional<Book> findByIsbn(String isbn);
}
