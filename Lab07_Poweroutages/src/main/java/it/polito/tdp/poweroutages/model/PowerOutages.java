package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.Duration;


public class PowerOutages {

	private Integer id;
	private Integer costumersAffected;
	private LocalDateTime dateI;
	private LocalDateTime dateF;
	private Integer demandLoss;
	
	private Nerc nerc;

	public PowerOutages(Integer id, Integer costumersAffected, LocalDateTime dateI, LocalDateTime dateF,
			Integer demandLoss) {
		super();
		this.id = id;
		this.costumersAffected = costumersAffected;
		this.dateI = dateI;
		this.dateF = dateF;
		this.demandLoss = demandLoss;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCostumersAffected() {
		return costumersAffected;
	}

	public void setCostumersAffected(Integer costumersAffected) {
		this.costumersAffected = costumersAffected;
	}

	public LocalDateTime getDateI() {
		return dateI;
	}

	public void setDateI(LocalDateTime dateI) {
		this.dateI = dateI;
	}

	public LocalDateTime getDateF() {
		return dateF;
	}

	public void setDateF(LocalDateTime dateF) {
		this.dateF = dateF;
	}

	public Integer getDemandLoss() {
		return demandLoss;
	}

	public void setDemandLoss(Integer demandLoss) {
		this.demandLoss = demandLoss;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return dateI.toString()+" "+dateF.toString()+" "+costumersAffected;
	}
	
	
	public Integer getDurata() {
		Duration d = Duration.between(dateI, dateF);
		return (int) d.toHours();
	}
	
	
}