package org.example.springapp.entitys;


import javax.persistence.*;



@Entity
public class FilesItem {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String text;
    private String tag;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    private String filename;
    private  String[] listAuthorizedUsers;                            // Список допущеных к файлу  пользователей




    public FilesItem(){
    }


    public FilesItem(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }



    public String getAuthorName() {
        return author != null ? author.getUsername() : "NONE AUTHOR";
    }


    public String[] getListAuthorizedUsers() {
        return listAuthorizedUsers;
    }
    public void setListAuthorizedUsers(String[] listAuthorizedUsers) {
        this.listAuthorizedUsers = listAuthorizedUsers;
    }

    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
