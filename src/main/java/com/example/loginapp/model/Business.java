package com.example.loginapp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "business")
public class Business {
//    @Id
//    private String businessId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 🔑 Real primary key (used internally by database and JPA)

    @Column(unique = true)
    private String businessId;

    private String businessName;
    private String businessType;
    private String address;
    private String city;
    private String district;
    private String pincode;
    private String state;
    private String gstNumber;
    private String whySell;

    private String panOrAadharPath;
    private String licensePath;
    private String gstCertificatePath;
    private String companyLogoPath;


    // Profile4 fields
    private String natureOfCompany;

    private LocalDate registrationDate;

    private String numberOfEmployees;

    private Boolean ecommerceDepartment;

    private Boolean sellsOnEcommerce;

    private Boolean hasDealerNetwork;

    private String turnoverYear1;
    private String turnoverYear2;
    private String turnoverYear3;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @ElementCollection
    @CollectionTable(name = "business_cities", joinColumns = @JoinColumn(name = "business_id"))
    @Column(name = "cities")
    private List<String> cities;

    // Getters and setters


    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


//    -------------------------------------


    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getGstNumber() { return gstNumber; }
    public void setGstNumber(String gstNumber) { this.gstNumber = gstNumber; }

    public String getWhySell() { return whySell; }
    public void setWhySell(String whySell) { this.whySell = whySell; }

    public String getPanOrAadharPath() { return panOrAadharPath; }
    public void setPanOrAadharPath(String panOrAadharPath) { this.panOrAadharPath = panOrAadharPath; }

    public String getLicensePath() { return licensePath; }
    public void setLicensePath(String licensePath) { this.licensePath = licensePath; }

    public String getGstCertificatePath() { return gstCertificatePath; }
    public void setGstCertificatePath(String gstCertificatePath) { this.gstCertificatePath = gstCertificatePath; }

    public String getCompanyLogoPath() { return companyLogoPath; }
    public void setCompanyLogoPath(String companyLogoPath) { this.companyLogoPath = companyLogoPath; }

//    --------------------
public String getNatureOfCompany() { return natureOfCompany; }
    public void setNatureOfCompany(String natureOfCompany) { this.natureOfCompany = natureOfCompany; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public String getNumberOfEmployees() { return numberOfEmployees; }
    public void setNumberOfEmployees(String numberOfEmployees) { this.numberOfEmployees = numberOfEmployees; }

    public Boolean getEcommerceDepartment() { return ecommerceDepartment; }
    public void setEcommerceDepartment(Boolean ecommerceDepartment) { this.ecommerceDepartment = ecommerceDepartment; }

    public Boolean getSellsOnEcommerce() { return sellsOnEcommerce; }
    public void setSellsOnEcommerce(Boolean sellsOnEcommerce) { this.sellsOnEcommerce = sellsOnEcommerce; }

    public Boolean getHasDealerNetwork() { return hasDealerNetwork; }
    public void setHasDealerNetwork(Boolean hasDealerNetwork) { this.hasDealerNetwork = hasDealerNetwork; }

    public String getTurnoverYear1() { return turnoverYear1; }
    public void setTurnoverYear1(String turnoverYear1) { this.turnoverYear1 = turnoverYear1; }

    public String getTurnoverYear2() { return turnoverYear2; }
    public void setTurnoverYear2(String turnoverYear2) { this.turnoverYear2 = turnoverYear2; }

    public String getTurnoverYear3() { return turnoverYear3; }
    public void setTurnoverYear3(String turnoverYear3) { this.turnoverYear3 = turnoverYear3; }




}

