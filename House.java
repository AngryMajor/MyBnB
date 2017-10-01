import java.util.logging.*;

public class House extends Property{	
	int numStories;
	int clearingFee;
	
	public int GetNumStories() {return numStories;}
	public int GetClearingFee() {return clearingFee;}
	
	public void SetNumStories(int num){
		if(num<=0){
			LOGGER.log(Level.WARNING,"Tryed to set:" + this.regNum + " number of stories to zero or less");
			return;
		}
		else{
			numStories = num;
		}
	}//end of numStories mutator
	public void SetClearingFee(int num){
		if(num<0){
		LOGGER.log(Level.WARNING,"Tryed to set:" + this.regNum + " nclearing fee to less than zero");
		return;
		}
		else{
			clearingFee = num;
		}
	}//end of clearing fee mutator
	
	public int GetTotalIncomeFromProperty(){
		int Total = 0;
		Total += super.GetTotalIncomeFromProperty();
		Total += clearingFee;
		return Total;
	}
	
	public House(int RegNum, int CostPD, int NumRentalDays, String OwnerName, String PostalAddress,int NumStories, int ClearingFee){
		super(RegNum,CostPD,NumRentalDays,OwnerName,PostalAddress);
		Type = new String("House");
		SetNumStories(NumStories);
		SetClearingFee(ClearingFee);		
	}//end of constructor
}//end of class
