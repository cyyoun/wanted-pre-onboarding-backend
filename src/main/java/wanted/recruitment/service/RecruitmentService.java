package wanted.recruitment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.recruitment.domain.Recruitment;
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

    public Optional<Recruitment> editRecruitment(Recruitment recruitment, Long id) {
        Recruitment getRecruitment = findRecruitment(id).orElse(null);
        if (getRecruitment != null) {
            getRecruitment.setContent(recruitment.getContent());
            getRecruitment.setPosition(recruitment.getPosition());
            getRecruitment.setCompensation(recruitment.getCompensation());
            getRecruitment.setSkill(recruitment.getSkill());
        }
        return Optional.ofNullable(getRecruitment);
    }

    public List<Long> findRecruitmentIdList(Recruitment recruitment) {
        List<Long> recruitmentIdList = recruitmentRepository.recruitmentIdList(recruitment.getCompany().getId());
        recruitmentIdList.remove(recruitment.getId());
        return recruitmentIdList;
    }

    public List<Recruitment> searchRecruitment(String keyword) {
        System.out.println(recruitmentRepository.recruitmentByKeyword(keyword).toString());
        return recruitmentRepository.recruitmentByKeyword(keyword);
    }
}

