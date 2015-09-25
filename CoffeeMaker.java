import java.util.Scanner;


public class CoffeeMaker {
	// public Vessel milkVessel, coffeeVessel, waterVessel, chocolateVessel;
	public Vessel rVessel;
	public Coffee esp,cap,moc,lat;
	public static boolean condition = true;
	
	public CoffeeMaker(){
		init();
	}
	
	public static void main(String[] args){
		CoffeeMaker coffeeMaker = new CoffeeMaker();
		Scanner in = new Scanner(System.in);
		
		
		int q = 0;
		int type;
		Coffee[] coffeeTypes = {coffeeMaker.esp, coffeeMaker.cap, coffeeMaker.moc, coffeeMaker.lat};
		
		System.out.print("Welcome. \nWould you like to refill? '1'-YES, '2'-NO ");
		if (in.nextInt() == 1) {
			
			coffeeMaker.load(false);
		}

		while(condition){

			System.out.print("Quantity? ");
			q = in.nextInt();
			System.out.print("Coffee type? \n'1' Espresso\n'2' Cappucino\n'3' Mocha\n'4' Latte ");
			type = in.nextInt();
		
			if (type < 5 && type > 0){
			Coffee cfSelected = coffeeTypes[type - 1];
			System.out.println("You selected "+cfSelected.getName());
			//System.out.println("Is enough: "+ coffeeMaker.isEnough(q, coffeeTypes[type - 1]));
			if (coffeeMaker.isEnough(q, cfSelected)){
				coffeeMaker.mix(q, cfSelected);
				coffeeMaker.brew(q, cfSelected);
			}else{
				coffeeMaker.load(true);
			}
			}


		}
		
	}
	
	public boolean isEnough(int quantity, Coffee ctype){
		/*int usedMilk = milkVessel.getUsed();
		int usedCoffee = coffeeVessel.getUsed();
		int usedWater = waterVessel.getUsed();
		int usedChocolate = chocolateVessel.getUsed();*/
		
		boolean isMilk = (quantity * ctype.getMilkAmt()) <= rVessel.getMilkCurrentVolume();
		boolean isCoffee = (quantity * ctype.getCoffeeAmt()) <= rVessel.getCoffeeCurrentVolume();
		boolean isWater = (quantity * ctype.getWaterAmt()) <= rVessel.getWaterCurrentVolume();
		boolean isChocolate = (quantity * ctype.getChocolateAmt()) <= rVessel.getChocolateCurrentVolume();
		
		return (isMilk && isCoffee && isWater && isChocolate);
	}
	
	public void init(){
		rVessel = new Vessel(10);
		// milkVessel = new Vessel(10,"milk");
		// coffeeVessel = new Vessel(10,"coffee");
		// waterVessel = new Vessel(10,"water");
		// chocolateVessel = new Vessel(10,"chocolate");		

		esp = new Coffee(0, 2, 4, 0, "Espresso");
		cap = new Coffee(2, 1, 4, 0, "Cappucino");
		moc = new Coffee(1, 2, 3, 1, "Mocha");
		lat = new Coffee(3, 2, 4, 0, "Latte");
	}
	
	public void mix(int q, Coffee cType){
		rVessel.setMilkCurrentVolume(rVessel.getMilkCurrentVolume() - (q * cType.getMilkAmt()));
		rVessel.setCoffeeCurrentVolume(rVessel.getCoffeeCurrentVolume()  - (q * cType.getCoffeeAmt()));
		rVessel.setWaterCurrentVolume(rVessel.getWaterCurrentVolume()  - (q  * cType.getWaterAmt()));
		rVessel.setChocolateCurrentVolume(rVessel.getChocolateCurrentVolume()  - (q * cType.getChocolateAmt()));

		// milkVessel.setCurrentVolume(milkVessel.getCurrentVolume() - (q * cType.getMilkAmt()));
		// coffeeVessel.setCurrentVolume(coffeeVessel.getCurrentVolume() - (q * cType.getCoffeeAmt()));
		// waterVessel.setCurrentVolume(waterVessel.getCurrentVolume() - (q * cType.getWaterAmt()));
		// chocolateVessel.setCurrentVolume(chocolateVessel.getCurrentVolume() - (q * cType.getChocolateAmt()));
	}
	
	public void load(boolean decision){
		Scanner in_2 = new Scanner(System.in);
   		System.out.println("Ingredient refilling Menu");

		System.out.print("Load milk: ");
		int qMilk = in_2.nextInt();
		System.out.print("Load water: ");
		int qWater = in_2.nextInt();
		System.out.print("Load coffee: ");
		int qCoffee = in_2.nextInt();
		System.out.print("Load chocolate: ");
		int qChocolate = in_2.nextInt();

		// loading the milk, this will add the current level oof milk and the value loaded
		rVessel.setMilkCurrentVolume(rVessel.getMilkCurrentVolume() + qMilk);
		rVessel.setCoffeeCurrentVolume(rVessel.getCoffeeCurrentVolume() + qCoffee);
		rVessel.setChocolateCurrentVolume(rVessel.getChocolateCurrentVolume() + qChocolate);
		rVessel.setWaterCurrentVolume(rVessel.getWaterCurrentVolume() + qWater);

		
		// Coffee refill = new Coffee(qMilk, qCoffee, qWater, qChocolate, "");

  }
	
	public void brew(int q, Coffee cfSel){
		int i = 0;
		while (i < 4){
			try{
				Thread.sleep(1000);
				System.out.println("Brewing, Please wait...");
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
			i++;
		}
		boolean plural = q > 1;
		System.out.println("Here "+(plural ? "are" : "is")+" your " + q + " cup"+(plural ? "s" : "")+" of " + cfSel.getName());
	}
}
