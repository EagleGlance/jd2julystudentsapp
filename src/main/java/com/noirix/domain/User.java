package com.noirix.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

public class User {
    private Long id;
    private String userName;
    private String surname;
    private Timestamp birth;
    private boolean isDeleted;
    private Timestamp creationDate;
    private Timestamp modificationDate;
    private Double weight;

    public User() {
    }

    public User(Long id, String userName, String surname, Timestamp birth, boolean isDeleted, Timestamp creationDate, Timestamp modificationDate, Double weight) {
        this.id = id;
        this.userName = userName;
        this.surname = surname;
        this.birth = birth;
        this.isDeleted = isDeleted;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Timestamp getBirth() {
        return birth;
    }

    public void setBirth(Timestamp birth) {
        this.birth = birth;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Timestamp modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (isDeleted != user.isDeleted) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (birth != null ? !birth.equals(user.birth) : user.birth != null) return false;
        if (creationDate != null ? !creationDate.equals(user.creationDate) : user.creationDate != null) return false;
        if (modificationDate != null ? !modificationDate.equals(user.modificationDate) : user.modificationDate != null)
            return false;
        return weight != null ? weight.equals(user.weight) : user.weight == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
