package practice6;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public boolean admin( ){
        List<CarEntity> entityList = carRepository.findAll();
        System.out.println(entityList);
        List<CarDto> dtoList = entityList.stream().map( CarEntity :: toDto ).toList();
        System.out.println("dtoList.size = " + dtoList.size());
        WebClient webClient = WebClient.create();
        Object object = webClient.post()
                .uri("http://localhost:8000/api/model/admin")
                .bodyValue( dtoList )               // JPA에서 꺼내온 DTO 보냄: 파이썬에게 보낼 자료
                .retrieve()
                .bodyToMono( Object.class )         // 응답자료 타입 : `타입명.class`
                .block(); // 동기화
        return true;
    }

    public Object prediction(CarDto carDto){
        System.out.println("carDto = " + carDto);
        WebClient webClient = WebClient.create();
        Object ob = webClient.post()
                .uri("http://localhost:8000/api/model/user")
                .bodyValue( carDto )
                .retrieve()
                .bodyToMono( Object.class )
                .block();
        return ob;
    }
}
