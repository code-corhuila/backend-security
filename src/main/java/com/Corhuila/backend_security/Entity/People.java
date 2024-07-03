package com.Corhuila.backend_security.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="people", schema = "parameter")
public class People extends BaseModel{
	
	@Column(name = "document_number", nullable = false, length = 20)
    private String documentNumber;
	
	@Column(name = "name", nullable = false, length = 100)
    private String name;
	
	@Column(name = "surname", nullable = false, length = 100)
    private String surname;

	@Column(name = "address", nullable = false, length = 100)
    private String address;
	
	@Column(name = "phone", nullable = false, length = 20)
    private String phone;
	
	@Column(name = "mail", nullable = false, length = 100)
    private String mail;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender", nullable = false)
    private Boolean gender;

    @Column(name = "attendant_name", nullable = false, length = 100)
    private String attendantName;

    @Column(name = "attending_phone", nullable = false, length = 20)
    private String attendingPhone;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentTypeId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City cityId;

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getAttendantName() {
		return attendantName;
	}

	public void setAttendantName(String attendantName) {
		this.attendantName = attendantName;
	}

	public String getAttendingPhone() {
		return attendingPhone;
	}

	public void setAttendingPhone(String attendingPhone) {
		this.attendingPhone = attendingPhone;
	}

	public DocumentType getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(DocumentType documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public City getCityId() {
		return cityId;
	}

	public void setCityId(City cityId) {
		this.cityId = cityId;
	} 
}
