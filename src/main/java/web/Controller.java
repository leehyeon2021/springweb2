package web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    // 1. 브라우저 -> 파이썬 -> 스프링
    @GetMapping("/api/product")
    public ResponseEntity<?> product(){
        // 파이썬에게 전달할 임의값
        Map<String,Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("name", "제로콜라");
        map.put("price", 2000);
        return ResponseEntity.ok( map );
    }

    // 2. `/product`는 파이썬이랑 스프링이랑 통신, `/products`는 파이썬이랑 브라우저랑 통신
    @GetMapping("/api/pyton")
    public ResponseEntity<?> python(){

        // 1) HTTP 통신 가능한 객체
        WebClient webClient = WebClient.create();
        // 2) HTTP 헤더 정보
        List response = webClient.get() // HTTP GET METHOD
                .uri("http://localhost:8000/api/products")  // 파이썬 주소
                .retrieve()                                     // 응답 받기
                .bodyToMono( List.class )                       // 응답 받은 자료 타입 변환
                .block();                                       // 동기화

        return ResponseEntity.ok( response );

    }
}
