package reportGA.nutritionBalance;

import java.util.ArrayList;

public interface IProblem {
	/* 初期化 */
	public ArrayList<int[]> initGene(int size);

	/* 適応度を返す */
	public double getFitness(int[] tmp);
}
