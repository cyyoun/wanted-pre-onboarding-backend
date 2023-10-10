package wanted.recruitment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.recruitment.domain.Company;
import wanted.recruitment.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;

    public void save(Company company) {
        repository.save(company);
    }

    public Optional<Company> getCompany(Long id) {
        return repository.findById(id);
    }

    public List<Company> getAllCompany() {
        return repository.findAll();
    }

}
