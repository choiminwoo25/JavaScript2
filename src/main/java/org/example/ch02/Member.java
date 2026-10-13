package org.example.ch02;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MEMBER",
                uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME", "AGE"}, name = "NAME_AGE_UNIQUE")})
public class Member {

    @Id
    @Column(name= "id")
    private String id;

    @Column(name = "name", nullable = false, length = 10)
    private String username;


    private Integer age;

    @Enumerated(EnumType.STRING)
    private Roletype roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Lob
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
