import java.util.*;
import java.io.*;

public class Main {
    static int bt(int k, int n){
        int answer = 0;
        if(k == 0){
            return n;
        }
        for(int i = 1; i <= n; i++){
            answer += bt(k - 1, i);
        }
        return answer;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for(int t = 1; t <= tc; t++){
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            System.out.println(bt(k, n));
        } 
    }
}