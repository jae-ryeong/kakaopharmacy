package kakao.search.pharmacy.kakaopharmacy.dto;

import lombok.Builder;

@Builder
public record OutputDto(
        double distance,
        String targetName,
        String targetAddress,
        String directionShortUrl,
        String roadViewShortUrl
) {
}
