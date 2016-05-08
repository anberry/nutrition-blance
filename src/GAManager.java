package reportGA.nutritionBalance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GAManager {
	// 問題
	IProblem problem;

	// 個体数
	private final int SIZE = 100;

	// 世代数
	private final int GENERATION_SIZE = 100;

	// 突然変異確率
	private final double MUTATION_PROBABILITY = 0.1;

	// 個体群
	private ArrayList<int[]> gene = new ArrayList<int[]>();

	// エリート
	private int[] elite;

	// 選択方法
	private ISelectStrategy selecter;

	// 交叉方法
	private ICrossoverStrategy crossoverer;

	public GAManager(String fileName) {
		selecter = new TournamentStrategy();
		crossoverer = new OnepointCrossoverStrategy();
//		crossoverer = new UniformCrossoverStrategy();

		problem = new NutritionProblem(fileName);
		initGene();

	}

	public void initGene() {
		gene.clear();
		gene.addAll(problem.initGene(SIZE));
		setElite();
	}

	public int getGenerationSize() {
		return GENERATION_SIZE;
	}

	public int[] getElite() {
		return elite;
	}

	// エリート保存
	public void setElite() {
		elite = Collections.max(gene, new ClazzComparator()).clone();
	}

	public double getFitness(int[] tmp) {
		return problem.getFitness(tmp);
	}

	// 選択
	public void select() {
		for (int j = 0; j < SIZE; j++) {
			gene.add(selecter.select(gene, new ClazzComparator()).clone());
		}
	}

	// 交叉
	public void crossover() {
		ArrayList<int[]> tmpGene = new ArrayList<int[]>();

		for (int j = 0; j < SIZE; j++) {
			int tmp1 = 0, tmp2 = 0;
			while (tmp1 == tmp2) {
				tmp1 = MyRandom.getInstancee().nextInt(SIZE);
				tmp2 = MyRandom.getInstancee().nextInt(SIZE);
			}
			tmpGene.addAll(crossoverer.crossover(gene.get(tmp1), gene.get(tmp2)));
		}

		gene.clear();
		gene.addAll(tmpGene);
	}

	// 突然変異
	public void mutate() {
		for (int j = 0; j < SIZE; j++) {
			for (int k = 0; k < gene.get(j).length; k++) {
				if (MyRandom.getInstancee().nextDouble() < MUTATION_PROBABILITY) {
					gene.get(j)[k] = (gene.get(j)[k] + 1) / 2;
				}
			}
		}
	}

	// 最弱と最強を交換
	public void exchange() {
		gene.set(gene.indexOf(Collections.min(gene, new ClazzComparator())), elite);
	}

	/* GA 実行 */
	public void start() {
		setElite();
		select();
		crossover();
		mutate();
		exchange();
	}

	/* エリート表示 */
	public void showEliteFitness() {
		System.out.println("Fitness of Elite | " + problem.getFitness(elite));
		System.out.println("That Elite");
		((NutritionProblem)problem).showFoodList(elite);
	}

	public void showClazzList(int[] tmp) {
		((NutritionProblem)problem).showFoodList(tmp);
	}

	class ClazzComparator implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			double fit1 = problem.getFitness((int[])o1);
			double fit2 = problem.getFitness((int[])o2);

			if (fit1 == fit2) {
				return 0;
			} else if (fit1 > fit2) {
				return 1;
			} else {
				return -1;
			}
		}
	}
}
