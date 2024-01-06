package kakao.search.pharmacy.kakaopharmacy.service;

import kakao.search.pharmacy.kakaopharmacy.entity.Url;
import kakao.search.pharmacy.kakaopharmacy.repository.UrlRepository;
import kakao.search.pharmacy.kakaopharmacy.util.Base62;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final static int GENERATE_COUNT = 5; //해시코드 충돌시 재생성 횟수
    private final Base62 base62;

    private final UrlRepository urlRepository;

    @Transactional
    public String getOriginUrlByShortUrl(String shortUrl) { // 에러처리 해주기
        Optional<Url> originUrl = urlRepository.findByShortUrl(shortUrl);
        return originUrl.map(Url::getOriginUrl).orElse(null);
    }

    @Transactional
    public String generateShorteningUrl(String originUrl){
        Optional<Url> shortUrl = urlRepository.findByShortUrl(originUrl);
        if (shortUrl.isPresent()) {
            //생성 요청한 원본 url이 존재 할 경우 기존에 생성된 alias 로 제공
            return shortUrl.get().getShortUrl();
        }
        return generate(originUrl);
    }

    private String generate(String originUrl){
        String shorteningUrl;
        for (int i = 0; i < GENERATE_COUNT; i++) {
            shorteningUrl = base62.generateHashingAlias(originUrl + i);

            if (!urlRepository.findByShortUrl(shorteningUrl).isPresent() && shorteningUrl.length() < 8) {
                Url url = new Url();
                urlRepository.save(
                        Url.builder()
                        .shortUrl(shorteningUrl)
                        .originUrl(originUrl)
                        .build());
                return shorteningUrl;
            }
        }
        return "URL을 생성하지 못했습니다.";
    }

    @Transactional
    public void deleteAll() {
        urlRepository.deleteAll();
    }
}
