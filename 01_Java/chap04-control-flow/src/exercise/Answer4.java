package exercise;

import java.util.Scanner;

public class Answer4 {
    /* Q3. 문자열을 입력받아 각 알파벳을 일정한 거리만큼 밀어서 다른 알파벳으로 바꾸는 시저 암호를 작성하세요.
     * 단, 입력된 문자열은 영문자와 공백으로만 이루어져야 하며, 밀리는 숫자는 0보다 큰 정수입니다.
     *
     * -- 입력 예시 --
     * 문자열을 입력하세요 : a B z
     * 숫자를 입력하세요 : 4
     *
     * -- 출력 예시 --
     * e F d
     * */

    public void shiftPass() {
        Scanner sc = new Scanner(System.in);

        System.out.print("문자열을 입력하세요 : ");
        String text = sc.nextLine();
        System.out.print("숫자를 입력하세요 : ");
        int num = sc.nextInt();

        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c >= 'a' && c <= 'z') {
                c = (char) ('a' + (c - 'a' + num) % 26);

            } else if (c >= 'A' && c <= 'Z') {
                c = (char) ('A' + (c - 'A' + num) % 26);

            }
            result += c;

        }
        System.out.println(result);


    }

}
