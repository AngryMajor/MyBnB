import java.util.logging.*;

public class Apartment extends Property{	
	int storeyNum;
	int numBeds;
	
	public int GetStoryNum() {return storeyNum;}
	public int GetNumBeds() {return numBeds;}
	
	public void SetStoryNum(int num){
		storeyNum = num;
	}//end of story num mutator
	public void SetNumBeds(int num){
		if(num<0){
			LOGGER.log(Level.WARNING,"Tryed to set:" + this.regNum + " num of beds to less then zero");
			return;
		}
		else{
			numBeds = num;
		}
	}//end of num beds mutator
	public int GetTotalIncomeFromProperty(){
		int Total = 0;
		Total += super.GetTotalIncomeFromProperty();
		return Total;
	}
	
	public Apartment(int RegNum, int CostPD, int NumRentalDays, String OwnerName, String PostalAddress, int StoryNum, int NumBeds){
		super(RegNum,CostPD,NumRentalDays,OwnerName,PostalAddress);
		Type = new String("Apartment");
		SetStoryNum(StoryNum);
		SetNumBeds(NumBeds);
	}//end of constructor
}//end of class
