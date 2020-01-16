package com.magister.garbagecollector.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="applications")
public class Application {

    @Id
	@GeneratedValue
    private Long id;
	
    @ManyToOne
    private User owner;
    
    private Double volume;
    
    private Date createdAt;
    
    private Date updatedAt;
    
    private Date acceptedAt;
    
    private Date completedAt;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    private String address;
    
    private String comment;

    private String tip;
    
    private Application() {}

	public Application(User owner, String address, Double volume, String comment, String tip) {
		this.owner = owner;
		this.address = address;
		this.volume = volume;
		this.createdAt = new Date();
		this.status = Status.NEW;
		this.comment = comment;
		this.tip = tip;
	}
}
