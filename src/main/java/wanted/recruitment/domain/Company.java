package wanted.recruitment.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "company_tbl")
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private long companyId;
    private String companyName;
}
