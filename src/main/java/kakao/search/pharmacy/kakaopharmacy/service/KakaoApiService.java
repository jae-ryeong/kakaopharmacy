package kakao.search.pharmacy.kakaopharmacy.service;

import kakao.search.pharmacy.kakaopharmacy.dto.KakaoApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoApiService {

    private final RestTemplate restTemplate;

    private static final String URL = "https://dapi.kakao.com/v2/local/search/address.json";
    private static final String KEY = "d5f0d71ff26e4d59cec7fa873d0e322c";

    public KakaoApiResponseDto KakaoAddressSearch(String address) {

        // URI 생성
        URI uri = UriComponentsBuilder
                .fromHttpUrl(URL)
                .queryParam("query", address)
                .build()
                .encode()
                .toUri();
        System.out.println("uri = " + uri);

        // 헤더에 인증키 값
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        System.out.println("entity = " + entity);

        /*
        kakao API 호출: exchange 함수 매개변수
        1. uri를 String 형태로
        2. Http 메서드
        3. HttpEntity
        4. 반환받을 변수 형식
         */
        return restTemplate.exchange(uri, HttpMethod.GET, entity, KakaoApiResponseDto.class).getBody(); // uri encode를 안하면 uri.toString()으로 매개변수를 넘겨줘야 작동한고 띄어쓰기 인식X
    }
}
