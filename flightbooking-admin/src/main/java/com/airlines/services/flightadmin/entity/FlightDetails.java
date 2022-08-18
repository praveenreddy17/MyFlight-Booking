package com.airlines.services.flightadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FLIGHT_DETAILS")
public class FlightDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int FlightID;
	@Column
	private String FromPlace;
	@Column
    private String ToPlace;
	@Column
    private double Cost;
	@Column
    private String CreatedDate;
	@Column
    private String CreatedBy;
	@Column
    private String FlightName;
	@Column
    private String IsActive;
	
	
	
	public String getFlightName() {
		return FlightName;
	}
	public void setFlightName(String flightName) {
		FlightName = flightName;
	}
	public int getFlightID() {
		return FlightID;
	}
	public void setFlightID(int flightID) {
		FlightID = flightID;
	}
	public String getFromPlace() {
		return FromPlace;
	}
	public void setFromPlace(String fromPlace) {
		FromPlace = fromPlace;
	}
	public String getToPlace() {
		return ToPlace;
	}
	public void setToPlace(String toPlace) {
		ToPlace = toPlace;
	}
	public double getCost() {
		return Cost;
	}
	public void setCost(double cost) {
		Cost = cost;
	}
	public String getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public String getIsActive() {
		return IsActive;
	}
	public void setIsActive(String isActive) {
		IsActive = isActive;
	}
    
	
    
}