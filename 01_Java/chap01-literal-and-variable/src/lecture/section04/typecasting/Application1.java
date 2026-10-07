package lecture.section04.typecasting;

public class Application1 {

    public static void main(String[] args) {

        // 자동형변환
        // 값의 범위를 넓히는 변환은 컴파일러가 자동으로 처리해준다
        // 서로 다른 숫자형을 연산할 때 > 더 큰 자료형으로 변환

        byte bnum = 1;
        short snum = bnum;
        int inum = snum;

        System.out.println(inum);

        int num1 = 10;
        long num2 = 20;
        int result = (int) (num1 + num2); //case
       // long result = (num1 + num2);


        //char -> int 로 자동변환
        char ch1 = 'a';
        int chNumber = ch1;
        System.out.println(chNumber);

    }
}

/*
* 연산자
*
*
* */
