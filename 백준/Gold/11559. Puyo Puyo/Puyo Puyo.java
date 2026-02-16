import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
    static char[][] b;
    static int R = 12, C = 6;
    static boolean[][] v = new boolean[12][6];

    static void clearV(){
        for(int i = 0; i < R; i++) Arrays.fill(v[i], false);
    }

    static boolean ppuyo(){
        clearV();
        boolean boom = false;
        for(int i = R - 1; i >= 0; i--){
            for(int j = 0; j < C; j++){
                if(v[i][j] || b[i][j] == '.') continue;
                Queue<int[]> q = new ArrayDeque<>();
                List<int[]> list = new ArrayList<>();

                v[i][j] = true;
                
                list.add(new int[]{i, j});
                q.offer(new int[]{i, j, b[i][j]});
                int answer = 1;

                while(!q.isEmpty()){
                    int[] now = q.poll();
                    int x = now[0], y = now[1];
                    char c = (char) now[2];

                    for(int k = 0; k < 4; k++){
                        int nx = x + dx[k], ny = y + dy[k];
                        if(nx < 0 || ny < 0 || nx >= R || ny >= C || v[nx][ny] || b[nx][ny] != c) continue;
                        answer++;
                        v[nx][ny] = true;
                        list.add(new int[]{nx, ny});
                        q.offer(new int[]{nx, ny, c});
                    }
                }

                if(answer >= 4){
                    for(int[] p : list){
                        b[p[0]][p[1]] = '.';
                    }
                    boom = true;
                }
            }
        }
        return boom;
    }

    static void gravity(){
        for(int i = 0; i < C; i++){
            Queue<Character> q = new ArrayDeque<>();
            int down = -1;
            for(int j = R - 1; j >= 0; j--){
                if(down == -1 && b[j][i] == '.') down = j;
                if(down >= 0 && b[j][i] != '.') q.offer(b[j][i]);
            }

            if(down >= 0 && q.size() > 0){
                while(!q.isEmpty()){
                    char now = q.poll();
                    b[down--][i] = now;
                }
                for(int j = down; j >= 0; j--){
                    b[j][i] = '.';
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        b = new char[R][C];
        
        for(int i = 0; i < R; i++){
            b[i] = br.readLine().toCharArray();
        }

        int answer = 0;
        while(ppuyo()){
            answer++;
            gravity();
        }
        System.out.print(answer);
    }
}