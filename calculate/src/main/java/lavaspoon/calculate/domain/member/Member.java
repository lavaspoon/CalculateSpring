package lavaspoon.calculate.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public  class Member {
    private long id;
    private String name;
    private int age;
    //기본 생성자
    public Member() {
    }

    //생성자
    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
