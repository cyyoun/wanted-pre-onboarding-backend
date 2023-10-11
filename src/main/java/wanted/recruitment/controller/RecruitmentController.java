package wanted.recruitment.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import wanted.recruitment.domain.Recruitment;
import wanted.recruitment.dto.RecruitmentDto;
import wanted.recruitment.dto.RecruitmentListDto;
import wanted.recruitment.service.RecruitmentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;
    private final ModelMapper modelMapper;

    @PostMapping("/add")
    public RecruitmentDto add(@RequestBody Recruitment recruitment) {
        recruitmentService.save(recruitment);
        Recruitment getRecruitment = recruitmentService.findRecruitment(recruitment.getId()).orElse(null);
        RecruitmentDto dto = new RecruitmentDto();
        if (getRecruitment != null) {
           return modelMapper.map(getRecruitment, RecruitmentDto.class);
        }
        return dto;
    }

    @GetMapping("/list")
    public List<RecruitmentListDto> list() {
        List<Recruitment> recruitmentList = recruitmentService.findRecruitmentList();

        return recruitmentList.stream()
                .map(recruitment -> modelMapper.map(recruitment, RecruitmentListDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{recruitmentId}")
    public RecruitmentDto detail(@PathVariable Long recruitmentId) {
        Recruitment getRecruitment = recruitmentService.findRecruitment(recruitmentId).orElse(null);
        RecruitmentDto dto = new RecruitmentDto();
        if (getRecruitment != null) {
            return modelMapper.map(getRecruitment, RecruitmentDto.class);
        }
        return dto;
    }


    @DeleteMapping("/{recruitmentId}")
    public String delete(@PathVariable Long recruitmentId) {
        recruitmentService.delRecruitment(recruitmentId);
        return "공고가 삭제되었습니다. 🙂";
    }

    @PatchMapping("/{recruitmentId}")
    public RecruitmentDto edit(@PathVariable long recruitmentId, @RequestBody Recruitment recruitment) {
        Recruitment getRecruitment = recruitmentService.editRecruitment(recruitment, recruitmentId).orElse(null);
        return modelMapper.map(getRecruitment, RecruitmentDto.class);
    }

}
