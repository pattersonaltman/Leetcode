public class TrappingRainWater {

	public static void main(String[] args) {


		//Leetcode debugging tests
		
		int[] height1 = {0,1,0,2,1,0,1,3,2,1,2,1};
		int[] height2 = {4,2,0,3,2,5};
		int[] height3 = {5,2,1,2,1,5};
		int[] height4 = {6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3};
		int[] height5 = {5,4,1,2};
		
		
		System.out.println(trap(height1));
		System.out.println(trap(height2));
		System.out.println(trap(height3));
		System.out.println(trap(height4));
		System.out.println(trap(height5));
		

	}

	
	
	
    public static int trap(int[] height) {
        
    	int rain = 0;
    	int left;					//left pointer
    	int right;					//right pointer
    	int max;					//max = floor(left-wall, right-wall)
    	int rightWallIndex = 0;
    	
    	for(int i = 0; i < height.length; i++)
		{
			left = i;
			right = i+1;
			
			if(right == height.length) {return rain;}
			
			if(height[right] < height[left])					//Height drop, potential water collection
			{
				int j = i + 2;							//j: pointer for locating the right-wall index (has to be +2 ahead of left-wall (left))
				
				if(j > height.length - 1) {return rain;}
				
				while(height[j] <= height[j-1])					//iterate forward until height rise, needed for water collection
				{								//After while loop: j = index of first point of height rise
					j++;
					if(j == height.length) {return rain;}
				}
				
				max = 0;
				
				while(j < height.length)					//Find the correct right-wall index, and set max to it, by finding either:
				{								//	- The 1st height >= the left wall
					if(height[j] >= height[left])				//	- or else, the highest height that is < the left wall
					{
						max = height[left];
						rightWallIndex = j;
						break;
					}
					
					if(height[j] > max)
					{
						max = height[j];
						rightWallIndex = j;
					}

					j++;
				}
																
				if(height[left] > max)							// **ONLY HAPPENS IF: The left wall is higher (>) than the right wall
				{									//Re-evaluate the original left wall according to the max
					while(height[left] >= max)					//and shift over left as needed
					{								//Left wall needs to be:
						left++;							//	1 index before the 1st index < max (1st index of water collection)
					}
					left--;
				}
				
				for(int k = left + 1; k < rightWallIndex; k++)			//Within rain-collection indices:
				{								//Rain = floor(left wall, right wall) - current height
					rain += max - height[k];
				}
				
				i = rightWallIndex - 1;						//Skip ahead and continue from the right wall
			}
		}

    	return rain;
    }
	
	
    
}
