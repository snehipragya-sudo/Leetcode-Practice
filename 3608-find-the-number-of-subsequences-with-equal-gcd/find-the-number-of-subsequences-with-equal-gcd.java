class Solution {
    static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int m = 0;
        for (int num : nums) {
            m = Math.max(m, num);
        }

        int[][] dp = new int[m + 1][m + 1];
        dp[0][0] = 1;

        for (int num : nums) {
            int[][] ndp = new int[m + 1][m + 1];

            for (int j = 0; j <= m; j++) {
                int divisor1 = gcd(j, num);

                for (int k = 0; k <= m; k++) {
                    int val = dp[j][k];
                    if (val == 0) continue;

                    int divisor2 = gcd(k, num);

                    // Skip the current number
                    ndp[j][k] = (int) ((ndp[j][k] + (long) val) % MOD);

                    // Add to first subsequence
                    ndp[divisor1][k] = (int) ((ndp[divisor1][k] + (long) val) % MOD);

                    // Add to second subsequence
                    ndp[j][divisor2] = (int) ((ndp[j][divisor2] + (long) val) % MOD);
                }
            }

            dp = ndp;
        }

        int ans = 0;
        for (int j = 1; j <= m; j++) {
            ans = (int) ((ans + (long) dp[j][j]) % MOD);
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}