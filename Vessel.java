
public class Vessel {
	private int capacity, currentVolume = 10;
	private String contentName;
	
	public Vessel(int capacity, String name){
		this.capacity = capacity;
		contentName = name;
	}
	public int getUsed(){
		return (capacity - currentVolume);
	}
	public int getCapacity() {
		return capacity;
	}
	public int getCurrentVolume() {
		return currentVolume;
	}
	public String getContentName() {
		return contentName;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
}
