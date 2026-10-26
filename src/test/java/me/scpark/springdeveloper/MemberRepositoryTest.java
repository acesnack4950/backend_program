package me.scpark.springdeveloper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Sql("/insert-members.sql")
    @DisplayName("members 테이블의 모든 레코드 가져오기")
    @Test
    void getAllMembers() {
        // when
        List<Member> members = memberRepository.findAll();

        // then
        assertThat(members.size()).isEqualTo(3);
    }

    @Sql("/insert-members.sql")
    @DisplayName("id로 회원 조회")
    @Test
    void getMemberById() {
        // when
        Member member = memberRepository.findById(2L).get();

        // then
        assertThat(member.getName()).isEqualTo("B");
    }

    @Sql("/insert-members.sql")
    @DisplayName("이름으로 회원 조회")
    @Test
    void getMemberByName() {
        // when
        Member member = memberRepository.findByName("C").get();

        // then
        assertThat(member.getId()).isEqualTo(3L);
    }

    @Sql("/insert-members.sql")
    @DisplayName("@Query(JPQL)로 이름 조회")
    @Test
    void getMemberByNameQuery() {
        // when
        Member member = memberRepository.findByNameQuery("A").get();

        // then
        assertThat(member.getId()).isEqualTo(1L);
    }

    @DisplayName("회원 1명 저장")
    @Test
    void saveMember() {
        // given
        Member member = new Member("A");

        // when
        Member saved = memberRepository.save(member);

        // then
        assertThat(memberRepository.findById(saved.getId()).get().getName()).isEqualTo("A");
    }

    @DisplayName("회원 여러 명 저장")
    @Test
    void saveMembers() {
        // given
        List<Member> members = List.of(new Member("B"), new Member("C"));

        // when
        memberRepository.saveAll(members);

        // then
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }

    @Sql("/insert-members.sql")
    @DisplayName("id로 회원 삭제")
    @Test
    void deleteMemberById() {
        // when
        memberRepository.deleteById(2L);

        // then
        assertThat(memberRepository.findById(2L).isEmpty()).isTrue();
    }

    @Sql("/insert-members.sql")
    @DisplayName("전체 회원 삭제")
    @Test
    void deleteAll() {
        // when
        memberRepository.deleteAll();

        // then
        assertThat(memberRepository.findAll().size()).isZero();
    }

    @Sql("/insert-members.sql")
    @DisplayName("회원 이름 수정 (변경 감지)")
    @Test
    void update() {
        // given
        Member member = memberRepository.findById(2L).get();

        // when
        member.changeName("BC");

        // then
        assertThat(memberRepository.findById(2L).get().getName()).isEqualTo("BC");
    }
}