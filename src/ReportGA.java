package reportGA.nutritionBalance;

import java.util.ArrayList;

public class ReportGA {

	public static void main(String[] args) {
		GAManager manager = new GAManager("D:\\GA\\nutrition_data.xls");

		ArrayList<int[]> result = new ArrayList<int[]>();

		int count = 0;
		while (count < 10) {
			manager.initGene();
		for (int i = 0; i < manager.getGenerationSize(); i++) {
			manager.start();
//			manager.showErite();
		}
		result.add(manager.getElite());
		count++;
		System.out.println(count);
		manager.showEliteFitness();
		}

		for (int[] tmp : result) {
			System.out.println(manager.getFitness(tmp));
			manager.showClazzList(tmp);
		}
	}
}
