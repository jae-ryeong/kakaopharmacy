package kakao.search.pharmacy.kakaopharmacy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "pharmacy")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pharmacy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pharmacy_id")
    private Long pharmacyId;

    private String pharmacyName;

    private String pharmacyAddress;

    private String pharmacyTel;

    private Double latitude;    // 위도, y

    private Double longitude;   // 경도, x
}
