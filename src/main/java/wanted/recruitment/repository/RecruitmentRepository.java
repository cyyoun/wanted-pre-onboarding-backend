package wanted.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.recruitment.domain.Recruitment;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
}
