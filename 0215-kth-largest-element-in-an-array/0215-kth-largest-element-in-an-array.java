class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pd=new PriorityQueue<>();
        for(int n:nums)
        {
            pd.offer(n);
            if(pd.size()>k)
            {
                pd.poll();
            }
        }
        return pd.peek();
        
    }
}