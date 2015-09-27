import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;


public class CoffeeMaker {
	// public Vessel milkVessel, coffeeVessel, waterVessel, chocolateVessel;
	public Vessel rVessel;
	public Coffee esp,cap,moc,lat;
	public static boolean condition = true;	
	int qSel = 0;
	Coffee[] coffeeTypes = new Coffee[4];
	Coffee selectedCoffee;
	
	public CoffeeMaker(){
		init();
	}
	
	public boolean isEnough(int quantity, Coffee ctype){
		int rMilk = quantity * ctype.getMilkAmt();
		int rCoffee = (quantity * ctype.getCoffeeAmt());
		int rWater = (quantity * ctype.getWaterAmt());
		int rChocolate = (quantity * ctype.getChocolateAmt());
		
		boolean isMilk = rMilk <= rVessel.getMilkCurrentVolume();
		boolean isCoffee = rCoffee <= rVessel.getCoffeeCurrentVolume();
		boolean isWater = rWater <= rVessel.getWaterCurrentVolume();
		boolean isChocolate = rChocolate <= rVessel.getChocolateCurrentVolume();
		
		getStock();
		System.out.println("\nPlease add the following for your "+ctype.name+"\n"+
				"Milk: "+rMilk+", Coffee: "+ rCoffee+", Water: "+rWater+", Chocolate: "+rChocolate+"\n");
		return (isMilk && isCoffee && isWater && isChocolate);
	}
	
	public void getStock(){
		System.out.println("\n\n----------------------Current stock-------------------- \n"+
				"The machine currently has: "+
				"Milk: "+rVessel.getMilkCurrentVolume()+", Coffee: "+ rVessel.getCoffeeCurrentVolume()+
				", Water: "+rVessel.getWaterCurrentVolume()+", Chocolate: "+rVessel.getChocolateCurrentVolume());
	}
	
	public void init(){
		rVessel = new Vessel(0);
		rVessel.setMaxVolume(10);	

		esp = new Coffee(0, 2, 4, 0, "Espresso");
		cap = new Coffee(2, 1, 4, 0, "Cappucino");
		moc = new Coffee(1, 2, 3, 1, "Mocha");
		lat = new Coffee(3, 2, 4, 0, "Latte");

		coffeeTypes[0] = esp;
		coffeeTypes[1] = cap;
		coffeeTypes[2] = moc;
		coffeeTypes[3] = lat;
	}
	
	public void mix(int q, Coffee cType){
		rVessel.setMilkCurrentVolume(rVessel.getMilkCurrentVolume() - (q * cType.getMilkAmt()));
		rVessel.setCoffeeCurrentVolume(rVessel.getCoffeeCurrentVolume()  - (q * cType.getCoffeeAmt()));
		rVessel.setWaterCurrentVolume(rVessel.getWaterCurrentVolume()  - (q  * cType.getWaterAmt()));
		rVessel.setChocolateCurrentVolume(rVessel.getChocolateCurrentVolume()  - (q * cType.getChocolateAmt()));
		
		saveIt();
	}
	
	public void load(boolean coffeeIsSelected){
		Scanner in_2 = new Scanner(System.in);
   		System.out.println("\n--------------------Ingredient Refilling Menu-----------------------\n");
   		
   		int usedMilk = rVessel.getMaxVolume() - rVessel.getMilkCurrentVolume();
   		int usedCoffee = rVessel.getMaxVolume() - rVessel.getCoffeeCurrentVolume();
   		int usedWater = rVessel.getMaxVolume() - rVessel.getWaterCurrentVolume();
   		int usedChocolate = rVessel.getMaxVolume() - rVessel.getChocolateCurrentVolume();
   		int qMilk,qWater,qCoffee,qChocolate;
   		if(usedMilk > 0){
			System.out.print("Load milk by: "+usedMilk + " units\nPlease input a value: ");
			qMilk = in_2.nextInt();
			rVessel.setMilkCurrentVolume(rVessel.getMilkCurrentVolume() + qMilk);
   		}
   		if(usedWater > 0){
			System.out.print("Load water by: "+usedWater + " units\nPlease input a value: ");
			qWater = in_2.nextInt();
			rVessel.setWaterCurrentVolume(rVessel.getWaterCurrentVolume() + qWater);
   		}
   		if(usedCoffee > 0){
			System.out.print("Load coffee by: "+ usedCoffee + " units\nPlease input a value: ");
			qCoffee = in_2.nextInt();
			rVessel.setCoffeeCurrentVolume(rVessel.getCoffeeCurrentVolume() + qCoffee);
   		}
   		if(usedChocolate > 0){
			System.out.print("Load chocolate by: "+usedChocolate + " units\nPlease input a value: ");
			qChocolate = in_2.nextInt();
			rVessel.setChocolateCurrentVolume(rVessel.getChocolateCurrentVolume() + qChocolate);
		}
   		
   		saveIt();
   		
   		if(coffeeIsSelected)
   			makeIt(qSel, selectedCoffee);

  }

