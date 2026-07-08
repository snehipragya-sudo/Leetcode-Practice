import java.util.*;

class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int MOD = 1_000_000_007;
        int n = s.length();

        int[] cnt = new int[n + 1];
        ArrayList<Integer> digits = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            cnt[i + 1] = cnt[i];
            int d = s.charAt(i) - '0';
            if (d != 0) {
                cnt[i + 1]++;
                digits.add(d);
            }
        }

        int m = digits.size();
        long[] pow = new long[m + 1];
        long[] val = new long[m + 1];
        int[] sum = new int[m + 1];

        pow[0] = 1;
        for (int i = 0; i < m; i++) {
            pow[i + 1] = pow[i] * 10 % MOD;
            val[i + 1] = (val[i] * 10 + digits.get(i)) % MOD;
            sum[i + 1] = sum[i] + digits.get(i);
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = cnt[queries[i][0]];
            int r = cnt[queries[i][1] + 1];

            if (l == r) {
                ans[i] = 0;
                continue;
            }

            long x = (val[r] - val[l] * pow[r - l] % MOD + MOD) % MOD;
            long digitSum = sum[r] - sum[l];
            ans[i] = (int) (x * digitSum % MOD);
        }

        return ans;
    }
}