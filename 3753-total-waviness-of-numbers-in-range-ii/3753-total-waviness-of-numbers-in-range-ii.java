class Solution {

    private String s;
    private long[][][][][] memoCnt;
    private long[][][][][] memoSum;
    private boolean[][][][][] seen;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long x) {
        if (x < 0) return 0;

        s = String.valueOf(x);
        int n = s.length();

        memoCnt = new long[n][11][11][2][2];
        memoSum = new long[n][11][11][2][2];
        seen = new boolean[n][11][11][2][2];

        Result res = dfs(0, 10, 10, 0, 1);
        return res.sum;
    }

    private Result dfs(int pos,
                       int prev2,
                       int prev1,
                       int started,
                       int tight) {

        if (pos == s.length()) {
            return new Result(1, 0);
        }

        if (seen[pos][prev2][prev1][started][tight]) {
            return new Result(
                memoCnt[pos][prev2][prev1][started][tight],
                memoSum[pos][prev2][prev1][started][tight]
            );
        }

        long count = 0;
        long sum = 0;

        int limit = tight == 1
                ? s.charAt(pos) - '0'
                : 9;

        for (int d = 0; d <= limit; d++) {

            int ntight =
                (tight == 1 && d == limit) ? 1 : 0;

            if (started == 0 && d == 0) {

                Result child =
                    dfs(pos + 1, 10, 10, 0, ntight);

                count += child.count;
                sum += child.sum;

            } else {

                int contribution = 0;

                if (started == 1 && prev2 != 10) {

                    boolean peak =
                        prev1 > prev2 &&
                        prev1 > d;

                    boolean valley =
                        prev1 < prev2 &&
                        prev1 < d;

                    if (peak || valley)
                        contribution = 1;
                }

                int nprev2 =
                    (started == 0) ? 10 : prev1;

                int nprev1 = d;

                Result child =
                    dfs(pos + 1,
                        nprev2,
                        nprev1,
                        1,
                        ntight);

                count += child.count;

                sum += child.sum
                        + child.count * contribution;
            }
        }

        seen[pos][prev2][prev1][started][tight] = true;

        memoCnt[pos][prev2][prev1][started][tight] = count;
        memoSum[pos][prev2][prev1][started][tight] = sum;

        return new Result(count, sum);
    }

    static class Result {
        long count;
        long sum;

        Result(long c, long s) {
            count = c;
            sum = s;
        }
    }
}