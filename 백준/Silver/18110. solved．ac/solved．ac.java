import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if(n == 0){
            System.out.println(0);
            return;
        }

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        int r = (int) Math.round(n * 0.15);
        int sum = 0;
        for(int i = r; i < n - r; i++) sum += arr[i];
        System.out.println(Math.round(sum / (double)(n - 2 * r)));
    }
}
