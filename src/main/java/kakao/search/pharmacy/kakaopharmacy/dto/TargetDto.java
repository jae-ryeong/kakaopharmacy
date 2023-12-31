package kakao.search.pharmacy.kakaopharmacy.dto;

import lombok.Builder;

@Builder
public record TargetDto(
        double distance,
        double targetLongitude,  // 약국 경도, y
        double targetLatitude    // 약국 위도, x
) {
}
