package wanted.recruitment.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "user_tbl")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    private String userName;

    @OneToMany(mappedBy = "user")
    private List<Application> applications = new ArrayList<>();

}
