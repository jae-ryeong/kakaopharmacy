package kakao.search.pharmacy.kakaopharmacy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}