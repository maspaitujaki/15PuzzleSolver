/**
 * PuzzleSolver.java
 */
import java.util.ArrayList;


public class PuzzleSolver {

    public static int kurang(Board b, int x) {
        boolean start = false;
        int counter = 0;
        for (int i = 0; i < b.getN(); i++) {
            for (int j = 0; j < b.getN(); j++) {
                if (b.getElmt(i, j) == x){
                    start = true;
                }
                if(start && b.getElmt(i, j) < x && b.getElmt(i, j) != 0){
                    counter++;
                }
            }
        }
        return counter;
    }

    public static int xBesar(Board b) {
        int i = 0;
        int j = 0;
        int ires = 0;
        int jres = 0;
        for (i = 0; i < b.getN(); i++) {
            for (j = 0; j < b.getN(); j++) {
                if (b.getElmt(i, j) == 16){
                    ires = i;
                    jres = j;
                }
            }
        }
        return (((ires + jres) % 2) == 0 ? 0: 1);
    }

    public static int gTopi(Board b) {
        int counter = 0;
        for (int i = 0; i < b.getN(); i++) {
            for (int j = 0; j < b.getN(); j++) {
                // System.out.println(Integer.toString(b.getElmt(i, j)) + " == " +  Integer.toString((i*4)+j+1));
                if(b.getElmt(i, j)!= (i*4)+j+1 && b.getElmt(i, j)!= 16)
                    counter++;
            }
        }
        return counter;
    }

    public static int fTopi(Board b) {
        return b.getF();
    }

    public static int cTopi(Board b) {
        return gTopi(b) + fTopi(b);
    }

    public static boolean isSolution(Board b) {
        return gTopi(b) == 0;
    }

    public static int minFour(int one, int two, int three, int four) {
        return Math.min(one, Math.min(two, Math.min(three, four)));
    }

    public static int findMin(ArrayList<Pair<Board, String>> list) {
        int min = Integer.MAX_VALUE;
        int idx = 0;
        int i = 0;
        for (Pair<Board, String> pair : list) {
            if (pair.getKey().getC() < min) {
                min = pair.getKey().getC();
                idx = i;
            }
            i++;
        }
        return idx;
    }

    public static boolean isBoardEqual(Pair<Board, String> b1, Pair<Board, String> b2) {
        return b1.getKey().equals(b2.getKey());
    }

    public static boolean has(ArrayList<Pair<Board, String>> list, Pair<Board, String> b) {
        for (Pair<Board, String> pair : list) {
            if (isBoardEqual(pair, b)) {
                return true;
            }
        }
        return false;
    }

    public static Pair<Board, Integer> solve(Board b){
        ArrayList<Pair<Board,String>> boardList = new ArrayList<Pair<Board,String>>();
        Board solutionSoFar = new Board(b);
        boolean end = false;
        Pair<Board, String> currPair = new Pair<Board, String>(solutionSoFar, "");
        int nodeCount = 1;

        while (!end) {
            ArrayList<Pair<Board,String>> localBoardList = new ArrayList<Pair<Board,String>>();
            Pair<Board, String> upPair;
            Pair<Board, String> rightPair;
            Pair<Board, String> leftPair;
            Pair<Board, String> downPair;
            if (!(currPair.getKey().getEmpty_x() == 0 || currPair.getValue().equals("down"))) {
                upPair = new Pair<Board, String>(new Board(currPair.getKey()), "up");
                upPair.getKey().setF(currPair.getKey().getF() + 1);
                for (String path : currPair.getKey().getTail()) {
                    upPair.getKey().addTail(path);
                }
                upPair.getKey().addTail("up");
                upPair.getKey().up();
                    localBoardList.add(upPair);
                nodeCount++;

                // if (!has(boardList, upPair)) {
                //     localBoardList.add(upPair);
                // }
            }
            if (!(currPair.getKey().getEmpty_x() == currPair.getKey().getN() - 1 || currPair.getValue().equals("up"))) {
                downPair = new Pair<Board, String>(new Board(currPair.getKey()), "down");
                downPair.getKey().setF(currPair.getKey().getF() + 1);
                for (String path : currPair.getKey().getTail()) {
                    downPair.getKey().addTail(path);
                }
                downPair.getKey().addTail("down");
                downPair.getKey().down();
                localBoardList.add(downPair);
                nodeCount++;


                // if (!has(boardList, downPair)) {
                //     localBoardList.add(downPair);
                // }
            }
            if (!(currPair.getKey().getEmpty_y() == 0 || currPair.getValue().equals("right"))) {
                leftPair = new Pair<Board, String>(new Board(currPair.getKey()), "left");
                leftPair.getKey().setF(currPair.getKey().getF() + 1);
                for (String path : currPair.getKey().getTail()) {
                    leftPair.getKey().addTail(path);
                }
                leftPair.getKey().addTail("left");
                leftPair.getKey().left();
                    localBoardList.add(leftPair);
                nodeCount++;


                // if (!has(boardList, leftPair)) {
                //     localBoardList.add(leftPair);
                // }
            }
            if (!(currPair.getKey().getEmpty_y() == currPair.getKey().getN() - 1 || currPair.getValue().equals("left"))) {
                rightPair = new Pair<Board, String>(new Board(currPair.getKey()), "right");
                rightPair.getKey().setF(currPair.getKey().getF() + 1);
                for (String path : currPair.getKey().getTail()) {
                    rightPair.getKey().addTail(path);
                }
                rightPair.getKey().addTail("right");
                rightPair.getKey().right();
                localBoardList.add(rightPair);
                nodeCount++;


                // if (!has(boardList, rightPair)) {
                //     localBoardList.add(rightPair);
                // }
            }

            for (Pair<Board,String> pair : localBoardList) {
                boardList.add(pair);
            }
            
            int iMin = PuzzleSolver.findMin(boardList);
            currPair = boardList.get(iMin);
            boardList.remove(iMin);


            localBoardList.clear();
            if (isSolution(currPair.getKey())) {
                solutionSoFar = currPair.getKey();
                ArrayList<Pair<Board,String>> fooListCopy = new ArrayList<Pair<Board,String>>();
                for (Pair<Board,String> pair : boardList) {
                    if (pair.getKey().getC() < currPair.getKey().getC()) {
                        fooListCopy.add(pair);
                    }
                }

                boardList = fooListCopy;

                if(boardList.isEmpty()){
                    end = true;
                }
                else{
                    currPair = boardList.get(PuzzleSolver.findMin(boardList));
                }
            }
        }
        return new Pair<Board,Integer>(solutionSoFar, nodeCount);       
    }
}

