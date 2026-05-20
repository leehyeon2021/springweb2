package practice6;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "중고차매매데이터")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer 차량번호ID;
    private Double 평균연비;
    private Integer 누적주행거리키로;
    private Integer 출고후경과월수;
    private Integer 사고감가건수;
    private Integer 소유자변경횟수;
    private Integer 매매가격만원;

    public CarDto toDto( ){
        return CarDto.builder()
                .평균연비( this.평균연비 ).누적주행거리키로( this.누적주행거리키로 ).출고후경과월수( this.출고후경과월수 )
                .사고감가건수( this.사고감가건수 ).소유자변경횟수( this.소유자변경횟수 ).매매가격만원( this.매매가격만원 )
                    .build();
    }
}
