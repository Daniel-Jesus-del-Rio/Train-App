package edu.uoc.locuocomotive.model;

import edu.uoc.locuocomotive.model.Exception.PassengerException;

import java.time.LocalDate;

public class Passenger {
    private String passport;
    private String name;
    private String surname;
    private LocalDate birth;
    private String email;
    public Passenger(String passport, String name, String surname, LocalDate birth, String email) throws Exception {
            setPassport(passport);
            setName(name);
            setSurname(surname);
            setBirth(birth);
            setEmail(email);

    }

    public String getPassport() {
        return passport;
    }

    private void setPassport(String passport) throws Exception {
        if (passport.isEmpty()||passport==null){
            throw new Exception(PassengerException.INVALID_PASSPORT);
        }
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception{
        if (name.isEmpty()||name==null){
            throw new Exception(PassengerException.INVALID_NAME);
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws Exception {
        if (surname.isEmpty()||surname==null){
            throw new Exception(PassengerException.INVALID_SURNAME);
        }
        this.surname = surname;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth)throws Exception {
        if (birth==null){
            throw new Exception(PassengerException.INVALID_BIRTH);
        }
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email)throws Exception {
        String emailRegex = "^(|[A-Za-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,3})$";

        if (email == null || !email.matches(emailRegex)) {
            throw new Exception(PassengerException.INVALID_EMAIL);

        }
        this.email=email;
    }
}
