class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        int h = n / 2;
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Math.abs((long) nums[i]);
        }
        Arrays.sort(arr);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (i < h ? -1L : 1L) * arr[i] * arr[i];
        }
        return ans;
    }
}