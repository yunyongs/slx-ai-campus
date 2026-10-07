package lecture.section03.overflow;

public class Application1 {

    public static void main(String[] args) {
        // 각 자료형마다 표현할 수 있는 범위가 있는데, 이 범위를 넘어설 경우

        byte num1 = 127;
        System.out.println("증가 전:" + num1);
        num1++; //num1 = num1 +1의 의미 > 128로 증가함!
        System.out.println("증가 후:" + num1); //-128

        int inum = 1000000;
        int inum2 = 700000;
        System.out.println(inum * inum2); //int 범위를 벗어나 -결과가 나옴

//        long longMulti = inum * inum2;
        System.out.println(inum * inum2); //int 범위를 벗어나 -결과가 나옴

    }


}
