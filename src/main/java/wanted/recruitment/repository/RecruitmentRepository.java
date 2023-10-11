package wanted.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wanted.recruitment.domain.Recruitment;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    @Query("SELECT r.id FROM Recruitment r WHERE r.company.id = :companyId")
    List<Long> recruitmentIdList(@Param("companyId") Long companyId);

    @Query("SELECT r FROM Recruitment r WHERE r.content like %:keyword% OR " +
                "r.position like %:keyword% OR r.skill like %:keyword%")
    List<Recruitment> recruitmentByKeyword(@Param("keyword") String keyword);

}
