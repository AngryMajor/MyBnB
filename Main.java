import javax.swing.JOptionPane;
import java.util.logging.*;
import java.util.*;

enum PropType {house,apartment,villa};

public class MyBnB{
	
	private static final Logger LOGGER = Logger.getLogger( MyBnB.class.getName() );
	static Random rand = new Random();
	
	private static int MAXREGEDPROPS = 100;
	
	//all the storage arrays
	static List<Integer> allRegNums = new ArrayList<Integer>();
	static List<House> houses= new ArrayList<House>();
	static List<Apartment> aparts= new ArrayList<Apartment>();
	static List<Villa> villas= new ArrayList<Villa>();
	
	//regestration methods
	public static boolean DoseRegExist(int Reg){//checks if a given reg exists in the sytem already
		for(Integer i: allRegNums){
			if(i.equals(Reg)){
				return true;//reg exists
			}//end of check if the same
		}//end of for each loop
		return false;//reg dose not exist
	}
	public static boolean RegesterProperty(int Reg){//atempts to regester a property with a given reg number
		if(DoseRegExist(Reg) == false){
			allRegNums.add(new Integer(Reg));
			return true;
		}
		else return false;
	}
	public static int RegesterProperty(){//regesters a given property with then next avalable reg number
		
		int randReg = Math.abs(rand.nextInt());
		
		if(DoseRegExist(randReg) == false){
			
			allRegNums.add(new Integer(randReg));
			return randReg;
	
		}//end of reg dose not exist
		else{
			
			randReg++;
			//search for next empty regester number
			while(DoseRegExist(randReg) == true){
				randReg++; 
			}//end of loop to find non exixting reg
			allRegNums.add(new Integer(randReg));
			return randReg;
		
		}//end of random reg dose exist
	}//end of regester without spesific number
	public static void DeleteGivenReg(int Reg){//trys to delete a regestered property with a given reg number
		if(DoseRegExist(Reg) == true) allRegNums.remove(Reg);
	}
	
	//fill in data methods
	public static void FillInPropertis(){//this is a test method to fill in dumby propertys and check that the whole systme works
		
		//app property should normaly have a check for an error here but because the values are hard coded in i desided to forgo it as i know it works
		
		for(int i=0;i<3;i++){
		AddProperty(PropType.house,1,0,null,null,1,1);
		AddProperty(PropType.apartment,1,0,null,null,1,1);
		AddProperty(PropType.villa,1,0,null,null,1,1,1);
		}//end of creat properties
		
		for(int i=0;i<3;i++){
			for(Property prop : houses){
				RentAProperty(prop);
			}
			for(Property prop : aparts){
				RentAProperty(prop);
			}
			for(Property prop : villas){
				RentAProperty(prop);
			}
		}//end of rent each property three times

	}//end of fill in properties
	public static int CalculateTotalIncome(){
		int Total=0;
		for(Property prop : houses){
				Total += prop.GetTotalIncomeFromProperty();
			}
			for(Property prop : aparts){
				Total += prop.GetTotalIncomeFromProperty();
			}
			for(Property prop : villas){
				Total += prop.GetTotalIncomeFromProperty();
			}
		return Total;
	}
	
