package wanted.recruitment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wanted.recruitment.domain.Company;
import wanted.recruitment.service.CompanyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public String add(@RequestBody Company company) {
        companyService.save(company);
        return "환영합니다 👾";
    }

}
