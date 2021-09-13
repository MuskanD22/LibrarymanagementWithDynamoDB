package com.vwit.Librarymanagement.controller.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vwit.Librarymanagement.model.Author;
import com.vwit.Librarymanagement.model.Book;
import com.vwit.Librarymanagement.model.Member;
import com.vwit.Librarymanagement.model.request.AuthorCreationRequest;
import com.vwit.Librarymanagement.model.request.BookCreationRequest;
import com.vwit.Librarymanagement.model.request.BookLendRequest;
import com.vwit.Librarymanagement.model.request.MemberCreationRequest;
import com.vwit.Librarymanagement.service.LibraryService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/library")
public class LibraryRestController {
	
	private final LibraryService libraryService;

	@GetMapping("/book")
	public ResponseEntity readBooks(String isbn) {
		if (isbn == null) {
			return ResponseEntity.ok(libraryService.readBooks());
		}
		libraryService.readBook(isbn);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/book/{bookId}")
	public ResponseEntity<Book> readBookById(@PathVariable String bookId) {
		libraryService.readBookById(bookId);
		return new ResponseEntity<Book>(HttpStatus.OK);
	}

	@PostMapping("/book")
	public ResponseEntity<Book> createBook(@RequestBody BookCreationRequest request) {
		Book createBook = libraryService.createBook(request);
		return new ResponseEntity<Book>(createBook, HttpStatus.CREATED);
		// return ResponseEntity.ok(libraryService.createBook(request));
	}

	@DeleteMapping("/book/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
		libraryService.deleteBook(bookId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/member")
	public ResponseEntity<Member> createMember(@RequestBody MemberCreationRequest request) {
		Member createMember = libraryService.createMember(request);
		return new ResponseEntity<Member>(createMember, HttpStatus.CREATED);
	}

	@PatchMapping("/member/{memberId}")
	public ResponseEntity<Member> updateMember(@RequestBody MemberCreationRequest request,
			@PathVariable String memberId) {
		Member updateMember = libraryService.updateMember(memberId, request);
		return new ResponseEntity<Member>(updateMember, HttpStatus.OK);
	}

	@PostMapping("book/lend")
	public ResponseEntity<List<String>> lendBook(@RequestBody BookLendRequest bookLendRequests) {
		List<String> lendABook = libraryService.lendABook(bookLendRequests);
		return new ResponseEntity<List<String>>(lendABook, HttpStatus.CREATED);
	}

	@PostMapping("/author")
	public ResponseEntity<Author> addBook(@RequestBody AuthorCreationRequest request) {
		Author author = libraryService.createAuthor(request);
		return new ResponseEntity<Author>(author, HttpStatus.CREATED);
	}

}
