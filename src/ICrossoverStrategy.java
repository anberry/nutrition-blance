package reportGA.nutritionBalance;

import java.util.ArrayList;
import java.util.Comparator;

public interface ICrossoverStrategy {

	/* 交叉 */
	public ArrayList<int[]> crossover(int[] order1, int[] order2);
}
