package kakao.search.pharmacy.kakaopharmacy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record DocumentDto(
        @JsonProperty("address_name")
        String addressName,     // 내 위치
        @JsonProperty("x")
        Double longitude,       // 내 경도, y
        @JsonProperty("y")
        Double latitude         // 내 위도, x
) {
}
