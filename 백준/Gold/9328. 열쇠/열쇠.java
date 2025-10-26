import java.util.*;
import java.io.*;

public class Main{
    static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
    

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder("");
        
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            char[][] a = new char[h][w];
            for(int i = 0; i < h; i++){
                String input = br.readLine();
                a[i] = input.toCharArray();
            }

            String initialKeysInput = br.readLine();
            
            int keyMask = 0;
            if(!initialKeysInput.equals("0")){
                for(int i = 0; i < initialKeysInput.length(); i++){
                    keyMask |= (1 << (initialKeysInput.charAt(i) - 'a'));
                }
            }

            int answer = 0;
            
            @SuppressWarnings("unchecked")
            List<int[]>[] blockedDoors = new ArrayList[26];
            for(int i = 0; i < 26; i++) {
                blockedDoors[i] = new ArrayList<>();
            }
            
            while(true) {
                boolean[][] visited = new boolean[h][w];
                Queue<int[]> q = new ArrayDeque<>();
                boolean keyFoundThisIteration = false;
                
                List<int[]> outLine = new ArrayList<>();
                
                for(int j = 0; j < w; j++){
                    if(a[0][j] != '*') outLine.add(new int[]{0, j});
                    if(h > 1 && a[h - 1][j] != '*') outLine.add(new int[]{h - 1, j});
                }
                for(int i = 1; i < h - 1; i++){
                    if(a[i][0] != '*') outLine.add(new int[]{i, 0});
                    if(w > 1 && a[i][w - 1] != '*') outLine.add(new int[]{i, w - 1});
                }
                
                for(int[] pos : outLine) {
                    int x = pos[0];
                    int y = pos[1];
                    
                    if (visited[x][y]) continue;

                    char cell = a[x][y];
                    
                    if (cell >= 'A' && cell <= 'Z') {
                        int doorIndex = cell - 'A';
                        if ((keyMask & (1 << doorIndex)) != 0) {
                            q.offer(new int[]{x, y});
                            visited[x][y] = true;
                        } else {
                            blockedDoors[doorIndex].add(new int[]{x, y});
                            visited[x][y] = true; 
                        }
                    } else {
                        q.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
                
                for (int i = 0; i < 26; i++) {
                    if ((keyMask & (1 << i)) != 0) {
                        for (int[] doorPos : blockedDoors[i]) {
                            int x = doorPos[0], y = doorPos[1];
                            if (!visited[x][y]) {
                                q.offer(new int[]{x, y});
                                visited[x][y] = true;
                            }
                        }
                        blockedDoors[i].clear();
                    }
                }


                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    int x = cur[0], y = cur[1];
                    char cell = a[x][y];

                    if (cell >= 'a' && cell <= 'z') {
                        int keyIndex = cell - 'a';
                        int keyBit = (1 << keyIndex);
                        
                        if ((keyMask & keyBit) == 0) {
                            keyMask |= keyBit;
                            keyFoundThisIteration = true;
                        }
                        a[x][y] = '.';
                        
                    } else if (cell == '$') {
                        answer++;
                        a[x][y] = '.';
                    }
                    
                    for(int i = 0; i < 4; i++){
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        if(nx >= 0 && nx < h && ny >= 0 && ny < w && !visited[nx][ny]){
                            char nextCell = a[nx][ny];

                            if(nextCell == '*') continue;
                            
                            if(nextCell >= 'A' && nextCell <= 'Z'){
                                int doorIndex = nextCell - 'A';
                                if((keyMask & (1 << doorIndex)) != 0){
                                    visited[nx][ny] = true;
                                    q.offer(new int[]{nx, ny});
                                } else {
                                    blockedDoors[doorIndex].add(new int[]{nx, ny});
                                    visited[nx][ny] = true;
                                }
                            } else {
                                visited[nx][ny] = true;
                                q.offer(new int[]{nx, ny});
                            }
                        }
                    }
                }

                if (!keyFoundThisIteration) {
                    break;
                }
            }

            sb.append(answer).append("\n");
        }
        
        System.out.print(sb);
    }
}
