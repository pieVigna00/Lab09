package it.polito.tdp.borders.model;

import java.util.Objects;

public class Country {
	private int ccode;
	private String stateAbb;
	private String stateNme;
	public Country(int ccode, String stateAbb, String stateNme) {
		super();
		this.ccode = ccode;
		this.stateAbb = stateAbb;
		this.stateNme = stateNme;
	}
	public int getCcode() {
		return ccode;
	}
	public void setCcode(int ccode) {
		this.ccode = ccode;
	}
	public String getStateAbb() {
		return stateAbb;
	}
	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}
	public String getStateNme() {
		return stateNme;
	}
	public void setStateNme(String stateNme) {
		this.stateNme = stateNme;
	}
	@Override
	public String toString() {
		return "Country [ccode=" + ccode + ", stateAbb=" + stateAbb + ", stateNme=" + stateNme + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(ccode);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return ccode == other.ccode;
	}
	
}
