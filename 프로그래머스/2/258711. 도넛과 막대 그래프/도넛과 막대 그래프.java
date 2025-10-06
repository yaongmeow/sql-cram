import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        List<Integer>[] g = new List[1000000];
        int[] in = new int[1000000];
        int[] out = new int[1000000];
        int maxV = 0;
        for(int i = 0; i < edges.length; i++){
            int from = edges[i][0] - 1;
            int to = edges[i][1] - 1;
            if(g[from] == null) g[from] = new ArrayList<>();
            g[from].add(to); in[to]++; out[from]++;
            if(from > maxV) maxV = from;
            if(to > maxV) maxV = to;
        }
        
        int newV = -1;
        int[] answer = new int[4];
        for(int i = 0; i <= maxV; i++){
            if(in[i] != 0 || out[i] <= 1) continue;
            if(newV == -1 || out[i] > out[newV]) newV = i;
        }
        
        answer[0] = newV + 1;
        for(Integer idx: g[newV]){
            in[idx]--;
            if(in[idx] == 0 && out[idx] == 0) answer[2]++;
        }
        
        boolean[] vis = new boolean[maxV + 1];
        for(int i = 0; i <= maxV; i++){
            if(vis[i] || i == newV || out[i] == 0) continue;
            if(in[i] == 0 && out[i] == 1){
                // 막대형
                int now = i;
                while(out[now] != 0){
                    vis[now] = true;
                    now = g[now].get(0);
                }
                answer[2]++;
                continue;
            }
            if(out[i] == 2 && in[i] == 2){
                // 8자형의 관절
                vis[i] = true;
                
                int now = i;
                while(!vis[now]){
                    vis[now] = true;
                    now = g[now].get(0);
                }
                
                now = g[now].get(1);
                while(!vis[now]){
                    vis[now] = true;
                    now = g[now].get(0);
                }
                answer[3]++;
            }
        }
        //System.out.println(out[newV] + " " + answer[2] + " " + answer[3]);
        
        answer[1] = out[newV] - answer[2] - answer[3]; 
        
        // for(int i = 0; i <= maxV; i++){
        //     if(vis[i] || i == newV) continue;
        //     if(in[i] == 1 && out[i] == 1){
        //         int now = i;
        //         while(!vis[now]){
        //             vis[now] = true;
        //             now = g[now].get(0);
        //         }
        //         answer[1]++;
        //     }
        // }
        
        return answer;
    }
}