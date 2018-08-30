package com.wolfTungsten.vcampus.entity;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "User")
public class User {

@DatabaseField(generatedId = true)
private UUID uuid;
@DatabaseField
private String username;
@DatabaseField
private String cardnum;

public User() {
	
}

public UUID getUuid() {
	return uuid;
}



public void setUuid(UUID uuid) {
	this.uuid = uuid;
}



public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public User(String name, String cardnum) {
	this.username = name;
	this.cardnum = cardnum;
}


public String getCardnum() {
	return cardnum;
}

public void setCardnum(String cardnum) {
	this.cardnum = cardnum;
}



}