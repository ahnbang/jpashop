package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // @Service 어노테이션에 @component가 붙어있기때문에, @Service 어노테이션 선언시, 자동으로 컴포넌트 스캔 대상이 된다. -> 자동으로 스프링 빈으로 등록되다.
//@Transactional // : 트랜잭션, 영속성 컨텍스트, jpa어떤 데이터 변경이나 로직은 트랜잭션안에서 수행되어야함으로 해당 어노테이션 필요, 트랜잭션 해주면 기본적으로 public 메서드들은 트랜잭션에 걸리게됨
@Transactional(readOnly = true) // 이경우 jpa가 조회하는데 있어 최적화, 결론 조회같은 읽기는 해당 어노테이션 추가해줄것
@RequiredArgsConstructor // final로 선언된 녀석만 가지고 생성자 자동 생성
public class MemberService {

   //@Autowired 스프링이  스프링 빈에 등록되어 있는 MemberRepositroy에 의존성 주입, 이경우 필드 인젝션 -> 주입이 어려움, 변경도 어려움
    private final MemberRepository memberRepository;

    /* 아래는 setter 주입, 위와다르게 메서드를 통해서 주입을 수행함. 주입이 쉽고, 변경도 쉽다. 하지만 프로그램 돌아가는 시점에 누군가가 이걸 바꿀 수 있음....

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

     */

    //아래는 생성자 주입, 이게 제일 best임.
    /*
    @Autowired
    public MemberService (MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    */




    //회원가입
    @Transactional // 쓰기에는 readonly=True 해주면 안됨~ -> 해주게되면 데이터 변경이 안됨
    public Long join(Member member){
         validateDuplicateMember(member); // 중복 회원 검증
         memberRepository.save(member);
         return member.getId();
    }

    //중복회원 검증 메소드 : 중복회원일시 예외 터트리기
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    //회원 전체 조회
    //@Transactional(readOnly = true) // 이경우 jpa가 조회하는데 있어 최적화, 결론 조회같은 읽기는 해당 어노테이션 추가해줄것
    public List<Member> findMembers(){
        return memberRepository.findALL();
    }

    //회원 단일 조회
    //@Transactional(readOnly = true) // 이경우 jpa가 조회하는데 있어 최적화
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
