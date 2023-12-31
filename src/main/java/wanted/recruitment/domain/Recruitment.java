package wanted.recruitment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "recruitment_tbl")
public class Recruitment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitment_id")
    private long id;

    private String content;
    private String position;
    private String compensation;
    private String skill;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.REMOVE) // 연쇄 삭제 적용
    private List<Application> applications = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_id", nullable = false) // 외래키 null 허용 안함
    private Company company;

    public void setContent(String content) {
        this.content = content;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
