
public class SelectionSort {

	/**
	 * @param args
	 */

	public SelectionSort(){
	}

	public int[]  sort(int[] nums) {
		//iterate list
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
		return nums;
	}
}
