package org.example.springapp.entitys;


import javax.persistence.*;


@Entity
@Table(name="tags_file")
public class Tags {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;


    // Конструкторы
    public Tags() {
    }
    public Tags(String name) {
        this.name = name;
    }



    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
