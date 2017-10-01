import java.util.logging.*;

public class Villa extends Property{
	int numRooms;
	int roomServiceCost;
	int luxuryTax;
	
	public int GetNumRooms() {return numRooms;}
	public int GetServiceCost() {return roomServiceCost;}
	public int GetLuxuryTax() {return luxuryTax;}
	
	public void SetNumRooms(int num){
		if(num<0){
			LOGGER.log(Level.WARNING,"Tryed to set:" + this.regNum + " num of rooms to less then zero");
			return;
		}
		else{
			numRooms = num;
		}
	}//end of set num rooms mutator
	public void SetRoomServiceCost(int num){
		if(num<0){
			LOGGER.log(Level.WARNING,"Tryed to set:" + this.regNum + " room service cost to less then zero");
			return;
		}
		else{
			roomServiceCost = num;
		}
	}//end of room service cost mutator
	public void SetLuxuryTax(int num){
				if(num<0){
			LOGGER.log(Level.WARNING,"Tryed to set:" + this.regNum + " luxuryTax to less then zero");
			return;
		}
		else{
			luxuryTax = num;
		}
	}//end of luxury tax mutator
	public int GetTotalIncomeFromProperty(){
		int Total = 0;
		Total += super.GetTotalIncomeFromProperty();
		Total += luxuryTax*numRentalDays;
		Total += roomServiceCost*numRentalDays;
		return Total;
	}
	
	public Villa(int RegNum, int CostPD, int NumRentalDays, String OwnerName, String PostalAddress, int NumRooms, int RoomServiceCost, int LuxuryTax){
		super(RegNum,CostPD,NumRentalDays,OwnerName,PostalAddress);
		Type = new String("Villa");
		SetNumRooms(NumRooms);
		SetRoomServiceCost(RoomServiceCost);
		SetLuxuryTax(LuxuryTax);
	}//end of constructor
}//end of class
