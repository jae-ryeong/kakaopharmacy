package kakao.search.pharmacy.kakaopharmacy.service;

import kakao.search.pharmacy.kakaopharmacy.entity.Url;
import kakao.search.pharmacy.kakaopharmacy.repository.UrlRepository;
import kakao.search.pharmacy.kakaopharmacy.util.Base62;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlService {
    private final static int GENERATE_COUNT = 5; //해시코드 충돌시 재생성 횟수
    private final Base62 base62;

    private final UrlRepository urlRepository;

    @Transactional
    public String getOriginUrlByShortUrl(String shortUrl) { // TODO: 에러처리 해주기
        Optional<Url> originUrl = urlRepository.findByShortUrl(shortUrl);
        String url = originUrl.map(Url::getOriginUrl).orElse(null); // 만약 url이 null 값이면 alert창 띄어주기?? 아니면

        log.info("[UrlService getOriginUrlByShortUrl] url : {}", url);

        return UriComponentsBuilder.fromHttpUrl(Objects.requireNonNull(url)).toUriString();
    }

    @Transactional
    public String generateShorteningUrl(String originUrl){
        Optional<Url> findOriginUrl = urlRepository.findByOriginUrl(originUrl);
        if(findOriginUrl.isPresent()){
            return findOriginUrl.get().getShortUrl();
        }
        return generate(originUrl);
    }

    private String generate(String originUrl){String shorteningUrl;
        for (int i = 0; i < GENERATE_COUNT; i++) {
            shorteningUrl = Base62.generateHashingAlias(originUrl + i);

            if (urlRepository.findByShortUrl(shorteningUrl).isEmpty() && shorteningUrl.length() < 8) {
                urlRepository.save(
                        Url.builder()
                        .shortUrl(shorteningUrl)
                        .originUrl(originUrl)
                        .build());
                return shorteningUrl;
            }
        }
        return "URL을 생성하지 못했습니다.";  // TODO: 에러처리?
    }

    @Transactional
    public void deleteAll() {
        urlRepository.deleteAll();
    }
}
