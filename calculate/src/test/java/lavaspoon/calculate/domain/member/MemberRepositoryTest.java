package lavaspoon.calculate.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    //싱글톤이기 때문에 이렇게 아래처럼 하면 안됨
    //MemberRepository memberRepository = new MemberRepository();
    MemberRepository memberRepository = MemberRepository.getInstance();
    //ps: 서블릿이 아닌 스프링을 쓰면 싱글톤을 쓸필요 없다. 스프링은 싱글톤은 보장해주기 때문에

    //테스트가 끝날떄마다 깔끔하게 초기화
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("hello",28);
        //when
        Member saveMember = memberRepository.save(member);
        //then
        Member findMember = memberRepository.findById(saveMember.getId());
        org.assertj.core.api.Assertions.assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findById() {
        //given

        //when

        //then
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("lavaspoon", 28);
        Member member2 = new Member("jomaseon", 27);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
        //result 에 member1,2 가 포함 됐는지 테스트
        org.assertj.core.api.Assertions.assertThat(result).contains(member1,member2);
    }
}