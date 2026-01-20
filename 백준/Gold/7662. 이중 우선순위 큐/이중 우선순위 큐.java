import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc <= T; tc++){
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st;
            int size = 0;
            PriorityQueue<Integer> min = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
            PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> -Integer.compare(a, b));
            HashMap<Integer, Integer> hm = new HashMap<>();

            for(int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                if(cmd.equals("I")){
                    max.offer(num);
                    min.offer(num);
                    if(hm.containsKey(num)){
                        hm.put(num, hm.get(num) + 1);
                    } else hm.put(num, 1);
                    size++;
                    continue;
                }
                if(size == 0) {
                    max.clear();
                    min.clear();
                    hm.clear();
                    continue;
                }
                if(num == -1) {
                    int tmp = min.peek();
                    while(hm.get(tmp) == 0){
                        min.poll();
                        tmp = min.peek();
                    }
                    min.poll();
                    hm.put(tmp, hm.get(tmp) - 1);
                }
                else {
                    int tmp = max.peek();
                    while(hm.get(tmp) == 0){
                        max.poll();
                        tmp = max.peek();
                    }
                    max.poll();
                    hm.put(tmp, hm.get(tmp) - 1);
                }
                size--;
            }
            if(size == 0){
                sb.append("EMPTY\n");
            }
            else{
                int a = max.peek();
                int b = min.peek();
                while(hm.get(a) == 0){
                    max.poll();
                    a = max.peek();
                }

                while(hm.get(b) == 0){
                    min.poll();
                    b = min.peek();
                }
                sb.append(max.poll() + " " + min.poll() + "\n");
            }
        }

        System.out.print(sb.toString());
    }
}