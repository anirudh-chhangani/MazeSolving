/* Maze Solving algorithm.
 * 
 * Code Log
 * 
 * 17th May 2013.
 * Status : Incomplete
 * BFS in Incomplete
 * Tree Implementation is not working as required 
 * DFS is Complete.
 * 
 * 18th May 2013. 9:00AM
 * Status : Complete
 * BFS : complete
 * Tree construction is complete
 * DFS : Complete
 * Print function not working
 * 
 * 12:20Am * 
 * Print function Started Working.
 * Hence Complete;
 * 
 * 2:30 PM Revised the code for any general Matrix
 * 
 * Build By : Anirudh Chhangani. 
 */

import java.util.HashSet; // to remmember the nodes that are in the tree.
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JOptionPane;

class node{
    node links[] = new node[4];
    int val=0;
    boolean value;
    String loc;
    int visit=0;
}

class maze{
   static int maze[][]= {                     // 0 : Free Nodes ; 1 : Wall
                {0,0,1,1,1,0,0,0},
                {0,1,1,1,1,0,1,0},
                {0,0,0,1,0,0,0,0},
                {1,1,0,0,0,1,1,1},
                {1,0,0,1,0,1,1,1},
                {1,1,0,0,0,0,1,1},
                {0,0,0,1,1,0,0,0},
                {0,1,1,1,1,0,1,0},
                {0,0,0,1,0,0,0,0},
                {1,1,0,0,0,1,1,1},
                
            };
        
        public static void main(String [] args){
            
            
            String dec = JOptionPane.showInputDialog("Enter the End location"); // Destination Address
            char err = dec.charAt(0);
            char err2 = dec.charAt(1);
            int y1 = (int)(err-'0');
            int u =(int)(err2-'0');
            
            if(y1>=0&&u>=0&&maze[y1][u]!=0){
                System.out.println("No path Exist");
            }
            // select start point x and y
            String strt = JOptionPane.showInputDialog("Enter the Start location");
            //String strt = "54";
            char err3 = strt.charAt(0);
            char err4 = strt.charAt(1);
            int x = (int)(err3-'0');
            int y = (int)(err4-'0');
            
            
            HashSet set = new HashSet();
            //HashSet set2 = new HashSet();
            Queue<node> que = new LinkedList<node>();
            Queue<node> que2 = new LinkedList<node>();
            que2 = build_tree(maze,x,y,set,que,que2);
            node tree = (node) que2.peek();
            
            
            //System.out.println(tree.loc);
            //System.out.println(nod[0][0].loc);
            Stack s = new Stack();
            
            DFS(tree,dec,s);         
            
        }
        
        public static Queue build_tree(int [][] maze, int i, int j,HashSet set,Queue que,Queue que2){
            String temp_loc = Integer.toString(i);
            temp_loc +=(Integer.toString(j));          
            
            //System.out.println(temp_loc);
            if(set.contains(temp_loc)){
                //do nothing;
            }
            else if(que.isEmpty()==true){
            set.add(temp_loc);
            node x =new node();
            x.loc = temp_loc;
            x.value=true;
            que.add(x);
            check(maze,x,i,j,set,que,que2);                           
            }
            //System.out.println(que2.size());
            return que2;        
        }


        public static void check(int [][] maze , node x,int i,int j,HashSet set,Queue que,Queue que2){
    
            //System.out.println(x.loc);
            //System.out.println(maze.length);
            // check adjacent locations and add adjacent nodes to the link
            if(i+1<maze.length&&maze[i+1][j]==0){
                node y = new node();
                y.loc = Integer.toString(i+1);
                y.loc+= Integer.toString(j);
                //System.out.println(y.loc);
                if(set.contains(y.loc)){
                    //do nothing
                }
                else{
                 que.add(y);
                //x.value=true;
                x.links[x.val] = y;
                x.val++;
                y.value = true;                
                set.add(y.loc);
                    
                }
                
                                
            }
    
            if(j+1<maze[0].length&&maze[i][j+1]==0){
                node q = new node();
                q.loc = Integer.toString(i);
                q.loc+= Integer.toString(j+1);
                //System.out.println(q.loc);
                
                //System.out.println(temp);
                if(set.contains(q.loc)){
                    //do nothing
                }
                else{
                    que.add(q);
                    x.links[x.val] = q;
                    x.val++; 
                    q.value = true;                
                    set.add(q.loc);
                    //System.out.println(q.loc);
                }                               
            }
            if(i-1>=0&&maze[i-1][j]==0){
                node p = new node();
                p.loc = Integer.toString(i-1);
                p.loc+= Integer.toString(j);
               
                if(set.contains(p.loc)){/*do nothing*/}
                else{
                    que.add(p);
                    //x.value=true;
                    x.links[x.val] = p;
                    x.val++;
                    p.value = true;                
                    set.add(p.loc);                    
                }                
            }
            
            if(j-1>=0&&maze[i][j-1]==0){
                node z = new node();
                z.loc = Integer.toString(i);
                z.loc+= Integer.toString(j-1);
                
                if(set.contains(z.loc)){/*do nothing*/}
                else{
                    que.add(z);
                    //x.value=true;
                    x.links[x.val] = z;
                    z.value=true;
                    x.val++;                
                    set.add(z.loc);
                }
                
            }
            
            //System.out.println(x.loc);
            
            que2.add(que.remove());
            //System.out.println(que.peek());
            node t =  (node) que.peek();
            if(t==null)return;
            char temp = t.loc.charAt(0);
            
            int s = (int) (temp-'0');
            //System.out.println(s);
            char temp2 = t.loc.charAt(1);
            
            int d = (int)(temp2-'0');
            //System.out.println(d);
            node g = (node) que.peek();
            //System.out.println(g.loc);
            if(que.isEmpty()!=true){
                check(maze,g,s,d,set,que,que2);
            }
            
            //return que2;
    
    
} 
        
        
        public static void DFS(node x,String dec,Stack s){
            
            
            //System.out.println(x.val);
            if(x.loc.equals(dec)) {
                System.out.println("found");
                System.out.println("Shortest Distance is : "+ s.size());
                String nodeloc ="";
                while(s.size()!=0){
                    node temp = (node) s.pop();
                    //System.out.print(temp.loc + "<-");
                    nodeloc = temp.loc + " -> " + nodeloc;                    
                }                
                System.out.print(nodeloc.substring(0, nodeloc.length()-3));
                System.out.println("-> " + dec);
                //System.out.println(nodeloc.length());              
                
                System.exit(0);
                return;
            }
            //System.out.println(x.loc);
            for(int i =0 ; i<x.val;i++)
            {
                //System.out.println(x.loc);
                s.add(x);
                node a = (node) s.peek();
                
                if(x.links[i]!=null) {
                   // System.out.println("hi");
                    DFS(x.links[i],dec,s);                   
                }
                if(x.loc==a.loc){
                    s.remove(a);
                }
                
            }     
        }        
        
}
