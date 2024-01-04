package kakao.search.pharmacy.kakaopharmacy.dto;

import lombok.Builder;

@Builder
public record TargetDto(
        double distance,
        String targetName,
        String targetAddress,
        String directionUrl,
        String roadViewUrl
) {
}
