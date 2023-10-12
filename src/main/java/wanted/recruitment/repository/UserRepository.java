package wanted.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.recruitment.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