	//rent a property methods
	public static boolean RentAProperty(int propRegNum){//will add a renting of a given property

		if(DoseRegExist(propRegNum) == false) return false;
		
		//search for given property 
		for(Property prop : houses){
			if(prop.regNum == propRegNum){
				prop.AddOneRentealDay();
				return true;
			}
		}//end check houses
		for(Property prop : aparts){
			if(prop.regNum == propRegNum){
				prop.AddOneRentealDay();
				return true;
			}
		}//end of check aparts
		for(Property prop : villas){
			if(prop.regNum == propRegNum){
				prop.AddOneRentealDay();
				return true;
			}
		}//end of check villas
		
		LOGGER.log(Level.WARNING,"Regester number not attacheted to property");
		DeleteGivenReg(propRegNum);
		return false;
		
	}//end of rent property
	public static void RentAProperty(Property prop){
		prop.AddOneRentealDay();
	}
	public static boolean AddProperty(PropType PropertyType, int CostPD, int NumRentalDays, String OwnerName, String PostalAddress, int var1, int var2, int var3){//adds an individual property
		switch(PropertyType){
			case house:
			houses.add(new House(RegesterProperty(),CostPD,NumRentalDays,OwnerName,PostalAddress,var1,var2));
			break;
			case apartment:
			aparts.add(new Apartment(RegesterProperty(),CostPD,NumRentalDays,OwnerName,PostalAddress,var1,var2));
			break;
			case villa:
			if(var3 < 0) return false;
			villas.add(new Villa(RegesterProperty(),CostPD,NumRentalDays,OwnerName,PostalAddress,var1,var2,var3));
			break;
			default:
			return false;
		}//end of switch
		return true;
	}//end of add property
	public static boolean AddProperty(PropType PropertyType, int CostPD, int NumRentalDays, String OwnerName, String PostalAddress, int var1, int var2){
		return AddProperty(PropertyType,CostPD,NumRentalDays,OwnerName,PostalAddress,var1,var2,-1);
	}//end of add property overload
	public static void DeleteProperty(int Reg){
		for(Property prop : houses){
			if(prop.regNum == Reg){
				houses.remove(prop);
				DeleteGivenReg(Reg);
			}
		}//end check houses
		for(Property prop : aparts){
			if(prop.regNum == Reg){
				houses.remove(prop);
				DeleteGivenReg(Reg);
			}
		}//end of check aparts
		for(Property prop : villas){
			if(prop.regNum == Reg){
				houses.remove(prop);
				DeleteGivenReg(Reg);
			}		
		}//end of check villas
	}
	
	//display to screen methods
	public static void PrintAllProperties(){
		String Message = "";
		
		Message += "Houses \n";
		for(House prop : houses){
			Message += "Regestration number: " + prop.GetRegNum() + "\n";
			Message += "Cost per day: " + prop.GetCostPD() + "\n";
			Message += "number of days rented for: " + prop.GetNumRentalDays() + "\n";
			Message += "Owner's Name: " + prop.GetOwnerName() + "\n";
			Message += "Postal Address: " + prop.GetPostalAddress() + "\n";
			Message += "Number of stories: " + prop.GetNumStories() + "\n";
			Message += "Clearing fee: " + prop.GetClearingFee() + "\n";			
			Message += "Total income from this property: " + prop.GetTotalIncomeFromProperty() + "\n";
			Message += "\n\n";
		}
		
		JOptionPane.showMessageDialog(null,Message);
		Message = "Apartments\n";
		
		for(Apartment prop : aparts){
			Message += "Regestration number: " + prop.GetRegNum() + "\n";
			Message += "Cost per day: " + prop.GetCostPD() + "\n";
			Message += "number of days rented for: " + prop.GetNumRentalDays() + "\n";
			Message += "Owner's Name: " + prop.GetOwnerName() + "\n";
			Message += "Postal Address: " + prop.GetPostalAddress() + "\n";
			Message += "Story num: " + prop.GetStoryNum() + "\n";
			Message += "number of beds: " + prop.GetNumBeds() + "\n";
			Message += "Total income from this property: " + prop.GetTotalIncomeFromProperty() + "\n";
			Message += "\n\n";
		}
		
		JOptionPane.showMessageDialog(null,Message);
		Message = "Villas\n";
		
		for(Villa prop : villas){
			Message += "Regestration number: " + prop.GetRegNum() + "\n";
			Message += "Cost per day: " + prop.GetCostPD() + "\n";
			Message += "number of days rented for: " + prop.GetNumRentalDays() + "\n";
			Message += "Owner's Name: " + prop.GetOwnerName() + "\n";
			Message += "Postal Address: " + prop.GetPostalAddress() + "\n";
			Message += "number of rooms: " + prop.GetNumRooms() + "\n";
			Message += "Room Service cost: " + prop.GetServiceCost() + "\n";
			Message += "Luxury Tax: " + prop.GetLuxuryTax() + "\n";
			Message += "Total income from this property: " + prop.GetTotalIncomeFromProperty() + "\n";
			Message += "\n\n";
		}
		
		JOptionPane.showMessageDialog(null,Message);
		Message = "Total Income:\n";
		Message += "" + CalculateTotalIncome();
		JOptionPane.showMessageDialog(null,Message);
		
	}
	
	public static void main(String[] args){
		FillInPropertis();
		PrintAllProperties();
	}//end of main
}//end of class
