
public class Coffee {

	private  int milkAmt, coffeeAmt, waterAmt, chocolateAmt;
	String name;
	
	public Coffee(int milk, int coffee, int water, int chocolate, String name){
		milkAmt = milk;
		coffeeAmt = coffee;
		waterAmt = water;
		chocolateAmt = chocolate;
		this.name = name;
	}

	public  int getMilkAmt() {
		return milkAmt;
	}

	public int getCoffeeAmt() {
		return coffeeAmt;
	}

	public  int getWaterAmt() {
		return waterAmt;
	}

	public  int getChocolateAmt() {
		return chocolateAmt;
	}
	
	public  String getName(){
		return name;
	}

	// public  void setMilkAmt(int milk){
	// 	this.milkAmt = milk;
	// }

	// public  void setCoffeeAmt(int coffee){
	// 	this.coffeeAmt = coffee;
	// }

	// public  void setWaterAmt(int water){
	// 	this.waterAmt = water;
	// }

	// public  void setChocolateAmt(int chocolate){
	// 	this.chocolateAmt = chocolate;
	// }
	
}
