package it.polito.tdp.poweroutages.model;

import java.util.*;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	private Integer bestFolla;
	private Integer bestOre;
	private Integer maxAnno;
	private Integer maxOre;
	private List<PowerOutages> bestSoluzione = null;
	private List<PowerOutages> blackOut;

	public Model() {
		podao = new PowerOutageDAO();
	}

	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<PowerOutages> calcoloBlackOut(Nerc nerc, Integer annoM, Integer oreM) {

		this.maxAnno = annoM;
		this.maxOre = oreM;

		this.blackOut = new ArrayList<>(podao.getBlackOutinNerc(nerc));

		List<PowerOutages> parziale = new ArrayList<>();
		cerca(parziale, 1);

		return bestSoluzione;
	}

	private void cerca(List<PowerOutages> parziale, int l) {

		// se le ore o gli anni son sbagliati
		if (!this.checkOre(parziale) || !this.checkAnni(parziale)) {
			return;
		}

		// Controllo se la soluzione che ho è meglio dell'altra oppure no

		Integer newFolla = this.getFolla(parziale);
		if (newFolla > bestFolla) {
			bestSoluzione = new ArrayList<>(parziale);
			bestFolla = newFolla;
		}

		// se so uguali so arrivata
		if (l == blackOut.size()) {
			return;
		}

		parziale.add(blackOut.get(l));
		this.cerca(parziale, l + 1);
		parziale.remove(parziale.size() - 1);
		this.cerca(parziale, l + 1);
	}

	public Integer getFolla(List<PowerOutages> parziale) {
		// TODO Auto-generated method stub
		Integer somma = 0;
		for (PowerOutages p : parziale) {
			somma += p.getCostumersAffected();
		}
		return somma;
	}

	public Integer getDurata(List<PowerOutages> parziale) {
		// TODO Auto-generated method stub
		Integer somma = 0;
		for (PowerOutages p : parziale) {
			somma += p.getDurata();
		}
		return somma;
	}

	private boolean checkAnni(List<PowerOutages> parziale) {
		// TODO Auto-generated method stub
		if (blackOut.size() < 2) {
			return true;
		}
		PowerOutages first = blackOut.get(0);
		PowerOutages last = blackOut.get(blackOut.size() - 1);

		if (last.getDateI().getYear() - first.getDateI().getYear() <= maxAnno) {
			return true;
		} else
			return false;
	}

	private boolean checkOre(List<PowerOutages> parziale) {
		// TODO Auto-generated method stub
		Integer somma = 0;
		for (PowerOutages p : parziale) {
			somma += p.getDurata();
		}

		if (somma <= this.maxOre)
			return true;
		else
			return false;
	}
	
	
	

}
