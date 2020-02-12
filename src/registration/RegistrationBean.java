package registration;

import persistence.Person;
import persistence.PersonManager;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

//@Stateless(name = "Registration")
@Named(value = "registrationBean")
@RequestScoped
public class RegistrationBean {

    @Inject
    NameHandler nameHandler;
    @Inject
    PersonManager personManager;

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String card;
    private Long id;
    private Integer canceled;
    private Integer zip;
    private Person p;


    public RegistrationBean() {

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCanceled() {
        return canceled;
    }

    public void setCanceled(Integer canceled) {
        this.canceled = canceled;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String registerUser(){
        p = new Person(firstname, lastname, username, email, phone, address, city,
                card, canceled, zip);
        personManager.addPerson(p, password);
        return "greeting";
    }

    public String greeting(){
        return nameHandler.greetingsMessage(p);
    }
    public String goBack(){
        return "index";
    }
}
