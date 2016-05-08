package reportGA.nutritionBalance;

import org.apache.poi.hssf.usermodel.HSSFRow;

public class Nutrition {
	//食品名
	private String foodName;

	// エネルギー量
	private double energy;

	// 1群
	private double firstG;

	// 2群
	private double secondG;

	// 3群
	private double thirdG;

	// 4群
	private double fourthG;

	// 価値
	private double value;

	private final double energyFitness = 3;

	// firstG Fitness
	private final double firstGFitness = 3;

	// secondG Fitness
	private final double secondGFitness = 3;

	// thirdG Fitness
	private final double thirdGFitness = 3;

	// fourthG Fitness
	private double fourthGFitness = 11;

	public Nutrition(HSSFRow row) {
		initClazz(row);
	}

	private void initClazz(HSSFRow row) {

		foodName = row.getCell(0).toString();
		energy = (double)Double.parseDouble(row.getCell(1).toString());
		firstG = (double)Double.parseDouble(row.getCell(2).toString());
		secondG = (double)Double.parseDouble(row.getCell(3).toString());
		thirdG = (double)Double.parseDouble(row.getCell(4).toString());
		fourthG = (double)Double.parseDouble(row.getCell(5).toString());

		value = (( firstG * firstGFitness +
				  secondG * secondGFitness +
				  thirdG * thirdGFitness + fourthG * fourthGFitness)) + 10;
	}

	public String getFoodName() {
		return foodName;
	}

	public double getEnergy() {
		return energy;
	}

	public double getFirstG() {
		return firstG;
	}

	public double getSecondG() {
		return secondG;
	}

	public double getThirdG() {
		return thirdG;
	}

	public double getFourthG(){
		return fourthG;
	}
	public double getValue() {
		return value;
	}

	public void showData() {
		System.out.println("[" + foodName + "] : エネルギー" + energy + " ,1群" + firstG + " ,2群" + secondG + " ,3群" + thirdG + " ,4群" + fourthG );
	}
}
