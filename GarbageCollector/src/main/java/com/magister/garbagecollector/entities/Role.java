package com.magister.garbagecollector.entities;

import lombok.Data;
import com.magister.garbagecollector.entities.jpa.RoleKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Entity
@Table(name="authorities")
@IdClass(RoleKey.class)
public class Role {

	@Id
	private String username;
	
	@Id
	private String authority;

	private Role() {}
	
	public Role(String username, String authority) {
		this.username = username;
		this.authority = authority;
	}
}
