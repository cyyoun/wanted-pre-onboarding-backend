package wanted.recruitment.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false) // 외래키 null 허용 안함
    private Company company;

}
