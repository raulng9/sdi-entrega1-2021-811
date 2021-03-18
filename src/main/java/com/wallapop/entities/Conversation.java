package com.wallapop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Conversation {
	//TODO completar atributos y establecer relaciones con usuario y oferta
	@Id
	@GeneratedValue
	private long id;
}
