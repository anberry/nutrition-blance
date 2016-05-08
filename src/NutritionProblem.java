package reportGA.nutritionBalance;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class NutritionProblem implements IProblem {
	// 読み込むファイル
	String fileName;

	//エネルギー量ペナルティ
	private final double ENERGY_TOTAL = 1800;
	private final double ENERGY_PENALTY = 10;

	// 1群ペナルティ
	private double FIRST_TOTAL = 3;
	private double FIRST_PENALTY = 5;

	// 2群ペナルティ
	private double SECOND_TOTAL = 3;
	private double SECOND_PENALTY = 5;

	// 3群ペナルティ
	private double THIRD_TOTAL = 3;
	private  double THIRD_PENALTY = 5;

	//4群ペナルティ
	private double FOURTH_TOTAL =11;
	private double FOURTH_PENALTY = 5;


	// 食品名 (遺伝子長)
	private ArrayList<Nutrition> foodList = new ArrayList<Nutrition>();


	public NutritionProblem() {

	}

	public NutritionProblem(String fileName) {
		this.fileName = fileName;
		initMenue(fileName);
	}

	/* 初期個体群生成 */
	@Override
	public ArrayList<int[]> initGene(int size) {
		// TODO 自動生成されたメソッド・スタブ
		ArrayList<int[]> gene = new ArrayList<int[]>();

		for (int i = 0; i < size; i++) {
			int[] tmp = new int[foodList.size()];
			for (int j = 0; j < tmp.length; j++) {
				tmp[j] = MyRandom.getInstancee().nextDouble() < 0.5 ? 1 : 0;
			}
			gene.add(tmp);
		}

		return gene;
	}

	/* メニュー情報の初期化 */
	public void initMenue(String fileName) {

		try {
			// Excelのワークブックを読み込み
			POIFSFileSystem filein = new POIFSFileSystem(new FileInputStream(fileName));
			HSSFWorkbook wb = new HSSFWorkbook(filein);

			// シートを読み込み
			HSSFSheet sheet = wb.getSheet("NutritionSheet");

			// 食品の初期化
			for (int i = 1; i <sheet.getPhysicalNumberOfRows(); i++ ) {
				if (sheet.getRow(i).getPhysicalNumberOfCells() == sheet.getRow(0).getPhysicalNumberOfCells()) {
					foodList.add(new Nutrition(sheet.getRow(i)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("処理が失敗しました");
		}
	}

	/* 適応度計算 */
	public double getFitness(int[] tmp) {
		double fitness = 0;
		/*
		 * 総エネルギー1800以上
		 * 総1群2点以下
		 * 総2群2点以下
		 * 総3群2点以下
		 * 総4群11点以下
		 *
		 */

		// 取るべき食品名
		ArrayList<ArrayList<Nutrition>> foodTaken = new ArrayList<ArrayList<Nutrition>>();
		
		ArrayList<Nutrition> taken = new ArrayList<Nutrition>();
		
		foodTaken.add(taken);
		for (int i= 0; i<tmp.length;i++){
			if (tmp [i] ==1){
				fitness += foodList.get(i).getValue();
				taken.add(foodList.get(i));
			}
		}

		// エネルギー過剰のペナルティ
		double energyTotal = 0;
        for (ArrayList<Nutrition> energy : foodTaken){
        	for (Nutrition food : energy){
        		energyTotal += food.getEnergy();
        	}
        	if (energyTotal > ENERGY_TOTAL){
        		fitness -= ENERGY_PENALTY * ENERGY_PENALTY * (energyTotal - ENERGY_TOTAL);
        	}
        }

        // 1群足りないのペナルティ
        double firstGTotal = 0;
        for (ArrayList<Nutrition> first : foodTaken){
        	for (Nutrition food : first){
        		firstGTotal += food.getFirstG();
        	}
        	if (firstGTotal < FIRST_TOTAL){
        		fitness -= FIRST_PENALTY * FIRST_PENALTY * ( FIRST_TOTAL-firstGTotal);
        	}
        }

     // 2群足りないのペナルティ
        double secondGTotal = 0;
        for (ArrayList<Nutrition> second : foodTaken){
        	for (Nutrition food : second){
        		secondGTotal += food.getSecondG();
        	}
        	if (secondGTotal < SECOND_TOTAL){
        		fitness -= SECOND_PENALTY * SECOND_PENALTY * (SECOND_TOTAL - secondGTotal);
        	}
        }

     // 3群足りないのペナルティ
        double thirdGTotal = 0;
        for (ArrayList<Nutrition> third : foodTaken){
        	for (Nutrition food : third){
        		thirdGTotal += food.getThirdG();
        	}
        	if (thirdGTotal < THIRD_TOTAL){
        		fitness -= THIRD_PENALTY * THIRD_PENALTY * (THIRD_TOTAL - thirdGTotal);
        	}
        }

     // 4群足りないのペナルティ
        double fourthGTotal = 0;
        for (ArrayList<Nutrition> fourth : foodTaken){
        	for (Nutrition food : fourth){
        		fourthGTotal += food.getFourthG();
        	}
        	if (fourthGTotal < FOURTH_TOTAL){
        		fitness -= FOURTH_PENALTY * FOURTH_PENALTY * ( FOURTH_TOTAL - fourthGTotal);
        	}
        }
		return fitness;
	}

	// メニュー表示
	public void showFoodList(int[] tmp) {
		for (int i = 0; i < tmp.length; i++) {
			System.out.print(tmp[i] + " ");
			if (tmp[i] == 1) {
				foodList.get(i).showData();
			} else {
				System.out.println();
			}
		}
	}

}
