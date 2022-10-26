package com.ksy.ecommerce.entity;

import com.ksy.ecommerce.dto.MemberFormDto;
import com.ksy.ecommerce.repository.CartRepository;
import com.ksy.ecommerce.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class CartTest {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    public Member createMemberforCart() {

        MemberFormDto memberFormDto = MemberFormDto.builder().email("kkk@test.com")
                .name("kkk")
                .address("seoul")
                .password("1234")
                .build();

        return Member.createMember(memberFormDto,passwordEncoder);
    }

    @Test
    @DisplayName("find cart test")
    public void findCartAndMemberTest() {
        Member member = createMemberforCart();

        memberRepository.save(member);
        Cart cart = new Cart();
        cart.setMember(member);

        cartRepository.save(cart);
        em.flush(); //트랜젝션이 끝나야 flush 하기 때문에 강제로

        em.clear(); //영속성 컨텍스트에 엔티티가 없을 경우 find가 가능하기 때문에
        Cart savedCart = cartRepository.findById(cart.getId()).orElseThrow(EntityNotFoundException::new );
        assertEquals(savedCart.getMember().getId(), member.getId());
    }
}