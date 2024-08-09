package io.github.raphael_vi.skillkeeper.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "skills_name", nullable = false, length = 255)
    private String name;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Field field;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }


    public Field getField(){

        return field;
    }

    public void setField(Field field) {

        this.field = field;
    }

}
