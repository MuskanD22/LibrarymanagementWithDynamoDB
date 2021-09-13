package com.vwit.Librarymanagement.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.vwit.Librarymanagement.model.Member;

@EnableScan
public interface MemberRepository extends CrudRepository<Member, String>{

}
