package kakao.search.pharmacy.kakaopharmacy.repository;

import kakao.search.pharmacy.kakaopharmacy.dto.TargetDto;
import kakao.search.pharmacy.kakaopharmacy.entity.Direction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DirectionRepositoryTest {

    @Autowired
    private DirectionRepository directionRepository;

/*    @Test
    void pharmacyThreeFilter() {
        Direction direction1 = Direction.builder()
                .distance(7.89)
                .latitude(1.2)
                .longitude(1.2)
                .build();
        Direction direction2 = Direction.builder()
                .distance(8.89)
                .latitude(1.2)
                .longitude(1.2)
                .build();
        Direction direction3 = Direction.builder()
                .distance(9.89)
                .latitude(1.2)
                .longitude(1.2)
                .build();
        Direction direction4 = Direction.builder()
                .distance(10.89)
                .latitude(1.2)
                .longitude(1.2)
                .build();

        directionRepository.save(direction1);
        directionRepository.save(direction2);
        directionRepository.save(direction3);
        directionRepository.save(direction4);

        List<Direction> directions = directionRepository.pharmacyThreeFilter();

        assertThat(directions.size()).isEqualTo(3);
        assertThat(directions.get(0).getDistance()).isEqualTo(7.89);
        assertThat(directions.get(2).getDistance()).isEqualTo(9.89);
    }*/

    @Test
    void pharmacyThreeFilter2() {
        Direction direction1 = Direction.builder()
                .distance(7.89)
                .pharmacyName("약국1")
                .latitude(1.2)
                .longitude(1.2)
                .build();
        Direction direction2 = Direction.builder()
                .distance(10.89)
                .pharmacyName("약국2")
                .latitude(1.2)
                .longitude(1.2)
                .build();
        Direction direction3 = Direction.builder()
                .distance(9.89)
                .pharmacyName("약국3")
                .latitude(1.2)
                .longitude(1.2)
                .build();
        Direction direction4 = Direction.builder()
                .distance(10.89)
                .pharmacyName("약국4")
                .latitude(1.2)
                .longitude(1.2)
                .build();

        directionRepository.save(direction1);
        directionRepository.save(direction2);
        directionRepository.save(direction3);
        directionRepository.save(direction4);

        List<TargetDto> directions = directionRepository.pharmacyThreeFilter();

        assertThat(directions.size()).isEqualTo(2);
        assertThat(directions.get(0).distance()).isEqualTo(7.89);
        //assertThat(directions.get(2).distance()).isEqualTo(9.89);
    }
}