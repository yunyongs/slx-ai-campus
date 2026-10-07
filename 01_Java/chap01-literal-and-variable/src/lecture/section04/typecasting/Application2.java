package lecture.section04.typecasting;

public class Application2 {

    public static void main(String[] args) {

        // 강제 형변환
        long longNum = 3000000000L;
        int intNum = (int) longNum;


        System.out.println("longNum = " + longNum);
        System.out.println("intNum = " + intNum);
        
        // 숫자 -> 문자
        int num = 65;
        char ch = (char) num;
        System.out.println("ch = " + ch);
        
        double height = 179.9;
        System.out.println("height = " + height);
        int floorHeight = (int) height;
        System.out.println("floorHeight = " + floorHeight);
    }
}
