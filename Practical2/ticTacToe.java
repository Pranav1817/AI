
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.System.exit;

class TreeNode{
    char[][]board;
    ArrayList<TreeNode> children;
    char move;
    public TreeNode(){
        this.board = new char[3][3];
        for(int i =0; i< this.board.length; i++){
            for(int j = 0; j< this.board[i].length; j++){
                this.board[i][j] = 'n';
            }
        }
        this.children = new ArrayList<>();
        this.move = 'x';
    }

    public TreeNode(TreeNode node)
    {
        this.board = new char[3][3];
        this.children = new ArrayList<>();

        for(int i = 0; i< node.board.length; i++)
        {
            for(int j = 0; j< node.board[i].length; j++)
            {
                this.board[i][j] = node.board[i][j];
            }
        }
        if(node.move == 'X') this.move = 'O';
        else if(node.move == 'O') this.move = 'X';
        else{
            this.move = 'X';
        }
    }
}

public class ticTacToe {

    public static void print2DArray(char[][] arr)
    {
        for (char[] chars : arr) {
            for (int j = 0; j < chars.length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void printSearchSpaceTree(TreeNode root)
    {
        if(root == null) return;
        Queue<TreeNode> pending = new LinkedList<>();
        pending.add(root);
        int count = 1;
        while(!pending.isEmpty())
        {
            TreeNode temp = pending.remove();
            print2DArray(temp.board);
            count--;

            pending.addAll(temp.children);

            if(count == 0){
                count = pending.size();
                System.out.println("----------------------------------------");
            }
        }
    }

    public static boolean checkWinner(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Check rows
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Check columns
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Check diagonal (top-left to bottom-right)
        }
        else return board[0][2] == player && board[1][1] == player && board[2][0] == player; // Check diagonal (top-right to bottom-left)
    }

    public static boolean checkComplete(char[][]board)
    {
        int count = 0;
        for(char[] ele: board){
            for(char ch: ele)
            {
                if(ch == 'n') count++;
            }
        }
       return count == 0;
    }


    public static void DFS (TreeNode root, ArrayList<TreeNode> ds, ArrayList<ArrayList<TreeNode>> ans,char winner)
    {
        if(root == null) return;
        if(checkWinner(root.board,winner))
        {
            ds.add(root);
            ArrayList<TreeNode> temp = new ArrayList<>(ds);
            ds.remove(ds.size()-1);
            ans.add(temp);
            printPath(ans);
            exit(0);
        }
        else if(checkComplete(root.board))
        {
            return;
        }
        else{
            for(int i = 0; i< root.children.size(); i++)
            {
                TreeNode child = root.children.get(i);
                ds.add(child);
                DFS(child,ds,ans,winner);
                ds.remove(ds.size()-1);
            }
        }
    }
    public static TreeNode createSearchSpaceTree()
    {
        TreeNode root = new TreeNode();
        Queue<TreeNode> pendingNode = new LinkedList<>();
        pendingNode.add(root);
        while(!pendingNode.isEmpty())
        {
            TreeNode temp = pendingNode.remove();

                for(int j = 0; j< 3; j++)
                {
                    for(int k = 0; k< 3; k++)
                    {
                        if(temp.board[j][k] == 'n')
                        {
                            TreeNode newNode = new TreeNode(temp);
                            newNode.board[j][k] = newNode.move;
                            temp.children.add(newNode);
                            pendingNode.add(newNode);
                        }
                    }
                }
        }
        return root;
    }
    public static void printPath(ArrayList<ArrayList<TreeNode>> ans)
    {
        int i = 0;
        System.out.println("Different Path for win: ");
        for(ArrayList<TreeNode> ele: ans)
        {
            i++;
            System.out.println("Path number: "+i);
            for(TreeNode temp: ele)
            {
                print2DArray(temp.board);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeNode root = createSearchSpaceTree();
        ArrayList<ArrayList<TreeNode>> ans = new ArrayList<>();
        System.out.println("Enter the search symbol: ");
        char winner = sc.next().charAt(0);
        DFS(root,new ArrayList<>(),ans,winner);

    }
}
