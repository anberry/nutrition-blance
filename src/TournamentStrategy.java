package reportGA.nutritionBalance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;



public class TournamentStrategy implements ISelectStrategy {
	// トーナメントサイズ
	private static final int T_SIZE = 2;

	/* 選択 */
	@Override
	public int[] select(ArrayList<int[]> list, Comparator comp) {
		HashSet<int[]> randSet = new HashSet<int[]>();

		while (randSet.size() < T_SIZE) {
			randSet.add(list.get(MyRandom.getInstancee().nextInt(list.size())));
		}

		return Collections.max(randSet, comp);
	}

}

