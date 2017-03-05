package com.example.benaya.application2.model.entities;

/**
 * Created by User on 29/01/2017.
 */

public class Business {

    private long IDbusines;
    private String nameBusines;
    private String Ad_street;
    private String Ad_country;
    private String Ad_city;
    private int phone;
    private String Email;
    private String webSite;

    public Business(long IDbusines, String nameBusines,
                    String ad_street, String ad_country,
                    String ad_city, int phone, String email, String webSite) {
        this.IDbusines = IDbusines;
        this.nameBusines = nameBusines;
        Ad_street = ad_street;
        Ad_country = ad_country;
        Ad_city = ad_city;
        this.phone = phone;
        Email = email;
        this.webSite = webSite;
    }

    public Business() {
    }

    public long getIDbusines() {
        return IDbusines;
    }

    public void setIDbusines(long IDbusines) {
        this.IDbusines = IDbusines;
    }

    public String getNameBusines() {
        return nameBusines;
    }

    public void setNameBusines(String nameBusines) {
        this.nameBusines = nameBusines;
    }

    public String getAd_street() {
        return Ad_street;
    }

    public void setAd_street(String ad_street) {
        Ad_street = ad_street;
    }

    public String getAd_country() {
        return Ad_country;
    }

    public void setAd_country(String ad_country) {
        Ad_country = ad_country;
    }

    public String getAd_city() {
        return Ad_city;
    }

    public void setAd_city(String ad_city) {
        Ad_city = ad_city;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getwebSite() {
        return webSite;
    }

    public void setwebSite(String link) {
        webSite = link;
    }

}
