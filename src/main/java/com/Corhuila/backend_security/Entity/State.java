package com.Corhuila.backend_security.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="state", schema = "parameter")
public class State extends BaseGeneric{

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "state_type_id", nullable = false)
    private StateType stateTypeId;

	public StateType getStateTypeId() {
		return stateTypeId;
	}

	public void setStateTypeId(StateType stateTypeId) {
		this.stateTypeId = stateTypeId;
	}    
}
