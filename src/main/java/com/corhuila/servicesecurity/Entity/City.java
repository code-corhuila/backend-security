package com.corhuila.servicesecurity.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="city", schema = "parameter")
public class City extends BaseGeneric{
	
	 @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "estate_id", nullable = false)
    public Estate estateId;
	 
	public Estate getEstateId() {
		return estateId;
	}

	public void setEstateId(Estate estateId) {
		this.estateId = estateId;
	}     
}
