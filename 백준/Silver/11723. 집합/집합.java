import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        Set<Integer> s = new HashSet<>();
        StringBuilder sb = new StringBuilder("");
        for(int t = 0; t < m; t++){
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            String cmd = st.nextToken();
            if(cmd.equals("all")){
                s.clear();
                s.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
                continue;
            }
            if(cmd.equals("empty")){
                s.clear();
                continue;
            }
            int value = Integer.parseInt(st.nextToken());
            if(cmd.equals("add")){
                s.add(value);
            } else if(cmd.equals("remove")){
                s.remove(value);
            } else if(cmd.equals("check")){
                if(s.contains(value))sb.append(1 + "\n");
                else sb.append(0 + "\n");
            } else if(cmd.equals("toggle")){
                if(s.contains(value)) s.remove(value);
                else s.add(value);
            }
        }
        System.out.print(sb.toString());
    }
}