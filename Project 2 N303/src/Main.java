import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] numsSel = makeList();
		SelectionSort s = new SelectionSort();
		s.sort(numsSel);
		s.printnums(numsSel);
	}
	
	public static int[] makeList(){
		int max = 100;
		int min = 1;
		int size = 100;
		
		int [] nums = new int[size];
		Random ran = new Random();
		
		for(int i = 0; i < nums.length; i++){
			nums[i] = ran.nextInt((max - min) + 1) + min;
		}
		return nums;
	}

}
