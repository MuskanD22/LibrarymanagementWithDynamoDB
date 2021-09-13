package com.vwit.Librarymanagement.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.vwit.Librarymanagement.model.Book;
import com.vwit.Librarymanagement.model.Lend;
import com.vwit.Librarymanagement.model.LendStatus;

@EnableScan
public interface LendRepository extends CrudRepository<Lend, String>{
	Optional<Lend> findByBookANDStatus(Book book, LendStatus status);
}
