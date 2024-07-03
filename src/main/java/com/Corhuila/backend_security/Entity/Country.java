package com.Corhuila.backend_security.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="country", schema = "parameter")

public class Country extends BaseGeneric{

}
