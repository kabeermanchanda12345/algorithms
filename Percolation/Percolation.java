public class Percolation  {
	private WeightedQuickUnionUF mConnections;
	private STATE[][] mData;
	private int N;
	// create N-by-N grid, with all sites blocked
	public Percolation(int N) { 
		//The two extra are virtual nodes used to represent the top row
		// All the top rows are connected to to the second last virtual node
		// All the bottom rows are connected to the last node
		this.N = N;
		mConnections = new WeightedQuickUnionUF(N * N +2);
		for(int i = 0 ; i < N ; i++) {
			mConnections.union(i, N*N);
			mConnections.union((N-1)*(N)+i, N*N+1);
		}
		
		mData = new STATE[N][N];
		for(int i = 0 ; i < N ; i++) {
			for(int j= 0 ; j< N ; j++) {
				mData[i][j] = STATE.BLOCK;
			}
		}
	}
	// open site (row i, column j) if it is not already
	public void open(int i, int j){
		mData[i-1][j-1]= STATE.OPEN;
		//Left Neighbor
		if(j-2 >=0 && mData[i-1][j-2].ordinal() == STATE.OPEN.ordinal()) {
			mConnections.union((i-1)*(N)+j-2, (i-1)*(N)+j-1);
		}
		//Right Neighbor
		if(j <= N-1 && mData[i-1][j].ordinal() == STATE.OPEN.ordinal()) {
			mConnections.union((i-1)*(N)+j-1, (i-1)*(N)+j);
		}
		
		//Top Neighbor
		if(i-2 >= 0 && mData[i-2][j-1].ordinal() == STATE.OPEN.ordinal()) {
			mConnections.union((i-2)*(N)+j-1, (i-1)*(N)+j-1);
		}
		
		//Bottom Neighbor
		if(i <= N-1 && mData[i][j-1].ordinal() == STATE.OPEN.ordinal()) {
			mConnections.union((i-1)*(N)+j-1, (i)*(N)+j-1);
		}
	}
	
	
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		return mData[i-1][j-1].ordinal() == STATE.OPEN.ordinal();
	}
	
	// is site (row i, column j) full?
	public boolean isFull(int i, int j)  {
		return mConnections.connected((i-1)*N+(j-1), N*N);
	}
	// does the system percolate?
	public boolean percolates()   {
		return mConnections.connected(N*N+1, N*N);
	}
	
	private enum STATE{
		BLOCK,
		OPEN,
	}
}