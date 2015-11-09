import java.util.Scanner;

public class knapsack1 {
	static double c;
	static int n;
	static double w[];
	static double p[];
	static double cw;
	static double cp;
	static int bestX[];
	static MaxHeap heap;

	// 上界函数bound计算结点所相应价值的上界
	private static double bound(int i) {
		double cleft = c - cw;
		double b = cp;
		while (i <= n && w[i] <= cleft) {
			cleft = cleft - w[i];
			b = b + p[i];
			i++;
		}
		// 装填剩余容量装满背包
		if (i <= n)
			b = b + p[i] / w[i] * cleft;
		return b;
	}

	// addLiveNode将一个新的活结点插入到子集树和优先队列中
	private static void addLiveNode(double up, double pp, double ww, int lev,
			BBnode par, boolean ch) {
		// 将一个新的活结点插入到子集树和最大堆中
		BBnode b = new BBnode(par, ch);
		HeapNode node = new HeapNode(b, up, pp, ww, lev);
		heap.put(node);
	}

	private static double MaxKnapsack() {
		// 优先队列式分支限界法，返回最大价值，bestx返回最优解
		BBnode enode = null;
		int i = 1;
		double bestp = 0;// 当前最优值
		double up = bound(1);// 当前上界
		while (i != n + 1) {// 非叶子结点
			// 检查当前扩展结点的左儿子子结点
			double wt = cw + w[i];
			if (wt <= c) {
				if (cp + p[i] > bestp)
					bestp = cp + p[i];
				addLiveNode(up, cp + p[i], cw + w[i], i + 1, enode, true);
			}
			up = bound(i + 1);
			if (up >= bestp)
				addLiveNode(up, cp, cw, i + 1, enode, false);
			HeapNode node = (HeapNode) heap.removeMax();
			enode = node.liveNode;
			cw = node.weight;
			cp = node.profit;
			up = node.upperProfit;
			i = node.level;
		}
		for (int j = n; j > 0; j--) {

			bestX[j] = (enode.leftChild) ? 1 : 0;
			enode = enode.parent;
		}
		return cp;
	}

	public static double knapsack(double pp[], double ww[], double cc, int xx[]) {
		// 返回最大值，bestX返回最优解
		c = cc;
		n = pp.length - 1;
		// 定义以单位重量价值排序的物品数组
		Element q[] = new Element[n];
		double ws = 0.0;
		double ps = 0.0;
		for (int i = 0; i < n; i++) {
			q[i] = new Element(i + 1, pp[i + 1] / ww[i + 1]);
			ps = ps + pp[i + 1];
			ws = ws + ww[i + 1];
		}
		if (ws <= c) {
			return ps;
		}
		p = new double[n + 1];
		w = new double[n + 1];
		for (int i = 0; i < n; i++) {
			p[i + 1] = pp[q[i].id];
			w[i + 1] = ww[q[i].id];
		}
		cw = 0.0;
		cp = 0.0;
		bestX = new int[n + 1];
		heap = new MaxHeap(n);
		double bestp = MaxKnapsack();
		for (int j = 0; j < n; j++)
			xx[q[j].id] = bestX[j + 1];

		return bestp;

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("物品个数:");
		int n = sc.nextInt();
		System.out.println("背包容量：");
		double c = sc.nextDouble();
		System.out.println("物品重量：");
		double[] w = new double[n + 1];
		for (int i = 0; i < n; i++) {
			w[i + 1] = sc.nextDouble();
		}
		System.out.println("物品价值：");
		double[] v = new double[n + 1];
		for (int i = 0; i < n; i++) {
			v[i + 1] = sc.nextDouble();
		}
		int x[] = new int[6];
		double m = knapsack(v, w, c, x);
		System.out.println("最优值：=" + m);
		System.out.println("选中的物品是:");
		for (int i = 1; i <= 5; i++)
			System.out.print(x[i] + " ");
	}
}

// 子空间中节点类型
class BBnode {
	BBnode parent;// 父节点
	boolean leftChild;// 左儿子节点标志

	BBnode(BBnode par, boolean ch) {
		parent = par;
		leftChild = ch;
	}
}

class HeapNode implements Comparable {
	BBnode liveNode; // 活结点
	double upperProfit; // 结点的价值上界
	double profit; // 结点所相应的价值
	double weight; // 结点所相应的重量
	int level; // 活结点在子集树中所处的层次号

	// 构造方法
	public HeapNode(BBnode node, double up, double pp, double ww, int lev) {
		liveNode = node;
		upperProfit = up;
		profit = pp;
		weight = ww;
		level = lev;
	}

	public int compareTo(Object o) {

		double xup = ((HeapNode) o).upperProfit;
		if (upperProfit < xup)
			return -1;
		if (upperProfit == xup)
			return 0;
		else
			return 1;
	}
}

class Element implements Comparable {
	int id;
	double d;

	public Element(int idd, double dd) {
		id = idd;
		d = dd;
	}

	public int compareTo(Object x) {
		double xd = ((Element) x).d;
		if (d < xd)
			return -1;
		if (d == xd)
			return 0;
		return 1;
	}

	public boolean equals(Object x) {
		return d == ((Element) x).d;
	}
}

class MaxHeap {
	static HeapNode[] nodes;
	static int nextPlace;
	static int maxNumber;

	public MaxHeap(int n) {
		maxNumber = (int) Math.pow((double) 2, (double) n);
		nextPlace = 1;// 下一个存放位置
		nodes = new HeapNode[maxNumber];
	}

	public static void put(HeapNode node) {
		nodes[nextPlace] = node;
		nextPlace++;
		heapSort(nodes);

	}

	public static HeapNode removeMax() {
		HeapNode tempNode = nodes[1];
		nextPlace--;
		nodes[1] = nodes[nextPlace];
		heapSort(nodes);
		return tempNode;
	}

	private static void heapAdjust(HeapNode[] nodes, int s, int m) {
		HeapNode rc = nodes[s];
		for (int j = 2 * s; j <= m; j *= 2) {

			if (j < m && nodes[j].upperProfit < nodes[j + 1].upperProfit)
				++j;

			if (!(rc.upperProfit < nodes[j].upperProfit))
				break;
			nodes[s] = nodes[j];
			s = j;
		}
		nodes[s] = rc;
	}

	private static void heapSort(HeapNode[] nodes) {
		for (int i = (nextPlace - 1) / 2; i > 0; --i) {

			heapAdjust(nodes, i, nextPlace - 1);
		}
	}
}