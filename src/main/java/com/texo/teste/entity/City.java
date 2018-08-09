package com.texo.teste.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "city")
public class City {

    private Long hash;

    @Id
    private int ibgeId;
    private String uf;
    private String name;
    private boolean capital;
    private double latitude;
    private double longitude;
    private String noAccents;
    private String alternativeNames;
    private String microregion;
    private String mesoregion;

    public City() {
    }

    public City(int ibgeId) {
        this.ibgeId = ibgeId;
    }

    public City(int ibgeId, String uf, String name, boolean capital) {
        this.ibgeId = ibgeId;
        this.uf = uf;
        this.name = name;
        this.capital = capital;
    }

    public City(int ibgeId, String uf, String name, boolean capital, double latitude, double longitude, String noAccents, String alternativeNames, String microregion, String mesoregion) {
        this.ibgeId = ibgeId;
        this.uf = uf;
        this.name = name;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
        this.noAccents = noAccents;
        this.alternativeNames = alternativeNames;
        this.microregion = microregion;
        this.mesoregion = mesoregion;
    }

    public int getIbgeId() {
        return ibgeId;
    }

    public void setIbgeId(int ibgeId) {
        this.ibgeId = ibgeId;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNoAccents() {
        return noAccents;
    }

    public void setNoAccents(String noAccents) {
        this.noAccents = noAccents;
    }

    public String getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(String alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    public String getMicroregion() {
        return microregion;
    }

    public void setMicroregion(String microregion) {
        this.microregion = microregion;
    }

    public String getMesoregion() {
        return mesoregion;
    }

    public void setMesoregion(String mesoregion) {
        this.mesoregion = mesoregion;
    }

    @Override
    public int hashCode() {
        return this.hash.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof City && ((City) obj).ibgeId == this.ibgeId;
    }
}
