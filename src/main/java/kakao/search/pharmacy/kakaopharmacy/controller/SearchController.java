package kakao.search.pharmacy.kakaopharmacy.controller;

import kakao.search.pharmacy.kakaopharmacy.dto.OutputDto;
import kakao.search.pharmacy.kakaopharmacy.dto.TargetDto;
import kakao.search.pharmacy.kakaopharmacy.service.DistanceService;
import kakao.search.pharmacy.kakaopharmacy.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SearchController {

    private final DistanceService distanceService;
    private final UrlService urlService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

/*    @PostMapping("/search")
    public ModelAndView searchAddress(@RequestParam(name = "address") String address) {

        List<TargetDto> targetDtos = distanceService.SearchPharmacy(address);
        List<String> shortDirectionUrl = null;
        for (TargetDto targetDto : targetDtos) {
            String url = urlService.generateShorteningUrl(targetDto.directionUrl());
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        modelAndView.addObject("outputFormList", targetDtos);

        return modelAndView;
    }*/

    @PostMapping("/search")
    public ModelAndView searchAddress(@RequestParam(name = "address") String address) {

        List<OutputDto> outputDtos = distanceService.ListOutputDto(address);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        modelAndView.addObject("outputFormList", outputDtos);

        return modelAndView;
    }
}
