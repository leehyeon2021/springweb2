package practice6;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    @PostMapping("/admin")
    public ResponseEntity<?> admin(){
        return ResponseEntity.ok(carService.admin());
    }

    // {"평균연비": 14.5,"누적주행거리키로": 120000,"출고후경과월수": 84,"사고감가건수": 1,"소유자변경횟수": 2}
    @PostMapping("/user")
    public ResponseEntity<?> prediction(@RequestBody CarDto carDto){
        return ResponseEntity.ok( carService.prediction( carDto ) );
    }

    @GetMapping("/python")
    public ResponseEntity<?> python(){
        WebClient webClient = WebClient.create();
        List response = webClient.get()
                .uri("http://localhost:8000/api/car")
                .retrieve()
                .bodyToMono( List.class )
                .block();
        return ResponseEntity.ok( response );
    }

}
