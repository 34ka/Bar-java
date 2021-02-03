package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactDate {
    @Id
    @Column(name = "id")
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column (name = "firstname")
    private String firstname;

    @Expose
    @Column (name = "lastname")
    private String lastname;

    @Expose
    @Column (name = "home")
    @Type(type = "text")
    private String homePhone;

    @Expose
    @Column (name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Expose
    @Column (name = "work")
    @Type(type = "text")
    private String workPhone;

    @Expose
    @Column (name = "email")
    @Type(type = "text")
    private String email;

    @Expose
    @Column (name = "email2")
    @Type(type = "text")
    private String email2;


    @Column (name = "email3")
    @Type(type = "text")
    @Expose
    private String email3;

    @Expose
    @Column (name = "address")
    @Type(type = "text")
    private String address;

    @Transient
    private String group;

    @Transient
    private String allPhones;

    @Transient
    private String AllEmails;

    @Column (name = "photo")
    @Type(type = "text")
    private String photo;



    public int getId() {
        return id;
    }

    public ContactDate withId(int id) {
        this.id = id;
        return this;
    }

    public ContactDate withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactDate withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
    public ContactDate withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactDate withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactDate withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactDate withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactDate withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactDate withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactDate withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactDate withAllEmails(String AllEmails) {
        this.AllEmails = AllEmails;
        return this;
    }


    public ContactDate withAddress(String address) {
        this.address = address;
        return this;
    }


    public ContactDate withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactDate withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return AllEmails;
    }

    public String getAddress() {
        return address;
    }

    public String getGroup() {
        return group;
    }

    public File getPhoto() {
        return new File (photo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDate that = (ContactDate) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(homePhone, that.homePhone) &&
                Objects.equals(mobilePhone, that.mobilePhone) &&
                Objects.equals(workPhone, that.workPhone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(email3, that.email3) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, homePhone, mobilePhone, workPhone, email, email2, email3, address);
    }

    @Override
    public String toString() {
        return "ContactDate{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", address='" + address + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

}
