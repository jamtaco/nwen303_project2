import java.util.Random;

import mpi.MPI;
import mpi.MPIException;


public class SelectionSortParallel {

	private static int max = 50000;
	private static int min = 1;
	private static int size = max;


	//public static void main(String[] args, int[] nums) throws MPIException {
	public static void main(String[] args) throws MPIException {
		int[] nums = makeList();

		MPI.Init(args) ;

        int myrank = MPI.COMM_WORLD.getRank() ;
        int size = MPI.COMM_WORLD.getSize() ;

        int partition = nums.length/size;


        if (0 == myrank) {
        	partition = nums.length/size;
        	System.out.println("Num Processes: " + size);
        }
    	long startTime = System.currentTimeMillis();
        int[] newarray = new int[partition];

        MPI.COMM_WORLD.scatter(nums, partition, MPI.INT,newarray, partition,MPI.INT,0);
//        SelectionSort s = new SelectionSort();
//        s.sort(newarray);
        sort(newarray);
        MPI.COMM_WORLD.gather(nums, 0, MPI.INT,newarray,0,MPI.INT,0);

        if (myrank == 0) {
        	 long elapsedTime = System.currentTimeMillis() - startTime;
         	System.out.println("Sorted Array..");
             System.out.println("Selection Sort (Parallel) Time: " + elapsedTime + "ms\n");
        }

        MPI.Finalize();

        //Main.printnums(newarray);
    }

	public static void sort(int[] nums){
		for(int i = 0; i < nums.length; i++){
			int smallest = i;
			//iterate further down list for smaller number
			for(int j = i + 1; j < nums.length; j++){
				//found smaller num
				if(nums[j] < nums[smallest]){
					smallest = j;
				}
			}
			//swap smallest with i
			int temp = nums[i];
			nums[i] = nums[smallest];
			nums[smallest] = temp;
		}
	}

	/*
	 * The following are used for testing parallel on grid
	 */

	//makes array of random nums of specified size "size"
		public static int[] makeList(){
			int [] nums = new int[size];
			Random ran = new Random();
			for(int i = 0; i < nums.length; i++){
				//add random number from given range into array
				nums[i] = ran.nextInt((max - min) + 1) + min;
			}
			return nums;
		}

		//prints out array
		public static void printnums(int[] nums){
			for(int i = 0; i < nums.length; i++){
				if(i < nums.length-1){
					if(nums[i] > nums[i+1]){
						System.out.println("Array Unsorted!");
						break;
					}
				}
				System.out.print(nums[i] + ",");
			}
			System.out.println();
		}

}
