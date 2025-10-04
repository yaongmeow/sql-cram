import java.util.*;
import java.io.*;

public class Main {
    static int V, E, K;
    static public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        List<int[]>[] g = new List[V + 1]; for(int i = 1; i <= V; i++) g[i] = new ArrayList<>();
        int[] dist = new int[V + 1]; Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] vis = new boolean[V + 1];
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g[u].add(new int[]{v, w});
        }
        dist[K] = 0; int cnt = 0; //int now = K;
        PriorityQueue<int[]> pq  = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        pq.offer(new int[]{K, 0});
        while(!pq.isEmpty()){
            int[] now = pq.poll();
            if(dist[now[0]] != now[1]) continue;
            List<int[]> injup = g[now[0]];
            for(int i = 0; i < injup.size(); i++){
                int vNum = injup.get(i)[0];
                int weight = injup.get(i)[1];
                int tempDist = weight + dist[now[0]];
                if(tempDist < dist[vNum]) {
                    dist[vNum] = tempDist;
                    pq.offer(new int[]{vNum, tempDist});
                }
            }
        }
        // while(cnt < V){
        //     int min = Integer.MAX_VALUE; int minVertex = -1;
        //     List<int[]> injup = g[now];
        //     for(int i = 0; i < injup.size(); i++){
        //         int vNum = injup.get(i)[0];
        //         int weight = injup.get(i)[1];
        //         int tempDist = weight + dist[now];
        //         if(tempDist < dist[vNum]) dist[vNum] = tempDist;
        //     }

        //     for(int i = 1; i <= V; i++){
        //         if(!vis[i] && dist[i] < min){ 
        //             min = dist[i];
        //             minVertex = i;
        //         }
        //     }
        //     cnt++;
        //     if(minVertex == -1) break;
        //     now = minVertex;
        //     vis[minVertex] = true;
        // }

        for(int i = 1; i <= V; i++){
            if(dist[i] == Integer.MAX_VALUE){
                System.out.println("INF");
            }else System.out.println(dist[i]);
        }
    }
}