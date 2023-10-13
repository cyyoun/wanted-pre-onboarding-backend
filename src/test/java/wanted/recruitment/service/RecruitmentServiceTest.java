package wanted.recruitment.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wanted.recruitment.domain.Company;
import wanted.recruitment.domain.Recruitment;

@SpringBootTest
public class RecruitmentServiceTest {


    @Autowired
    RecruitmentService recruitmentService;

    @Test
    @Transactional
    public void testSaveRecruitment() {
        Company company = new Company();
        company.setCompanyName("Test company");

        //given
        Recruitment recruitment = new Recruitment();
        recruitment.setContent("Test content");
        recruitment.setCompensation("Test compensation");
        recruitment.setSkill("Test skill");
        recruitment.setPosition("Test position");
        recruitment.setCompany(company);
        recruitmentService.save(recruitment);

        //when
        Recruitment saved = recruitmentService.findRecruitment(recruitment.getId());

        //then
        Assertions.assertThat("Test content").isEqualTo(saved.getContent());
        Assertions.assertThat("Test compensation").isEqualTo(saved.getCompensation());
        Assertions.assertThat("Test skill").isEqualTo(saved.getSkill());
        Assertions.assertThat("Test position").isEqualTo(saved.getPosition());

    }
}
