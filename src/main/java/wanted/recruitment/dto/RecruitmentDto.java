package wanted.recruitment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class RecruitmentDto {
    private long companyId;
    private String position;
    private String compensation;
    private String content;
    private String skill;
    private List<Long> recruitmentList = new ArrayList<>();
}
