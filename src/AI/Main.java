package AI;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
         try {
             FileReader reader = new FileReader("maze.txt");
             BufferedReader br = new BufferedReader(reader);

             String str=null;
             ArrayList<String[]> s=new ArrayList<>();
             while((str=br.readLine())!=null){
                 s.add(str.split(" "));
             }

             int[][] maze=new int[s.size()][s.get(0).length];
             for(int i=0;i<s.size();i++){
                 for(int j=0;j<s.get(0).length;j++){
                     maze[i][j]=Integer.parseInt(s.get(i)[j]);
                 }

             }
         Scanner scan = new Scanner(System.in);
         boolean flag=true;
         String res="";
             String startPoint="";
             String endPoint="";

         while(flag){
             System.out.println("Please input the start point:");
             startPoint=scan.nextLine();
             System.out.println("Please input the end point:");
             endPoint =scan.nextLine();

             res = bfs(maze, startPoint, endPoint);
             System.out.println("The answer is "+res);

             System.out.println("continue or end?");
             String f=scan.nextLine();
             if(f.equals("end"))
                 flag=false;

         }

         br.close();
         reader.close();

         } catch(FileNotFoundException e){
             e.printStackTrace();
         } catch (IOException e){
             e.printStackTrace();
         }

    }

    public static String bfs(int[][] maze, String startPoint, String endPoint){
        String res="NO";
        String[] startS=startPoint.substring(1,startPoint.length()-1).split(",");
        String[] endS=endPoint.substring(1,endPoint.length()-1).split(",");
        int[] start=new int[]{Integer.parseInt(startS[0]), Integer.parseInt(startS[1])};
        int[] end=new int[]{Integer.parseInt(endS[0]), Integer.parseInt(endS[1])};

        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] four_directions={{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        visited[start[0]][start[1]] = true;
        while (!q.isEmpty()) {
            int[] s = q.remove();
            if (s[0] == end[0] && s[1] == end[1])
                return "YES";
            for (int[] one_direction: four_directions) {
                int x = s[0] + one_direction[0];
                int y = s[1] + one_direction[1];
                if (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0 && !visited[x][y]) {
                    q.add(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }

        return res;
    }
}
