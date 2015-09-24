import java.util.Scanner;


public class CoffeeMaker {
	public Vessel milkVessel, coffeeVessel, waterVessel, chocolateVessel;
	public Coffee esp,cap,moc,lat;
	
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
	
	public boolean isEnough(int quantity, Coffee ctype){
		/*int usedMilk = milkVessel.getUsed();
		int usedCoffee = coffeeVessel.getUsed();
		int usedWater = waterVessel.getUsed();
		int usedChocolate = chocolateVessel.getUsed();*/
		
		boolean isMilk = (quantity * ctype.getMilkAmt()) <= milkVessel.getCurrentVolume();
		boolean isCoffee = (quantity * ctype.getCoffeeAmt()) <= coffeeVessel.getCurrentVolume();
		boolean isWater = (quantity * ctype.getWaterAmt()) <= waterVessel.getCurrentVolume();
		boolean isChocolate = (quantity * ctype.getChocolateAmt()) <= chocolateVessel.getCurrentVolume();
		
		return (isMilk && isCoffee && isWater && isChocolate);
	}
	
	public void init(){
		milkVessel = new Vessel(10,"milk");
		coffeeVessel = new Vessel(10,"coffee");
		waterVessel = new Vessel(10,"water");
		chocolateVessel = new Vessel(10,"chocolate");		

		esp = new Coffee(0, 2, 4, 0, "Espresso");
		cap = new Coffee(2, 1, 4, 0, "Cappucino");
		moc = new Coffee(1, 2, 3, 1, "Mocha");
		lat = new Coffee(3, 2, 4, 0, "Latte");
	}
	
	public void mix(int q, Coffee cType){
		milkVessel.setCurrentVolume(milkVessel.getCurrentVolume() - (q * cType.getMilkAmt()));
		coffeeVessel.setCurrentVolume(coffeeVessel.getCurrentVolume() - (q * cType.getCoffeeAmt()));
		waterVessel.setCurrentVolume(waterVessel.getCurrentVolume() - (q * cType.getWaterAmt()));
		chocolateVessel.setCurrentVolume(chocolateVessel.getCurrentVolume() - (q * cType.getChocolateAmt()));
	}
	
	public void load(boolean isCoffeeSelected){
		
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
