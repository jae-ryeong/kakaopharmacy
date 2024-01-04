package kakao.search.pharmacy.kakaopharmacy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "DIRECTION")
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Direction {    // view에서 보여줄 데이터들
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long directionId;

    private Double distance; // 위치와 약국 사이의 거리

    private String pharmacyName;
    private Double longitude;   // 경도, x
    private Double latitude;    // 위도, y
}
