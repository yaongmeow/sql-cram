import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int tmp = Integer.parseInt(br.readLine());
        int th = tmp / 3600;
        tmp %= 3600;
        int tm = tmp / 60;
        tmp %= 60;
        int ts = tmp;
        s += ts;
        if(s >= 60){
            m += s / 60;
            s %= 60;
        }
        m += tm;
        if(m >= 60){
            h += m / 60;
            m %= 60;
        }
        h += th;
        if(h >= 24) h %= 24;
        System.out.println(h + " " + m + " " + s);
    }
}