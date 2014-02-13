
public class PercolationStats {
	private double[] mProbabilities;
   public PercolationStats(int N, int T) {
	   mProbabilities = new double[T];
	   // perform T independent computational experiments on an N-by-N grid
	   for (int i = 0; i < T; i++) {
		   Percolation p = new Percolation(N);
		   int numberOpened = 0;
		   while (!p.percolates()) {
			   
			int x = (int) (1+StdRandom.random() * (N));
		   	int y = (int) (1+StdRandom.random() * (N));
		   	if (!p.isOpen(x, y)) {
		   		p.open(x, y);
		   		numberOpened += 1;
		   	}
		   }
		   mProbabilities[i] = ((double) numberOpened/(N*N));
	   }
   }
   public double mean() {
	   return StdStats.mean(mProbabilities);
   }
   public double stddev()     {
	   return StdStats.stddev(mProbabilities);
   }
   public double confidenceLo()     {
	   // returns lower bound of the 95% confidence interval
	   double low = mean()-1.96*stddev()/Math.sqrt(mProbabilities.length);
	   return low;
   }
   public double confidenceHi()       {
	   // returns upper bound of the 95% confidence interval
	   double high = mean()+1.96*stddev()/Math.sqrt(mProbabilities.length);
	   return high;
   }
   public static void main(String[] args)  {
	   int N = Integer.parseInt(args[0]);
	   int T = Integer.parseInt(args[1]);
	   // test client, described below
	   PercolationStats stats = new PercolationStats(N, T);
	   System.out.println("Mean= "+stats.mean());
	   System.out.println("stdev= "+stats.stddev());
	   System.out.println("95 % confidence= "+stats.confidenceLo()
			   +" ,"+stats.confidenceHi());
	   
   }
}