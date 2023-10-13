package wanted.recruitment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.recruitment.domain.Recruitment;
import wanted.recruitment.repository.RecruitmentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public void save(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
    }

    public Recruitment findRecruitment(Long id) {
        return recruitmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Recruitment not found"));
    }

    public List<Recruitment> findRecruitmentList() {
        return recruitmentRepository.findAll();
    }

    public void delRecruitment(Long id) {
        recruitmentRepository.deleteById(id);
    }

    public Recruitment editRecruitment(Recruitment recruitment, Long id) {
        Recruitment getRecruitment = findRecruitment(id);
        getRecruitment.setContent(recruitment.getContent());
        getRecruitment.setPosition(recruitment.getPosition());
        getRecruitment.setCompensation(recruitment.getCompensation());
        getRecruitment.setSkill(recruitment.getSkill());

        return getRecruitment;
    }

    public List<Long> findRecruitmentIdList(Recruitment recruitment) {
        List<Long> recruitmentIdList = recruitmentRepository.recruitmentIdList(recruitment.getCompany().getId());
        recruitmentIdList.remove(recruitment.getId());
        return recruitmentIdList;
    }

    public List<Recruitment> searchRecruitment(String keyword) {
        return recruitmentRepository.recruitmentByKeyword(keyword);
    }

}

