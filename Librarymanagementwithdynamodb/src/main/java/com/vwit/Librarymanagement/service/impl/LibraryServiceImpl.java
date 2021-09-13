package com.vwit.Librarymanagement.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.vwit.Librarymanagement.exception.EntityNotFoundException;
import com.vwit.Librarymanagement.model.Author;
import com.vwit.Librarymanagement.model.Book;
import com.vwit.Librarymanagement.model.Lend;
import com.vwit.Librarymanagement.model.LendStatus;
import com.vwit.Librarymanagement.model.Member;
import com.vwit.Librarymanagement.model.MemberStatus;
import com.vwit.Librarymanagement.model.request.AuthorCreationRequest;
import com.vwit.Librarymanagement.model.request.BookCreationRequest;
import com.vwit.Librarymanagement.model.request.BookLendRequest;
import com.vwit.Librarymanagement.model.request.MemberCreationRequest;
import com.vwit.Librarymanagement.repository.AuthorRepository;
import com.vwit.Librarymanagement.repository.BookRepository;
import com.vwit.Librarymanagement.repository.LendRepository;
import com.vwit.Librarymanagement.repository.MemberRepository;
import com.vwit.Librarymanagement.service.LibraryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService{

	private final AuthorRepository authorRepo;
	private final MemberRepository memberRepo;
	private final LendRepository lendRepo;
	private final BookRepository bookRepo;
	
	@Override
	public Book readBookById(String id) {
		Optional<Book> book=bookRepo.findById(id);
		if(book.isPresent()) {
			return book.get();
		}
		throw new EntityNotFoundException("can't find any book under given ID");
	}

	@Override
	public Iterable<Book> readBooks() {
		return bookRepo.findAll();
	}

	@Override
	public Book readBook(String isbn) {
		 Optional<Book> book = bookRepo.findByIsbn(isbn);
		 if(book.isPresent()) {
			 return book.get();
		 }
		 throw new EntityNotFoundException("can't find any book under given ISBN");
	}

	@Override
	public Book createBook(BookCreationRequest book) {
		Optional<Author> author = authorRepo.findById(book.getAuthorId());
		if(!author.isPresent()) {
			throw new EntityNotFoundException("Author not found");
		}
		Book bookToCreate=new Book();
		BeanUtils.copyProperties(book, bookToCreate);
		bookToCreate.setAuthorId(author.get().getId());
		return bookRepo.save(bookToCreate);
	}

	@Override
	public void deleteBook(String id) {
		bookRepo.deleteById(id);
		
	}

	@Override
	public Member createMember(MemberCreationRequest request) {
		Member member=new Member();
		BeanUtils.copyProperties(request, member);
		member.setStatus(MemberStatus.ACTIVE);
		
		return memberRepo.save(member);
	}

	@Override
	public Member updateMember(String id, MemberCreationRequest request) {
		Optional<Member> optionalMember = memberRepo.findById(id);
		if(!optionalMember.isPresent()) {
			throw new EntityNotFoundException("Member not present in the database");
		}
		Member member =optionalMember.get();
		member.setFirstName(request.getFirstName());
		member.setLastName(request.getLastName());
		return memberRepo.save(member);
	}

	@Override
	public Author createAuthor(AuthorCreationRequest request) {
		Author author=new Author();
		BeanUtils.copyProperties(request, author);
		
		return authorRepo.save(author);
	}

	@Override
	public List<String> lendABook(BookLendRequest request) {
		Optional<Member> memberForId = memberRepo.findById(request.getMemberId());
        if (!memberForId.isPresent()) {
            throw new EntityNotFoundException("Member not present in the database");
        }
        Member member = memberForId.get();
        if (member.getStatus() != MemberStatus.ACTIVE) {
            throw new RuntimeException("User is not active to proceed a lending.");
        }
        List<String> booksApprovedToBurrow = new ArrayList<>();
        request.getBookId().forEach(bookId -> {
            Optional<Book> bookForId = bookRepo.findById(bookId);
            if (!bookForId.isPresent()) {
                throw new EntityNotFoundException("Cant find any book under given ID");
            }
            Optional<Lend> burrowedBook = lendRepo.findByBookANDStatus(bookForId.get(), LendStatus.BORROWED);
            if (!burrowedBook.isPresent()) {
                booksApprovedToBurrow.add(bookForId.get().getName());
                Lend lend = new Lend();
                lend.setMemberId(memberForId.get().getId());
                lend.setBookId(bookForId.get().getId());
                lend.setStatus(LendStatus.BORROWED);
                lend.setStartOn(Instant.now().toString());
                lend.setDueOn(Instant.now().plus(30, ChronoUnit.DAYS).toString());
                lendRepo.save(lend);
            }
        });
        return booksApprovedToBurrow;
    }

}
























