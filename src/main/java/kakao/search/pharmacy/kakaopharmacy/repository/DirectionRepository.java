package kakao.search.pharmacy.kakaopharmacy.repository;

import kakao.search.pharmacy.kakaopharmacy.dto.TargetDto;
import kakao.search.pharmacy.kakaopharmacy.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Long> {

    /*@Query("select D.distance, D.longitude, D.latitude from DIRECTION D ORDER BY D.distance asc limit 3")
    public List<TargetDto> pharmacyThreeFilter();*/ // ENTITY로 받기

    @Query("select new kakao.search.pharmacy.kakaopharmacy.dto.TargetDto(D.distance, D.longitude, D.latitude) " +
            "from DIRECTION D ORDER BY D.distance asc limit 3")
    public List<TargetDto> pharmacyThreeFilter();   // DTO로 받기
}
