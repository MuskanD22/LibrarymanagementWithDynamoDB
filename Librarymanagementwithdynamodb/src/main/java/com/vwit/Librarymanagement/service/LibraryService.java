package com.vwit.Librarymanagement.service;

import java.util.List;

import com.vwit.Librarymanagement.model.Author;
import com.vwit.Librarymanagement.model.Book;
import com.vwit.Librarymanagement.model.Member;
import com.vwit.Librarymanagement.model.request.AuthorCreationRequest;
import com.vwit.Librarymanagement.model.request.BookCreationRequest;
import com.vwit.Librarymanagement.model.request.BookLendRequest;
import com.vwit.Librarymanagement.model.request.MemberCreationRequest;

public interface LibraryService {

	public Book readBookById(String id);

	public Iterable<Book> readBooks();

	public Book readBook(String isbn);

	public Book createBook(BookCreationRequest book);

	public void deleteBook(String id);

	public Member createMember(MemberCreationRequest request);

	public Member updateMember(String id, MemberCreationRequest request);

	public Author createAuthor(AuthorCreationRequest request);

	public List<String> lendABook(BookLendRequest request);

}
