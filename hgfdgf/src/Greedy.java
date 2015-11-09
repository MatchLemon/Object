import java.util.Arrays;
import java.util.Scanner;

public class Greedy {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("��Ʒ����:");
		int n = in.nextInt();
		int[] w = new int[n]; // ��Ʒ��������
		int[] v = new int[n]; // ��Ʒ��Ǯ����
		System.out.println("��Ʒ����:");
		for (int i = 0; i < n; i++) {
			w[i] = in.nextInt();
		}
		System.out.println("��Ʒ��ֵ��");
		for (int i = 0; i < n; i++) {
			v[i] = in.nextInt();
		}
		System.out.println("����������");
		int c = in.nextInt();

		/**
		 * ����λ������ֵr[i] = v[i] / w[i]��������
		 * 
		 * ps:�����õ���ѡ������������鿴ѡ������
		 */
		double startTime = System.currentTimeMillis();
		double[] r = new double[n];
		int[] index = new int[n];
		for (int i = 0; i < n; i++) {
			r[i] = (double) v[i] / (double) w[i];
			index[i] = i;
		}
		double temp = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (r[i] < r[j]) {
					temp = r[i];
					r[i] = r[j];
					r[j] = temp;
					int x = index[i];
					index[i] = index[j];
					index[j] = x;
				}
			}
		}

		/**
		 * �����������ͼ�ֵ�ֱ�浽w1[]��v1[]��
		 */
		int[] w1 = new int[n];
		int[] v1 = new int[n];
		for (int i = 0; i < n; i++) {
			w1[i] = w[index[i]];
			v1[i] = v[index[i]];
		}

		/**
		 * ��ʼ��������x[n]
		 */
		int[] x = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = 0;
		}

		/**
		 * ��Ⲣ��ӡ������
		 */
		for (int i = 0; i < n; i++) {
			if (w1[i] < c) {
				x[i] = 1;
				c = c - w1[i];
			}
		}
		System.out.println("ѡ�е���Ʒ��" + Arrays.toString(x));

		/**
		 * ���ݽ�������������д����Ʒ������ֵ����ӡ
		 */
		int maxValue = 0;
		for (int i = 0; i < n; i++) {
			if (x[i] == 1)
				maxValue += v1[i];
		}
		double endTime = System.currentTimeMillis();
		System.out.println("����ֵ��" + maxValue);
	}
}