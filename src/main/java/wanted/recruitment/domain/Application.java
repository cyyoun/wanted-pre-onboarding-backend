package wanted.recruitment.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(
        name = "application_tbl",
        uniqueConstraints = { // 고유 제약 조건 설정 (고유한 조합 설정)
                @UniqueConstraint(columnNames = {"user_id", "recruitment_id"})
        }
)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private long applicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

}
