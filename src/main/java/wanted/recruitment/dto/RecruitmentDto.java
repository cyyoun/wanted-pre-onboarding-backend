package wanted.recruitment.dto;

import lombok.Data;

@Data
public class RecruitmentDto {
    private long company_id;
    private String position;
    private String compensation;
    private String content;
    private String skill;
}
