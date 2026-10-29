package section01.aop;

public interface MemberService {

    String processMember(boolean error);

    void selectMember();

    void selectMembers();

    void checkMember();

    void registerMember(String name, int age);

    void updateMember();
}
