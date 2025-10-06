import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        HashMap<String, Integer> idx = new HashMap<>();
        int fn = friends.length;
        int gn = gifts.length;
        
        for(int i = 0; i < fn; i++) idx.put(friends[i], i);
    
        int[] rcv = new int[fn];
        int[] gv = new int[fn];
        int[][] a = new int[fn][fn];
        
        for(int i = 0; i < gn; i++){
            StringTokenizer st = new StringTokenizer(gifts[i]);
            int from = idx.get(st.nextToken());
            int to = idx.get(st.nextToken());
            a[from][to]++; gv[from]++; rcv[to]++;
        }
        
        int[] res = new int[fn];
        
        for(int i = 0; i < fn; i++){
            for(int j = 0; j < fn; j++){
                if(i >= j) continue;
                if(a[i][j] == a[j][i]) {
                    int is = gv[i] - rcv[i];
                    int js = gv[j] - rcv[j];
                    if(js > is) {
                        res[j]++;
                    } else if(is > js) {
                        res[i]++;
                    }
                }
                else if(a[i][j] > a[j][i]){
                    res[i]++;
                } else {
                    res[j]++;
                }
            }
        }
        
        int answer = 0;
        for(int i = 0; i < fn; i++){
            if(res[i] > answer) answer = res[i];
        }
        return answer;
    }
}