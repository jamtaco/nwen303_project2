
public class BubbleSort {

	/**
	 * @param args
	 */
	public BubbleSort(){
	}
	
	public int[]  sort(int[] nums) {
		//iterate list
		boolean swap = true;
		//last one is always sorted, so iteration gets shorter
		for(int i = nums.length -1; i > 0 && swap; i--){
			swap = false;
			for(int j = 0; j < i; j++){
				if(nums[j]> nums[j+1]){
					int temp = nums[j];
					nums[j] = nums [j+1];
					nums[j+1] = temp;
					swap = true;
				}
			}
		}
		
		return nums;
	}
	
	
	public void printnums(int[] nums){
		for(int i = 0; i < nums.length; i++){
			System.out.print(nums[i] + ",");
		}
		System.out.println();
	}

}
