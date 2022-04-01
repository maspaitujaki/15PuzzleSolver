

/**
 * Main.java
 */
public class Main  {
    public static void main(String[] args) {
        
        Board b = null;
        try {
            b = Board.fromFile(args[0]);
        } catch (Exception e) {
            if (e instanceof NoSuchFieldException) {
                System.out.println("File tidak ditemukan, membuat board random...");
            }
            b = Board.random();
        }

        System.out.println();
        System.out.println("Board puzzle berhasil dibuat");
        b.show();

        int kurang = 0;
        System.out.println();
        System.out.println(" _____ _________");
        System.out.println("|  i  |Kurang(i)|");
        System.out.println("|_____|_________|");
        for (int i = 1; i <= 16; i++) {
            int kurangi = PuzzleSolver.kurang(b, i);
            kurang += kurangi;
            if (i < 10) {
                System.out.print("|  " + i + "  |");
            } else {
                System.out.print("| " + i + "  |");
            }
            if (kurangi < 10) {
                System.out.println("    " + kurangi + "    |");
            } else {
                System.out.println("   " + kurangi + "   |");
            }
        }
        System.out.println("| tot |    "+kurang+"   |");
        System.out.println("|_____|_________|");
        System.out.println("|  X  |    " + PuzzleSolver.xBesar(b) + "    |");
        System.out.println("|_____|_________|+");
        System.out.println("|       "+(kurang + PuzzleSolver.xBesar(b))+"      |");
        System.out.println("|_______________|");

        
        

        if (!b.isSolvable()) {
            System.out.println("Status tujuan tidak dapat dicapai.");
        } else{
            long startTime = System.nanoTime();
            // ArrayList<Board> solution = 
            Pair<Board,Integer> result = PuzzleSolver.solve(b);
            long stopTime = System.nanoTime();
            // 1 second = 1_000_000_000 nano seconds
            double elapsedTimeInSecond = (double) (stopTime - startTime) / 1_000_000_000;

            b.showSolution(result.getKey().getTail());
            
            System.out.println("Waktu eksekusi: " + elapsedTimeInSecond + " seconds");
            System.out.println("Jumlah node dibangkitkan: " + result.getValue());
        }
    }
}