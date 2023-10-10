package wanted.recruitment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wanted.recruitment.domain.Recruitment;
import wanted.recruitment.dto.RecruitmentDto;
import wanted.recruitment.service.RecruitmentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping("/add")
    public RecruitmentDto addRecruitment(@RequestBody Recruitment recruitment) {
        recruitmentService.save(recruitment);
        Recruitment getRecruitment = recruitmentService.findRecruitment(recruitment.getId()).orElse(null);
        RecruitmentDto dto = new RecruitmentDto();
        if (getRecruitment != null) {
            dto.setCompany_id(getRecruitment.getId());
            dto.setPosition(getRecruitment.getPosition());
            dto.setContent(getRecruitment.getContent());
            dto.setCompensation(getRecruitment.getCompensation());
            dto.setSkill(getRecruitment.getSkill());
        }
        return dto;
    }

}
