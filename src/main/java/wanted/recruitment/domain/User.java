package wanted.recruitment.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    long userId;
    String userName;

}
