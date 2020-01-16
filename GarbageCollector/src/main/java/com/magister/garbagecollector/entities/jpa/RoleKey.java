package com.magister.garbagecollector.entities.jpa;

import java.io.Serializable;

import lombok.Data;

@Data
public class RoleKey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String authority;
}
