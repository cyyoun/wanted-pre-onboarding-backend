package wanted.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.recruitment.domain.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
