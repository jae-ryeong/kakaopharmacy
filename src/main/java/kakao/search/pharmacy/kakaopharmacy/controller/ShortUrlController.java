package kakao.search.pharmacy.kakaopharmacy.controller;

import kakao.search.pharmacy.kakaopharmacy.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ShortUrlController {

    private final UrlService urlService;

    @GetMapping("/dir/{shortUrl}")
    public String searchUrl(@PathVariable("shortUrl") String shortUrl) {
        String url = urlService.getOriginUrlByShortUrl(shortUrl);

        if (url.isBlank()){ // 만약 url이 정상적으로 생성되지 않았다면 메인페이지로 return
            return "redirect:/";
        }

        return "redirect:" + url;
    }

    @GetMapping("/delete/delete/all")
    public String deleteUrl() {
        urlService.deleteAll();
        return "redirect:/";
    }
}
