package kakao.search.pharmacy.kakaopharmacy.service;

import kakao.search.pharmacy.kakaopharmacy.dto.DocumentDto;
import kakao.search.pharmacy.kakaopharmacy.dto.KakaoApiResponseDto;
import kakao.search.pharmacy.kakaopharmacy.dto.OutputDto;
import kakao.search.pharmacy.kakaopharmacy.dto.TargetDto;
import kakao.search.pharmacy.kakaopharmacy.entity.Direction;
import kakao.search.pharmacy.kakaopharmacy.entity.Pharmacy;
import kakao.search.pharmacy.kakaopharmacy.repository.DirectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistanceService {

    private final KakaoApiService kakaoApiService;
    private final DirectionRepository directionRepository;
    private final PharmacyService pharmacyService;
    private final UrlService urlService;

    private static final String ROAD_VIEW_URL = "https://map.kakao.com/link/roadview/";
    private static final String SEARCH_URL =  "https://map.kakao.com/link/map/";
    private static final String SHORT_URL = "http://localhost:8080/dir/";

    public List<TargetDto> SearchPharmacy(String Address) { // LongUrl

        if(Objects.isNull(Address)) {
            log.error("[DistanceService PharmacyDistance의 Address가 null값 이다.]");
            return null;
        }

        KakaoApiResponseDto kakaoApiResponseDto = kakaoApiService.KakaoAddressSearch(Address);
        DocumentDto documentDto = kakaoApiResponseDto.documentList().get(0);

        return ListTargetDto(documentDto);
    }

    public List<OutputDto> ListOutputDto(String Address) {  // ShortUrl

        if(Objects.isNull(Address)) {
            log.error("[DistanceService PharmacyDistance의 Address가 null값 이다.]");
            return null;
        }

        KakaoApiResponseDto kakaoApiResponseDto = kakaoApiService.KakaoAddressSearch(Address);
        DocumentDto documentDto = kakaoApiResponseDto.documentList().get(0);

        return ListTargetDto(documentDto).stream().map(targetDto ->
                OutputDto.builder()
                        .distance(targetDto.distance())
                        .targetName(targetDto.targetName())
                        .targetAddress(targetDto.targetAddress())
                        .roadViewShortUrl(SHORT_URL + urlService.generateShorteningUrl(targetDto.roadViewUrl()))
                        .directionShortUrl(SHORT_URL + urlService.generateShorteningUrl(targetDto.directionUrl()))
                        .build()).collect(Collectors.toList());
    }

    public void SaveDirection(String Address) { // Address를 받아와서 Direction을 저장만 한다.

        if(Objects.isNull(Address)) {
            log.error("[DistanceService SaveDirection의 Address가 null값 이다.]");
        }

        KakaoApiResponseDto kakaoApiResponseDto = kakaoApiService.KakaoAddressSearch(Address);
        Double myLongitude = kakaoApiResponseDto.documentList().get(0).longitude(); // x
        Double myLatitude = kakaoApiResponseDto.documentList().get(0).latitude();   // y

        List<Pharmacy> pharmacyList = pharmacyService.findAll();

        for (Pharmacy pharmacy : pharmacyList) {
            Double distance = HaversineDistance(myLatitude, myLongitude, pharmacy.getLatitude(), pharmacy.getLongitude());
            directionRepository.save(Direction.builder()
                    .distance(distance)
                    .pharmacyName(pharmacy.getPharmacyName())
                    .longitude(pharmacy.getLongitude())
                    .latitude(pharmacy.getLatitude())
                    .build());
        }
    }

    public List<TargetDto> SelectTargetDtosAndDelete() {    // 가장 가까운 약국 3곳을 출력 후 모든 direction 삭제
        List<TargetDto> targetDtos = directionRepository.pharmacyThreeFilter();

        directionRepository.deleteAll();
        return targetDtos;
    }

    public Double HaversineDistance(double x1, double y1, double x2, double y2) {   // 실수로 거꾸로 썼다. x가 위도(latitude) y가 경도(longitude)이다
        double distance;
        final double radius = 6371; // 지구 반지름
        final double toRadian = Math.PI / 180;

        double deltaLatitude = Math.abs(x1 - x2) * toRadian;
        double deltaLongitude = Math.abs(y1 - y2) * toRadian;

        double sinDeltaLat = Math.sin(deltaLatitude / 2);
        double sinDeltaLng = Math.sin(deltaLongitude / 2);
        double squareRoot = Math.sqrt(
                sinDeltaLat * sinDeltaLat +
                        Math.cos(x1 * toRadian) * Math.cos(x2 * toRadian) * sinDeltaLng * sinDeltaLng);

        distance = 2 * radius * Math.asin(squareRoot);

        return distance * 1000;  // m 단위로 환산
    }

    private List<TargetDto> ListTargetDto(DocumentDto documentDto) {
        return pharmacyService.findAll().
                stream().map(pharmacy ->
                        TargetDto.builder()
                                .distance(Math.round(HaversineDistance(documentDto.latitude(), documentDto.longitude(), pharmacy.getLatitude(), pharmacy.getLongitude())))
                                .targetName(pharmacy.getPharmacyName())
                                .targetAddress(pharmacy.getPharmacyAddress())
                                .roadViewUrl(ROAD_VIEW_URL + pharmacy.getLatitude() + "," + pharmacy.getLongitude())  //
                                .directionUrl(SEARCH_URL + pharmacy.getPharmacyName() + "," + pharmacy.getLatitude() + "," + pharmacy.getLongitude())
                                .build())
                .filter(targetDto -> targetDto.distance() <= 1000)
                .sorted(Comparator.comparing(TargetDto::distance))
                .limit(3)
                .collect(Collectors.toList());
    }
}
