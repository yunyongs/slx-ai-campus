package org.example.requestmapping.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order/*")
public class ClassMappingTestController {

    @GetMapping("/regist")
    public String registOrder(Model model) {

        model.addAttribute("message", "/order/regist 요청 결과");
        return "mappingResult";
    }

    // 여러개의 요청 매핑 : @RequestMapping의 value 속성에 중괄호를 이용해 매핑할 URL을 나열 가능
    // order/modify
    // order/delete
    @RequestMapping(value = {"/modify", "/delete"}, method = {RequestMethod.POST})
    public String modifyAndDelete(Model model) {

        model.addAttribute("message", "/order/modify와 /order/delete 요청 결과");
        return "mappingResult";
    }

    // PathVariable: url에서 변수를 받아올 수 있음
    // 조회, 특히 단건 조회할 때
    // @PathVariable(변수명) 타입 변수명
    // @PathVariable 타입 Path에 적힌 변수명
    @GetMapping("/detail/{orderNo}")
    //public String selectOrderDetail(Model model, @PathVariable("orderNo") int a) {
    public String selectOrderDetail(Model model, @PathVariable int orderNo) {

        model.addAttribute("message", orderNo + "번 주문 상세 조회합니다.");
        return "mappingResult";
    }

    //그외 모든 리퀘스트 요청 처리
    @RequestMapping("/*")
    public String anyRequest(Model model) {

        model.addAttribute("message", "모든 요청 처리");
        return "mappingResult";
    }
}
