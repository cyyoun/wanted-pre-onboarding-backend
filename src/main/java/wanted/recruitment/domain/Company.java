package wanted.recruitment.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "company_tbl")
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private long id;
    private String companyName;
}
