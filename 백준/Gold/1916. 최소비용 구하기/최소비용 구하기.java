import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int v = Integer.parseInt(br.readLine());
        int e = Integer.parseInt(br.readLine());
        List<int[]>[] g = new List[v]; for(int i = 0; i < v; i++) g[i] = new ArrayList<>();
        int[] djk = new int[v]; Arrays.fill(djk, Integer.MAX_VALUE);
        int[] pre = new int[v]; Arrays.fill(pre, Integer.MAX_VALUE);
        StringTokenizer st;
        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            g[f].add(new int[]{t, w});
        }

        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[]{from, 0}); djk[from] = 0; pre[from] = from;
        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int vertex = now[0];
            int weight = now[1];
            if(djk[vertex] != weight) continue;
            for(int[] to: g[vertex]){
                if(djk[to[0]] > djk[vertex] + to[1]){
                    djk[to[0]] = djk[vertex] + to[1];
                    pre[to[0]] = vertex;
                    pq.offer(new int[]{to[0], djk[vertex] + to[1]});
                }
            }
        }

        System.out.println(djk[end]);
        // int cnt = 1;
        // StringBuilder sb = new StringBuilder("");
        // int now = end; sb.insert(0, Integer.toString(now + 1) + " ");
        // while(now != from){
        //     now = pre[now];
        //     sb.insert(0, Integer.toString(now + 1) + " ");
        //     cnt++;
        // }
        // System.out.println(cnt);
        // System.out.println(sb.toString());
    }
}