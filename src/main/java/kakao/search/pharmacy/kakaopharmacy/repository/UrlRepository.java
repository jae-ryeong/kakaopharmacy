package kakao.search.pharmacy.kakaopharmacy.repository;

import kakao.search.pharmacy.kakaopharmacy.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortUrl(String shortUrl);
    Optional<Url> findByOriginUrl(String originUrl);
}
