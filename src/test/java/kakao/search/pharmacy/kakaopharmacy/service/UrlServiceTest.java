package kakao.search.pharmacy.kakaopharmacy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlServiceTest {

    @Autowired
    private UrlService urlService;

    @Test
    void generateShorteningUrl() {
        //given
        String oriUrl = "www.naver.com";

        //when
        String shorteningUrl = urlService.generateShorteningUrl(oriUrl);

        //then
        assertThat(oriUrl).isNotEqualTo(shorteningUrl);
        System.out.println("shorteningUrl = " + shorteningUrl);
    }
}