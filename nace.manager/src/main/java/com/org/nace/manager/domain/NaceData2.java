package com.org.nace.manager.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


public class NaceData2  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
//	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
//	 @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="nace_id")
	private Long naceId;
	

	@Column(name="nace_orderz_Id", unique=true, nullable = false )
	private Integer naceOrderId;
	
	
}
