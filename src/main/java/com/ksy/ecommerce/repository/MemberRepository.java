package com.ksy.ecommerce.repository;

import com.ksy.ecommerce.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
