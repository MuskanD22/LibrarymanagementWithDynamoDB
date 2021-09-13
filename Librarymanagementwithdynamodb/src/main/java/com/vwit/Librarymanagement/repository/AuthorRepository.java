package com.vwit.Librarymanagement.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.vwit.Librarymanagement.model.Author;

@EnableScan
public interface AuthorRepository extends CrudRepository<Author, String> {

}
