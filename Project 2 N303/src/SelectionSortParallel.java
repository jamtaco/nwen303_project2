import java.util.Random;

import mpi.MPI;
import mpi.MPIException;
import mpi.Status;


public class SelectionSortParallel {

	private static int max = 100;
	private static int min = 1;
	private static int size = max;


	public static void main(String[] args) throws MPIException {
		int[] nums = makeList();
		MPI.Init(args) ;

        int source;  // Rank of sender
        int dest;    // Rank of receiver
        int tag=50;  // Tag for messages
        int next;
        int prev;
        int message[] = new int [1];

        int myrank = MPI.COMM_WORLD.getRank() ;
        int size = MPI.COMM_WORLD.getSize() ;

        int partition = nums.length/size;

        next = (myrank + 1) % size;
        prev = (myrank + size - 1) % size;

        if (0 == myrank) {
        	message[0] = 10;

        	partition = nums.length/size;//TODO
        	System.out.println("Num Processes: " + size);

            //MPI.COMM_WORLD.send(message, 1, MPI.INT, next, tag);
        }

        int[] newarray = new int[partition];
        MPI.COMM_WORLD.scatter(nums, partition, MPI.INT,newarray, partition,MPI.INT,0);
        System.out.println(newarray.length + "<<<<<<<<");
        sort(newarray);
        MPI.COMM_WORLD.gather(nums, 0, MPI.INT,newarray,0,MPI.INT,0);

        if (0 == myrank) {
            //MPI.COMM_WORLD.recv(message, 1, MPI.INT, prev, tag);

        }

        MPI.Finalize();
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

}
