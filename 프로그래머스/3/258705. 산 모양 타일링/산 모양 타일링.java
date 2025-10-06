import java.io.*;
import java.util.*;

class Solution {
    //dp같은데!
    public int solution(int n, int[] tops) {
        // n은 마름모 최대 개수
        int answer;
        long[][] d = new long[2 * n + 1][4];
        d[0][0] = 1;
        d[1][0] = 1;
        d[1][1] = 1;
        d[1][2] = 1; // 다음번 영향
        if(tops[(1 - 1)/2] == 1) d[1][3] = 1;
        for(int i = 2; i < 2 * n + 1; i++){
            d[i][0] = d[i - 1][0] + d[i - 1][1];
            d[i][0] %= 10007;
            d[i][0] += d[i - 1][3];
            d[i][0] %= 10007;
            
            d[i][1] = d[i - 1][2];
            d[i][1] %= 10007;
            
            d[i][2] = d[i - 1][0] + d[i - 1][1];
            d[i][2] %= 10007;
            d[i][2] += d[i - 1][3];
            d[i][2] %= 10007;
            
            if(i % 2 == 1 && tops[(i - 1)/2] == 1){
                d[i][3] = d[i - 1][0] + d[i - 1][1];
                d[i][3] %= 10007;
            }
        }
        
        // for(int i = 0; i < 2 * n + 1;i++){
        //     System.out.println(Arrays.toString(d[i]));
        // }
        answer = (int) d[2 * n][0] + (int) d[2 * n][1];
        answer %= 10007;
        answer += (int) d[2 * n][3];
        answer %= 10007;
        return answer;
    }
}