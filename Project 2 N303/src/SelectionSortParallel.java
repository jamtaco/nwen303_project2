import mpi.MPI;
import mpi.MPIException;
import mpi.Status;


public class SelectionSortParallel {

	public static void main(String[] args, int[] nums) throws MPIException {
		MPI.Init(args) ;

        int source;  // Rank of sender
        int dest;    // Rank of receiver
        int tag=50;  // Tag for messages
        int next;
        int prev;
        int message[] = new int [1];

        int partition;

        int myrank = MPI.COMM_WORLD.getRank() ;
        int size = MPI.COMM_WORLD.getSize() ;

        next = (myrank + 1) % size;
        prev = (myrank + size - 1) % size;

        if (0 == myrank) {
        	message[0] = 10;

        	partition = nums.length/size;


            MPI.COMM_WORLD.send(message, 1, MPI.INT, next, tag);
        }

        while (true) {
            MPI.COMM_WORLD.recv(message, 1, MPI.INT, prev, tag);

            if (0 == myrank) {
                --message[0];
                System.out.println("Process 0 decremented value: " + message[0]);
            }

            MPI.COMM_WORLD.send(message, 1, MPI.INT, next, tag);
            if (0 == message[0]) {
                System.out.println("Process " + myrank + " exiting");
                break;
            }
        }

        if (0 == myrank) {
            MPI.COMM_WORLD.recv(message, 1, MPI.INT, prev, tag);
        }

        MPI.Finalize();
    }


	public static void sort(int[] nums){

	}

}
