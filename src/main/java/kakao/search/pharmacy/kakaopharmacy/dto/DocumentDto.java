package kakao.search.pharmacy.kakaopharmacy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DocumentDto(
        @JsonProperty("address_name")
        String addressName,
        @JsonProperty("x")
        Double longitude,
        @JsonProperty("y")
        Double latitude
) {
}
