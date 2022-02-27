package com.app.core.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "batch")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Batch extends BaseEntity {
	
	@Column(name = "batchid")
	private int batchId;
	
	@Column(name = "batch_time")
	private String batchTime;
	
	@Column(name = "batch_type")
	private BatchType batchType;
	

}
