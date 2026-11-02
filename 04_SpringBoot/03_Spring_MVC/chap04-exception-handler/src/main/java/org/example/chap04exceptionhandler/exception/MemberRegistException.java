package org.example.chap04exceptionhandler.exception;

//사용자 정의 예외 처리
public class MemberRegistException extends Exception{

    //부모로 예외 메시지 저장 가능
    public MemberRegistException(String message) {
        super(message);
    }
}
