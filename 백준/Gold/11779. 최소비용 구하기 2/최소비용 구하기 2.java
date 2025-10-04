import java.util.*;
import java.io.*;

class Pair{
    public int v;
    public int price;
    public Pair(int v, int price){
        this.v = v;
        this.price = price;
    }
}

public class Main {
    static int n, m, cnt;
    static List<Pair>[] g;
    static int[] dist, trace;

    static String getLog(int now, int st){
        cnt++;
        if(now == st){
            return Integer.toString(now);
        }
        return getLog(trace[now], st) + " " + now;
    }
    static public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); m = Integer.parseInt(br.readLine());
        g = new List[n + 1]; for(int i = 1; i <= n; i++) g[i] = new ArrayList<>();
        dist = new int[n + 1]; Arrays.fill(dist, Integer.MAX_VALUE);
        trace = new int[n + 1];
        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            g[from].add(new Pair(to, price));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.price, o2.price));
        dist[start] = 0; trace[start] = 0;
        pq.offer(new Pair(start, 0));
        while(!pq.isEmpty()){
            Pair now = pq.poll();
            if(dist[now.v] != now.price) continue;
            List<Pair> injup = g[now.v];
            for(Pair p : injup){
                int tempDist = p.price + dist[now.v];
                if(tempDist < dist[p.v]){
                    trace[p.v] = now.v;
                    dist[p.v] = tempDist;
                    pq.offer(new Pair(p.v, tempDist));
                }
            }
        }
        cnt = 0;
        String log = getLog(end, start);
        System.out.println(dist[end]);
        System.out.println(cnt);
        System.out.println(log);
        
    }
}