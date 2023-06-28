package it.polito.tdp.borders.model;

public class CountryAndNumber {
	private Country country;
	private int number;
	public CountryAndNumber(Country country, int number) {
		super();
		this.country = country;
		this.number = number;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return  country + " numero di stati confinanti: " + number;
	}
	

}
