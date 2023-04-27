package com.example.pagina.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("Autor")
public class Autor {

    @Id
    private String id;
    @Column("NAME")
    private String name;
    @Column("PHONE")
    private String phone;
    @Column("MAIL")
    private String mail;

    public Autor(String name, String phone, String mail){
        this.phone= phone;
        this.name = name;
        this.mail=mail;
    }

    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public String getMail(){
        return mail;
    }
}
