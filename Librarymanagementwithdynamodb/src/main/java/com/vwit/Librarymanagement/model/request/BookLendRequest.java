package com.vwit.Librarymanagement.model.request;

import java.util.List;

import lombok.Data;

@Data
public class BookLendRequest {
	private List<String> bookId;
	private String memberId;
}
