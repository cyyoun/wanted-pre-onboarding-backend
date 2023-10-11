package wanted.recruitment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.recruitment.domain.Recruitment;
import wanted.recruitment.repository.CompanyRepository;
import wanted.recruitment.repository.RecruitmentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public void save(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
    }

    public Optional<Recruitment> findRecruitment(Long id) {
        Optional<Recruitment> recruitment = recruitmentRepository.findById(id);
        return recruitmentRepository.findById(id);
    }

    public List<Recruitment> findRecruitmentList() {
        return recruitmentRepository.findAll();
    }

    public void delRecruitment(Long id) {
        recruitmentRepository.deleteById(id);
    }
}

