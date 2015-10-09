import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import mpi.MPIException;


public class Main {

	private static int max = 10;
	private static int min = 0;
	private static int size = max;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//make random list or nums
		int[] numsSel = makeList();
		int[] numsBub = new int[size];
		int[] numsShe = new int[size];
		int[] numsSelPar = new int[size];

		//copy array for different sorts
		System.arraycopy(numsSel, 0, numsBub, 0, numsSel.length);
		System.arraycopy(numsSel, 0, numsShe, 0, numsSel.length);
		System.arraycopy(numsSel, 0, numsSelPar, 0, numsSel.length);

		//Selection Sort
		selectionSort(numsSel);

		//Bubble Sort
		bubbleSort(numsBub);

		//Shell Sort
		shellSort(numsShe);

		//Selection Sort Parallel
		selectionSortPar(args,numsSelPar);
	}

	private static void selectionSortPar(String[] args, int[] numsSelPar) {
		SelectionSortParallel s = new SelectionSortParallel();
		try {
			s.main(args,numsSelPar);
		} catch (MPIException e) {
			e.printStackTrace();
		}
		StopWatch time = new StopWatch();
		time.start();
		s.sort(numsSelPar);
		time.stop();
		System.out.println("Selection Sort (Parallel) Time: " + time.getElapsedTime() + "ms");

	}

	private static void shellSort(int[] numsShe) {
		ShellSort s = new ShellSort();
		StopWatch time = new StopWatch();
		time.start();
		s.sort(numsShe);
		time.stop();
		System.out.println("Shell Sort (Serial) Time: " + time.getElapsedTime() + "ms");

	}

	private static void bubbleSort(int[] numsBub) {
		BubbleSort b = new BubbleSort();
		StopWatch time = new StopWatch();
		time.start();
		b.sort(numsBub);
		time.stop();
		System.out.println("Bubble Sort (Serial) Time: " + time.getElapsedTime() + "ms");
	}

	private static void selectionSort(int[] numsSel) {
		SelectionSort s = new SelectionSort();
		StopWatch time = new StopWatch();
		time.start();
		s.sort(numsSel);
		time.stop();
		System.out.println("Selection Sort (Serial) Time: " + time.getElapsedTime() + "ms");
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

	//prints out array
	public static void printnums(int[] nums){
		for(int i = 0; i < nums.length; i++){
			System.out.print(nums[i] + ",");
		}
		System.out.println();
	}

}
