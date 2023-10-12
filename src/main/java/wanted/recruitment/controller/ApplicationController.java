package wanted.recruitment.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import wanted.recruitment.domain.Application;
import wanted.recruitment.dto.ApplicationDto;
import wanted.recruitment.service.ApplicationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("apply")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ApplicationDto applyRecruitment(@RequestBody Application application) {
        Application applied = applicationService.applyRecruitment(application);
        return modelMapper.map(applied, ApplicationDto.class);
    }

}
