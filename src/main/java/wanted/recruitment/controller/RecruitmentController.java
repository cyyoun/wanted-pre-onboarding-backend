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
        Recruitment getRecruitment = recruitmentService.findRecruitment(recruitment.getId());
        return modelMapper.map(getRecruitment, RecruitmentDto.class);
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
        Recruitment getRecruitment = recruitmentService.findRecruitment(recruitmentId);
        RecruitmentDto dto = modelMapper.map(getRecruitment, RecruitmentDto.class);

        //다른 채용 공고
        dto.setRecruitmentList(recruitmentService.findRecruitmentIdList(getRecruitment));
        return dto;
    }

    @DeleteMapping("/{recruitmentId}")
    public String delete(@PathVariable Long recruitmentId) {
        recruitmentService.delRecruitment(recruitmentId);
        return "공고가 삭제되었습니다. 🙂";
    }

    @PatchMapping("/{recruitmentId}")
    public RecruitmentDto edit(@PathVariable long recruitmentId, @RequestBody Recruitment recruitment) {
        Recruitment getRecruitment = recruitmentService.editRecruitment(recruitment, recruitmentId);
        return modelMapper.map(getRecruitment, RecruitmentDto.class);
    }

    @GetMapping
    public List<RecruitmentDto> search(@RequestParam("search") String search) {
        List<Recruitment> recruitments = recruitmentService.searchRecruitment(search);
        return recruitments.stream()
                .map(recruitment -> modelMapper.map(recruitment, RecruitmentDto.class))
                .collect(Collectors.toList());
    }

}
