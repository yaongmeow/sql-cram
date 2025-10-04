import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] g = new int[n + 1][n + 1]; for(int i = 0; i <= n; i++) Arrays.fill(g[i], 0x3f3f3f3f);
        //for(int i = 1; i <= n; i++) g[i][i] = 0;
        int[][] nxt = new int[n + 1][n + 1];
        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (g[from][to] > cost){
                g[from][to] = cost;
                nxt[from][to] = to;
            }
        }

        for(int via = 1; via <= n; via++){
            for(int from = 1; from <= n; from++){
                if(from == via) continue;
                for(int to = 1; to <= n; to++){
                    if(to == via || from == to) continue;
                    if(g[from][via] + g[via][to] < g[from][to]){
                        g[from][to] = g[from][via] + g[via][to];
                        nxt[from][to] = nxt[from][via];
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(g[i][j] == 0x3f3f3f3f){
                    System.out.print("0 ");
                    continue;
                }
                System.out.print(g[i][j] + " ");
            }
            System.out.println(" ");
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(g[i][j] == 0x3f3f3f3f){
                    System.out.println("0");
                    continue;
                }
                StringBuilder sb = new StringBuilder(Integer.toString(i));
                int now = i, cnt = 1;
                while(now != j){
                    now = nxt[now][j];
                    sb.append(" " + now); cnt++;
                }
                System.out.println(cnt + " " + sb.toString());
            }
        }
    }
}