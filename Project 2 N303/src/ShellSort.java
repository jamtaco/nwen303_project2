
public class ShellSort {


	public ShellSort(){

	}

	public <T extends Comparable<? super T>> int[]  sort(int[] nums) {
		//loop over gaps, decreasing size
		for(int gap = nums.length/2; gap > 0; gap /=2){
			//insertion sort for gap size
			for(int i = gap; i < nums.length; i++){
				int temp = nums[i];
				int j;
				for(j = i; j >= gap && larger(nums[j-gap],temp); j -= gap){
					nums[j] = nums[j-gap];
				}
				nums[j] = temp;
			}
		}
		return nums;
	}

	private boolean larger(int a, int b){
		if(a > b)return true;
		return false;
	}
}
