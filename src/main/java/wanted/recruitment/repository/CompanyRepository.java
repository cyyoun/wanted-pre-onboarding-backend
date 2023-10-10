package wanted.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.recruitment.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
