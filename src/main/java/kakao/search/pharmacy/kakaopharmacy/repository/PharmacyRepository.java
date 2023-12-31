package kakao.search.pharmacy.kakaopharmacy.repository;

import kakao.search.pharmacy.kakaopharmacy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
