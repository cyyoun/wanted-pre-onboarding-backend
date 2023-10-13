package wanted.recruitment.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wanted.recruitment.domain.Company;
import wanted.recruitment.domain.Recruitment;

import java.util.List;

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

    @Test
    @Transactional
    public void testEditRecruitment() {
        //given
        Company company = new Company();
        Recruitment recruitment = new Recruitment();

        company.setCompanyName("Test company");
        recruitment.setCompany(company);

        recruitmentService.save(recruitment);
        Recruitment saved = recruitmentService.findRecruitment(recruitment.getId());

        //when
        saved.setPosition("시스템 운영, 인프라 관리 담당자");
        Recruitment edited = recruitmentService.editRecruitment(saved, saved.getId());

        //then
        Assertions.assertThat("시스템 운영, 인프라 관리 담당자").isEqualTo(edited.getPosition());

    }

    @Test
    @Transactional
    public void testSearchRecruitment() {

        //given
        Company company = new Company();
        Recruitment recruitment1 = new Recruitment();
        Recruitment recruitment2 = new Recruitment();
        Recruitment recruitment3 = new Recruitment();

        company.setCompanyName("Test company");
        recruitment1.setCompany(company);
        recruitment1.setContent("귀여운 병아리같은 신입 개발자 구합니다 !!");
        recruitmentService.save(recruitment1);

        recruitment2.setCompany(company);
        recruitment2.setContent("개발자 공부환경을 위해 스터디카페를 제공합니다! 와우");
        recruitmentService.save(recruitment2);

        recruitment3.setCompany(company);
        recruitment3.setContent("스터디카페 관련 앱 개발 함께해요!");
        recruitmentService.save(recruitment3);


        //when
        List<Recruitment> searched = recruitmentService.searchRecruitment("스터디카페");

        //then
        Assertions.assertThat(2).isEqualTo(searched.size());

    }
}
