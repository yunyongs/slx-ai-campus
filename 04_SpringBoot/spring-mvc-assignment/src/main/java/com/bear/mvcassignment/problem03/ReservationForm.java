package com.bear.mvcassignment.problem03;

/**
 * 예약 폼의 입력값을 하나로 묶어 전달하는 DTO(Data Transfer Object)이다.
 * applicant(신청자), roomName(스터디룸), hours(이용 시간), price(시간당 요금)을 담는다.
 * @ModelAttribute로 폼 값을 바인딩할 때 사용한다. 완성된 제공 코드이므로 수정하지 않는다.
 */
public class ReservationForm {
    private String applicant;
    private String roomName;
    private int hours;
    private int price;

    public ReservationForm() {
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
