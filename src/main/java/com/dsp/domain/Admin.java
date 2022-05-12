package com.dsp.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "admin")
public class Admin {
    public Admin() {
    }
    @Id
    private Integer aId;
    private String aName;
    private String aPassword;
    private String aPhone;
    private String aImg;

    public Admin(Integer aId, String aName, String aPassword,String aPhone, String aImg) {
        this.aId = aId;
        this.aName = aName;
        this.aPassword = aPassword;
        this.aPhone = aPhone;
        this.aImg = aImg;
    }
}
