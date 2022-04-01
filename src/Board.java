/**
 * Board.java
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Board {
    private ArrayList<String> tail;
    private int f;
    private int c;
    private int g;
    private int N;
    private int empty_x;
    private int empty_y;
    private int[][] board;

    public Board() {
        super();
        this.tail = new ArrayList<String>();
        this.f = 0;
        this.N = 4;
        this.empty_x = 0;
        this.empty_y = 0;
        this.c = 0;
        this.g = 0;
        this.board = new int[4][4];
    }

    public Board(Board inputBoard) {
        super();
        this.tail = new ArrayList<String>();
        this.N = inputBoard.N;
        this.f = inputBoard.f;
        this.c = inputBoard.c;
        this.g = inputBoard.g;
        this.empty_x = inputBoard.empty_x;
        this.empty_y = inputBoard.empty_y;
        this.board = new int[this.N][this.N];
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                this.board[i][j] = inputBoard.board[i][j];
            }
        }
    }

    public void addTail(String input) {
        this.tail.add(input);
    }

    public ArrayList<String> getTail() {
        return tail;
    }
    public int getC() {
        return c;
    }
    public int getG() {
        return g;
    }

    public int getElmt(int i, int j){
        return this.board[i][j];
    }

    public int getN() {
        return N;
    }

    public int getF() {
        return f;
    }

    public int getEmpty_x() {
        return empty_x;
    }

    public int getEmpty_y() {
        return empty_y;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setN(int n) {
        N = n;
    }

    public void setEmpty_x(int empty_x) {
        this.empty_x = empty_x;
    }

    public void setEmpty_y(int empty_y) {
        this.empty_y = empty_y;
    }

    public void setElmt(int i, int j, int X){
        this.board[i][j] = X;
    }

    private void swap(int i, int j, int k, int l) {
        int temp = this.board[i][j];
        this.board[i][j] = this.board[k][l];
        this.board[k][l] = temp;
    }

    public static Board fromFile(String fileName) {
        Board A = new Board();

        String currDir = System.getProperty("user.dir");
        fileName = currDir + "\\config\\" + fileName;

		List<String> list = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			//br returns as stream and convert it into a List
			list = br.lines().collect(Collectors.toList());
            int i = 0;
            for (String string : list) {
                int j = 0;

                String[] splited = string.split("\\s+");
                for (String string2 : splited) {
                    A.board[i][j] = Integer.parseInt(string2);
                    if (Integer.parseInt(string2) == 16) {
                        A.empty_x = i;
                        A.empty_y = j;
                    }
                    j++; 
                }
                i++;
            }
		} catch (IOException e) {
			e.printStackTrace();
		}

        return A;
    }

    public static Board random() {
        Board A = new Board();
        ArrayList<Integer> l = new ArrayList<Integer>();
        Random rand = new Random();
        for (int i = 1; i<=16;i++){
            l.add(i);
        }
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++){
                    int randomIndex = rand.nextInt(l.size());
                    int randomElement = l.remove(randomIndex);
                    if (randomElement == 16){
                        A.empty_x = i;
                        A.empty_y = j;
                    }
                    A.board[i][j] = randomElement;
            }
        
        return A;
    }

    public void show() {
        System.out.println(" ____ ____ ____ ____");
        for (int i = 0; i < N; i++) {
            System.out.print("|");
            for (int j = 0; j < N; j++) {

                int x = this.board[i][j];
                if (x == 16) {
                    System.out.print("    |");
                } else {
                    if (x == (i*4)+j+1){
                        if (x < 10) {
                            System.out.print("*");
                            System.out.print(" " + x);
                            System.out.print("*|");
                        } else{
                            System.out.print("*");
                            System.out.print(x);
                            System.out.print("*|");
                        }
                    } else {
                        if (x < 10) {
                            System.out.print(" ");
                            System.out.print(" " + x);
                            System.out.print(" |");
                        } else{
                            System.out.print(" ");
                            System.out.print(x);
                            System.out.print(" |");
                        }
                    }
                    
                } 
                    
                
            }
            System.out.println();
            System.out.println("|____|____|____|____|");
        }
    }

    public void updateG() {
        this.g = PuzzleSolver.gTopi(this);
    }

    public void updateC() {
        this.updateG();
        this.c = this.f + this.g;
    }

    public void showSolution(ArrayList<String> list) {
        for (String string : list) {
            if (string.equals("up")) {
                this.up();
                this.show();
            } else if (string.equals("down")) {
                this.down();
                this.show();
            } else if (string.equals("left")) {
                this.left();
                this.show();
            } else if (string.equals("right")) {
                this.right();
                this.show();
            }
        }
    }



    public void up() {
        swap(empty_x, empty_y, empty_x-1, empty_y);
        empty_x--;
        this.updateC();
    }

    public void down() {
        swap(empty_x, empty_y, empty_x+1, empty_y);
        empty_x++;
        this.updateC();
    }
    
    public void left() {
        swap(empty_x, empty_y, empty_x, empty_y-1);
        empty_y--;
        this.updateC();
    }

    public void right() {
        swap(empty_x, empty_y, empty_x, empty_y+1);
        empty_y++;
        this.updateC();
    }

    public boolean isSolvable() {
        int kurang = 0;
        for (int i = 1; i <= 16; i++) {
            int kurangi = PuzzleSolver.kurang(this, i);
            kurang += kurangi;
        }
        kurang += PuzzleSolver.xBesar(this);
        return (kurang % 2 == 0);
    }

    

    public boolean equals(Board b){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.board[i][j] != b.board[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}

