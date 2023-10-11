package wanted.recruitment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecruitmentDto {
    private long companyId;
    private String position;
    private String compensation;
    private String content;
    private String skill;
}
