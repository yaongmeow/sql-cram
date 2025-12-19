import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();
        long answer = 0;
        for(int i = 0; i < n; i++){
            answer += (long) (input.charAt(i) - 'a' + 1) * Math.pow(31, i);
            answer %= 1234567891;
        }
        System.out.println(answer);   
    }
}