package lecture.section01.enumType;

public class Application {

    public static void main(String[] args) {
        Subjects subject1 = Subjects.JAVA;
        Subjects subject2 = Subjects.HTML;
        Subjects subject3 = Subjects.JAVA;

        if (subject1 == subject3) {
            System.out.println("같음");
        } else {
            System.out.println("다름");
        }

        switch (subject3) {
            case JAVA :
            case MYSQL:
            case JDBC:
                System.out.println("백엔드입니다.");
                break;
            case HTML:
            case CSS:
            case JAVASCRIPT:
                System.out.println("프런트엔드입니다.");
                break;
        }
        printSubject(subject3);
    }
    public static void printSubject(Subjects subject) {
        System.out.println(subject.name());
    }


}
