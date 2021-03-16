package com.wallapop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Conversation {
	@Id
	@GeneratedValue
	private long id;
}
