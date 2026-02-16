import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
    static char[][] b;
    static int R, C, answer;
    static boolean[] v = new boolean[26];

    static void bt(int x, int y, int cnt){
        if(cnt > answer) answer = cnt;
        for(int i = 0; i < 4; i++){
            int nx  = x + dx[i], ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= R || ny >= C || v[b[nx][ny] - 'A']) continue;
            v[b[nx][ny] - 'A'] = true;
            bt(nx, ny, cnt + 1);
            v[b[nx][ny] - 'A'] = false;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        b = new char[R][C];

        Arrays.fill(v, false);

        for(int i = 0; i < R; i++){
            b[i] = br.readLine().toCharArray();
        }

        answer = 0;
        v[b[0][0] - 'A'] = true;
        bt(0, 0, 1);
        
        System.out.print(answer);
    }
}