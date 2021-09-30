import java.util.Arrays;

public class Solution {
    public int assignBikes(int[][] workers, int[][] bikes) {
        int[][] f = new int[bikes.length][1 << workers.length];
        for (int[] row : f) {
            Arrays.fill(row, -1);
        }
        
        return dfs(0, 0, workers, bikes, f);
    }
    
    private int dfs(int pos, int state, int[][] w, int[][] b, int[][] f) {
    	// 车不够分了
        if (b.length - pos < w.length - Integer.bitCount(state)) {
            return 0x3f3f3f3f;
        }
        
        // 每个人都有车了
        if (state == (1 << w.length) - 1) {
            return 0;
        }
        
        // 有人没车，但车已经用完了
        if (pos == b.length) {
            return 0x3f3f3f3f;
        }
        
        // 有记忆则调取记忆
        if (f[pos][state] != -1) {
            return f[pos][state];
        }
        
        int res = 0x3f3f3f3f;
        // 枚举pos这个车分给谁
        for (int i = 0; i < w.length; i++) {
            if ((state >> i & 1) == 0) {
                res = Math.min(res, dis(w, b, i, pos) + dfs(pos + 1, state | (1 << i), w, b, f));
            }
        }
        
        // 枚举pos这个车浪费掉的情况
        res = Math.min(res, dfs(pos + 1, state, w, b, f));
        // 回溯前做记忆
        f[pos][state] = res;
        return res;
    }
    
    private int dis(int[][] w, int[][] b, int i, int j) {
        return Math.abs(w[i][0] - b[j][0]) + Math.abs(w[i][1] - b[j][1]);
    }
}
