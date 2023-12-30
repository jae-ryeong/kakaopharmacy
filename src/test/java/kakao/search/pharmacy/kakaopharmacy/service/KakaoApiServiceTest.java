package kakao.search.pharmacy.kakaopharmacy.service;

import kakao.search.pharmacy.kakaopharmacy.dto.KakaoApiResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class KakaoApiServiceTest {

/*    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private KakaoApiService kakaoApiService;*/

    @Autowired
    private KakaoApiService kakaoApiService;

    @Test
    public void KakaoAddressSearch() throws Exception{
        //given


        //when
        KakaoApiResponseDto test = kakaoApiService.KakaoAddressSearch("동산초교로46");

        //then
        System.out.println("test = " + test);
    }
}