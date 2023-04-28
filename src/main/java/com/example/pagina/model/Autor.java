package com.example.pagina.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("AUTOR")
public class Autor {

    @Id
    private String id;
    @Column("NAME")
    private String name;
    @Column("PHONE")
    private String phone;
    @Column("EMAIL")
    private String email;

    public Autor(String name, String phone, String email){
        this.phone= phone;
        this.name = name;
        this.email=email;
    }

    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
