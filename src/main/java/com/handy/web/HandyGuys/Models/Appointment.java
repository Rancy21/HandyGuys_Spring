package com.handy.web.HandyGuys.Models;

import java.util.Date;
import java.util.UUID;

public class Appointment {
    private UUID id = UUID.randomUUID();

    private User Helper;
    private User client;
    private String client_address;
    private Date dateOfAppointment;
    private String purpose;
    private AppoinmenntStatus status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getHelper() {
        return Helper;
    }

    public void setHelper(User helper) {
        Helper = helper;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public AppoinmenntStatus getStatus() {
        return status;
    }

    public void setStatus(AppoinmenntStatus status) {
        this.status = status;
    }

}
