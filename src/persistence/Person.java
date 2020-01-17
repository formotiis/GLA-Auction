/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

/**
 *
 * @author cirstea
 */
import java.io.Serializable;

public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String card;
    private Long id;
    private Integer canceled;
    private Integer zip;

    public Person(String firstName, String lastName, String userName, String email,
                  String phone, String address, String city, String card, Long id, Integer canceled, Integer zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.card = card;
        this.id = id;
        this.canceled = canceled;
        this.zip = zip;
    }

    public Person(String firstName, String lastName, String userName, String email,
                  String phone, String address, String city, String card, Integer canceled, Integer zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.card = card;
        this.canceled = canceled;
        this.zip = zip;
    }

    public Person(String firstName, String lastName, String userName, String email, String phone, Integer canceled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.canceled = canceled;
    }

    public String getFirstName() {
        return firstName;
    }

    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName(){
        return lastName + ' ' +firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isFirstNameSet(){
        return (firstName != null && !"".equals(firstName.trim()));
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isLastNameSet(){
        return (lastName != null && !"".equals(lastName.trim()));
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isUserNameSet(){
        return (userName != null && !"".equals(userName.trim()));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailSet(){
        return (email != null && !"".equals(email.trim()));
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isPhoneSet(){
        return (phone != null && !"".equals(phone.trim()));
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAddressSet(){
        return (address != null && !"".equals(address.trim()));
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isCitySet(){
        return (city != null && !"".equals(city.trim()));
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public boolean isCardSet(){
        return (card != null && !"".equals(card.trim()));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isIdSet(){
        return (id != null);
    }

    public Integer getCanceled() {
        return canceled;
    }

    public void setCanceled(Integer canceled) {
        this.canceled = canceled;
    }

    public boolean isCanceledSet(){
        return (canceled != null);
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }
    
    public boolean isZipSet(){
        return (zip != null);
    }
}
