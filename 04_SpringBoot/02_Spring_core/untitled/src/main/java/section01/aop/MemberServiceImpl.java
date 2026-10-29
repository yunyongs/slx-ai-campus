package section01.aop;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    @Override
    public String processMember(boolean error) {

        System.out.println("[Target] 회원처리");

        if(error) {
            // 예외 발생
            throw new IllegalStateException("회원처리중 오류 발생");
        }

        return "회원 처리 완료";
    }

    // execution으로 AOP 적용해 보기
    @Override
    public void selectMember() {
        System.out.println("[Target] SelectMember: execution으로 적용하기");

    }

    @Override
    public void selectMembers() {

        System.out.println("[Target] SelectMembers");
    }

    @Override
    public void checkMember() {

        System.out.println("[Target] CheckMember");
    }

    @Override
    public void registerMember(String name, int age) {

        System.out.println("[Target] RegisterMember");
    }

    @Override
    @AopLog
    public void updateMember() {

        System.out.println("[Target] UpdateMember");
    }
}
