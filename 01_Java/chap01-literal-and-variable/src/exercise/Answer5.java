package exercise;

public class Answer5 {
    public static void main(String[] args) {
        /*
         * 문제 5. 형변환을 이용한 평균 계산
         *
         * 국어 80점, 영어 75점, 수학 90점을 int 변수에 저장한다.
         * 총점을 int 변수에 저장하고, 평균이 실수로 나오도록 명시적 형변환을 사용한다.
         * 문자 'A'를 char 변수에 저장한 뒤 int로 자동 형변환하여 유니코드 값을 출력한다.
         *
         * 실행 결과
         * 총점 : 245
         * 평균 : 81.66666666666667
         * A의 유니코드 값 : 65
         */
        int ko = 80;
        int eng = 75;
        int math = 90;
        
        int sum  = ko+eng+math;
        double avg = (double) sum/3;
        System.out.printf("평균 : %.2f%n", avg);
        char ch = 'A';
        System.out.println("A의 유니코드 값 : " + (int) ch);
                
                
    }
}
