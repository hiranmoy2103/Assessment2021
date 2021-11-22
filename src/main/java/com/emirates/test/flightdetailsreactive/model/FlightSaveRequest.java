package com.emirates.test.flightdetailsreactive.model;

public class FlightSaveRequest {

    private String routeRegistrationNo;
    private String flightNumber;
    private String flightDate;
    private double basePrice;
    private String depCode;
    private String arrCode;

    public FlightSaveRequest(String routeRegistrationNo, String flightNumber, String flightDate, double basePrice, String depCode, String arrCode) {
        this.routeRegistrationNo = routeRegistrationNo;
        this.flightNumber = flightNumber;
        this.flightDate = flightDate;
        this.basePrice = basePrice;
        this.depCode = depCode;
        this.arrCode = arrCode;
    }


    public String getRouteRegistrationNo() {
        return routeRegistrationNo;
    }

    public void setRouteRegistrationNo(String routeRegistrationNo) {
        this.routeRegistrationNo = routeRegistrationNo;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getArrCode() {
        return arrCode;
    }

    public void setArrCode(String arrCode) {
        this.arrCode = arrCode;
    }

}
