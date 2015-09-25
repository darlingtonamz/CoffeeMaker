
public class Vessel {

	private int currentVolumeMilk, currentVolumeCoffee, currentVolumeChocolate, currentVolumeWater ;
	private int maxVolume;

	public int getMaxVolume() {
		return maxVolume;
	}

	public void setMaxVolume(int maxVolume) {
		this.maxVolume = maxVolume;
	}

	public Vessel(int volume){
		// constructor
		currentVolumeMilk = volume;
		currentVolumeCoffee = volume;
		currentVolumeChocolate = volume;
		currentVolumeWater = volume;
	}
	

	public int getMilkCurrentVolume() {
		return currentVolumeMilk;
	}

	public int getCoffeeCurrentVolume() {
		return currentVolumeCoffee;
	}

	public int getChocolateCurrentVolume() {
		return currentVolumeChocolate;
	}

	public int getWaterCurrentVolume() {
		return currentVolumeWater;
	}

	public void setMilkCurrentVolume(int milk) {
		this.currentVolumeMilk = milk;
	}

	public void setCoffeeCurrentVolume(int coffee) {
		this.currentVolumeCoffee = coffee;
	}

	public void setChocolateCurrentVolume(int chocolate) {
		this.currentVolumeChocolate = chocolate;
	}

	public void setWaterCurrentVolume(int water) {
		this.currentVolumeWater = water;
	}
	
}
