package kakao.search.pharmacy.kakaopharmacy.service;

import kakao.search.pharmacy.kakaopharmacy.dto.OutputDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DistanceServiceTest {

    @Autowired
    private DistanceService distanceService;

    @Test
    void haversineDistance() {
        Double distance = distanceService.HaversineDistance(36.3473473012438, 127.425145621839, 36.3510772106727, 127.426549988776);
        System.out.println("distance = " + distance);
    }

    @DisplayName("address Null 테스트")
    @Test
    public void listOutputDtoNullTest() throws Exception{
        //given
        String address = null;

        //when
        List<OutputDto> outputDtos = distanceService.ListOutputDto(address);

        //then
        assertThat(outputDtos).isNull();
    }

    @DisplayName("정상적인 address 값 테스트")
    @Test
    public void listOutputDtoTest() throws Exception{
        //given
        String address = "대전광역시 서구 둔산로 100";

        //when
        List<OutputDto> outputDtos = distanceService.ListOutputDto(address);

        //then
        assertThat(outputDtos.size()).isEqualTo(3);
    }
}