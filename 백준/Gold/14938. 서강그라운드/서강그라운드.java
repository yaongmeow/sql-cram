import java.util.*;
import java.io.*;

public class Main{
    static int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[] item = new int[n]; int[][] g = new int[n][n]; for(int i = 0; i < n; i++) Arrays.fill(g[i], INF);
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int len = Integer.parseInt(st.nextToken());
            g[a][b] = Integer.min(g[a][b], len);
            g[b][a] = Integer.min(g[b][a], len);
        }

        for(int i = 0; i < n; i++) g[i][i] = 0;
        for(int v = 0; v < n; v++){
            for(int f = 0; f < n; f++){
                //if(v == f) continue;
                for(int t = 0; t < n; t++){
                    //if(t == f || t == v) continue;
                    g[f][t] = Integer.min(g[f][v] + g[v][t], g[f][t]);
                }
            }
        }

        int answer = Integer.MIN_VALUE;

        for(int f = 0; f < n; f++){
            int cnt = 0;
            for(int t = 0; t < n; t++){
                if(g[f][t] <= m){
                    cnt += item[t];
                }
            }
            answer = Integer.max(answer, cnt);
        }

        System.out.println(answer);
        
    }
}