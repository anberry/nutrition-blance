package reportGA.nutritionBalance;

import java.util.ArrayList;
import java.util.Comparator;

public interface ISelectStrategy {
	/* 選択 */
	public int[] select(ArrayList<int[]> list, Comparator comp);
}