	private void makeIt(int q, Coffee cfSelected) {
		if (isEnough(q, cfSelected)){
			mix(q, cfSelected);
			brew(q, cfSelected);
		}else{
			logIt("Not enough ingredients to make "+cfSelected.getName()+" proceeding to refill");
			System.out.println("Refill ingredients to make the " + cfSelected.name);
			load(true);
		}
	}

	
	public void brew(int q, Coffee cfSel){
		int i = 0;
		while (i < 4){
			try{
				Thread.sleep(400);
				System.out.println("Brewing...");
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
			i++;
		}
		boolean plural = q > 1;
		System.out.println("Here "+(plural ? "are" : "is")+" your " + q + " cup"+(plural ? "s" : "")+" of " + cfSel.getName());
	}

	public void saveIt(){
		File file = new File("save.txt");
	      // creates the file
		try {
			String saveOut = rVessel.getMilkCurrentVolume()+"\n"+ rVessel.getCoffeeCurrentVolume()+
					"\n"+rVessel.getWaterCurrentVolume()+"\n"+rVessel.getChocolateCurrentVolume();
			file.createNewFile();
			FileWriter writer = new FileWriter(file); 
			writer.write(saveOut); 
			writer.flush();
			writer.close();
			logIt("Saved data " + saveOut.replace('\n', ','));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logIt("file error when saving coffee-maker state to file");
		}
	}
	
	public void logIt(String mes){
		File file = new File("log.txt");
	      // creates the file
		try {
			if(!file.exists()){
    			file.createNewFile();
    		}
			
			String time = LocalDateTime.now().toString();
			//file.i.createNewFile();
			FileWriter writer = new FileWriter(file,true); 
			writer.write(time + " : " + mes+"\n"); 
			writer.flush();
			writer.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void loadIt(){
		Scanner fileIn;
		try {
			fileIn = new Scanner(new FileReader("save.txt"));
			ArrayList<Integer> inFile = new ArrayList<Integer>();
			while(fileIn.hasNextLine()){
				inFile.add(Integer.parseInt(fileIn.nextLine()));
			}
			rVessel.setMilkCurrentVolume(inFile.get(0));
			rVessel.setCoffeeCurrentVolume(inFile.get(1));
			rVessel.setWaterCurrentVolume(inFile.get(2));
			rVessel.setChocolateCurrentVolume(inFile.get(3));
			
			logIt("Loading data from file " + inFile.get(0) + ", " + inFile.get(1) + ", "+ inFile.get(2) + ", "+ inFile.get(3));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//main method
	public static void main(String[] args){
		CoffeeMaker coffeeMaker = new CoffeeMaker();
		coffeeMaker.loadIt();
		Scanner in = new Scanner(System.in);
		
		int type;
		coffeeMaker.getStock();
		System.out.print("Welcome. \nWould you like to refill? '1'-YES, '2'-NO ");
		if (in.nextInt() == 1) {
			coffeeMaker.load(false);
		}
	
		while(condition){
			condition = false;
			
			try{
				System.out.print("\nNumber of cups:  - 1 or 2 ");
				coffeeMaker.qSel = in.nextInt();
				if(coffeeMaker.qSel < 2 && coffeeMaker.qSel > 0 ){
					System.out.print("Coffee type? \n'1' Espresso\n'2' Cappucino\n'3' Mocha\n'4' Latte ");
					type = in.nextInt();
					if (type < 5 && type > 0){
						Coffee cfSelected = coffeeMaker.coffeeTypes[type - 1];
						coffeeMaker.selectedCoffee = cfSelected;
						System.out.println("You selected "+cfSelected.getName());
						//System.out.println("Is enough: "+ coffeeMaker.isEnough(q, coffeeTypes[type - 1]));
						coffeeMaker.makeIt(coffeeMaker.qSel, cfSelected);
					}
				}else{
					System.out.println("Please input a quantity greater than 0 and and less than 3");
				}
			}catch(NumberFormatException e){
				System.out.println("Wrong number input entered");
			}
		
	
			System.out.println("Do you want to make another Coffee? \'1' - Yes or '2' - No");
			int input = in.nextInt();
			if (input == 2) {
				System.out.println("Thank you... \nEnjoy your coffee");
				break;
			}else if(input == 1){
				condition = true;
				continue;
			}else{
				System.out.println("Wrong input entered");
			}
		}
		
	}
}
