package model;

public class Verbruik {
	
	private int gas;
	private int stroom;
	private int water;
	
	public Verbruik(int gas, int stroom, int water) {
		this.gas = gas;
		this.stroom = stroom;
		this.water = water;
	}
	
	public Verbruik() {
		this(0,0,0);
	}
	
	public String toString() {
		return String.format("(gas: %d; stroom: %d; water: %d)", gas, stroom, water);
	}
}
