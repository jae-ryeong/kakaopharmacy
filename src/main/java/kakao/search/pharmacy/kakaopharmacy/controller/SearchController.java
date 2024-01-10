package kakao.search.pharmacy.kakaopharmacy.controller;

import kakao.search.pharmacy.kakaopharmacy.dto.OutputDto;
import kakao.search.pharmacy.kakaopharmacy.service.DistanceService;
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

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @PostMapping("/search")
    public ModelAndView searchAddress(@RequestParam(name = "address") String address) {

        List<OutputDto> outputDtos = distanceService.ListOutputDto(address);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        modelAndView.addObject("outputFormList", outputDtos);

        return modelAndView;
    }
}
