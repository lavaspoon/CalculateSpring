package lavaspoon.calculate.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    //Key id(Long), Value name,age(Member)
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    //[싱글톤]
    private static final MemberRepository instance = new MemberRepository();
    //무조건 외부에서는 getInastance로 조회하게 함
    public static MemberRepository getInstance(){
        return instance;
    }
    //싱글톤으로 할떄는 private 으로 아무나 생성하지 못하게 막아야함
    private MemberRepository() {
    }


    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

     public Member findById(Long id){
        return store.get(id);
     }

     public List<Member> findAll(){
        return new ArrayList<>(store.values());
     }
     //테스트용
     public void clearStore(){
        store.clear();
     }
}
