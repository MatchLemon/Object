import java.util.Scanner;

public class JuZhen {
    int[] p;
    int[][] m;
    int[][] s;
    int length;
    public JuZhen(int[] p,int[][] m,int[][] s){
        this.p = p;
        this.length = p.length/2;
        this.m = m;
        this.s = s;
        
        init();
        clac();
        printM();
    }
    
    public void init(){
        for (int i=0;i<length;i++){
            m[i][i] = 0;
        }
        
        
    }
    
    public void clac(){
        for (int i=1;i<length;i++){
            for (int j=0;j<length-i;j++){
                int r = j+i;
                int t = Integer.MAX_VALUE;
                for (int k = j;k<r;k++){
                    int temp = m[j][k] + m[k+1][r] + p[j*2]*p[k*2+1]*p[r*2+1];
                    if (t > temp){
                        t = temp;
                        m[j][r] = temp;
                    }
                }
            }
        }
    }
    
    public void printM(){
        for (int i=0;i<length;i++){
            for (int j=0;j<length;j++){
                System.out.print(m[i][j]+ "\t");
            }
            System.out.println();
        }
    }
    
    public static void main(String args[]){
    	Scanner sc = new Scanner(System.in);
    	System.out.println("请输入矩阵个数：");
    	int length = sc.nextInt();        
        int[][] m = new int[length][length];
        int[][] s = new int[length][length];
        int[] p = new int[length*2];
    	System.out.println("请输入每个矩阵的规模，空格隔开（两个矩阵30*30和10*10的请输入‘30 30 10 10’）：");
        for(int i=0;i<length*2;i++){
        	p[i]=sc.nextInt();
        }
        new JuZhen(p,m,s);
        sc.close();
    }
}