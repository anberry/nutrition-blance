package reportGA.nutritionBalance;

import java.util.ArrayList;

import reportGA.problem.MyRandom;

/* 一点交叉 */
public class OnepointCrossoverStrategy implements ICrossoverStrategy {
	// 交叉率
	private final double CROSSOVER_PROBABILITY = 0.9;

	@Override
	public ArrayList<int[]> crossover(int[] order1, int[] order2) {
		ArrayList<int[]> tmpList = new ArrayList<int[]>();

		if(MyRandom.getInstancee().nextDouble() < CROSSOVER_PROBABILITY) {
			int point = MyRandom.getInstancee().nextInt(order1.length - 1);
			for (int i = point; i < order1.length; i ++) {
				int tmp = order1[i];
				order1[i] = order2[i];
				order2[i] = tmp;
			}
		}
		tmpList.add(order1.clone());
		tmpList.add(order2.clone());

		// 子供たちを返す
		return tmpList;
	}

}
