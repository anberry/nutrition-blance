package reportGA.nutritionBalance;

import java.util.Random;

public class MyRandom {
	// 乱数シード
	private static Long seed;

	// 乱数ジェネレータ
	private static Random rand;

	private MyRandom() {

	}

	public static Random getInstancee() {
		if(rand == null) {
			if (seed == null) {
				setSeed(System.currentTimeMillis());
				rand = new Random(seed);
			}
			rand = new Random(seed);
		}

		return rand;
	}

	public static Long getSeed() {
		return seed;
	}

	public static boolean setSeed(Long tmp) {
		if (rand != null) {
			return false;
		}

		seed = tmp;
		return true;
	}
}
