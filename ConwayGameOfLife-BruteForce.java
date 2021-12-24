import java.time.LocalDateTime;
import java.util.Arrays;

public class ConwayGameOfLife {

    int genCount = 0;
    int testCount = 0;
    int best = 0;

    static String[] hexStorage = new String[32];
    static String[] start = {null, null, null, null, null, null, null, null};
    static String[] bestStart = new String[8];

    // Debugging patterns
    //static String[] startingPattern = {"00", "00", "00", "00", "FF", "00", "00", "00"};
    //static String[] startingPattern = {"00", "00", "20", "70", "50", "20", "00", "00"};
    //static String[] startingPattern = {"00", "00", "00", "20", "10", "70", "00", "00"};

    public static void main(String[] args) {
        ConwayGameOfLife cGoL = new ConwayGameOfLife();
        LocalDateTime twoHoursFromNow = LocalDateTime.now().plusHours(2);

        for (int i = 0 ; i < 32 ; i++) { hexStorage[i] = "00000000"; }
        if (start[0] == null) { start = startFill(); }

        int i = 0;
        while (LocalDateTime.now().compareTo(twoHoursFromNow) i < 15) {
            cGoL.fitness(start);
            start = startFill();
            cGoL.dumpLast();
            i++;
        }
    }

    static String[] startFill() {
        String[] hex = new String[8];
        for (int i = 0 ; i < hex.length ; i++) {
            int rand1 = (int) (16 * Math.random());
            int rand2 = (int) (16 * Math.random());
            String hex1 = Integer.toHexString(rand1);
            String hex2 = Integer.toHexString(rand2);
            hex[i] = hex1 + hex2;
        }
        return hex;
    }

    public int generate() {

        boolean[][] board = new boolean[32][32];
        for (int i = 0; i < hexStorage.length; i++) {
            for (int j = 0; j < hexStorage[i].length(); j++) {
                int tempHexToDec = Integer.parseInt(hexStorage[i].substring(j, j + 1), 16);
                StringBuilder tempHexToBin = new StringBuilder(Integer.toBinaryString(tempHexToDec));

                while (tempHexToBin.length() < 4) {
                    tempHexToBin.insert(0, "0");
                }

                for (int t = 0; t < tempHexToBin.length(); t++) {
                    String temp = tempHexToBin.substring(t, t + 1);
                    board[i][j * 4 + t] = !temp.equals("0");
                }
            }
        }

        boolean[][] boardFunc = new boolean[32][32];
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[i].length - 1; j++) {
                boolean[] neighbors = { board[i - 1][j], board[i - 1][j + 1],
                                        board[i][j + 1], board[i + 1][j + 1],
                                        board[i + 1][j], board[i + 1][j - 1],
                                        board[i][j - 1], board[i - 1][j - 1]};

                int aliveCount = 0;
                for (boolean neighbor : neighbors) {
                    if (neighbor) {
                        aliveCount++;
                    }
                }
                if (board[i][j] && aliveCount < 2) {
                    boardFunc[i][j] = false;
                } else if (board[i][j] && aliveCount > 3) {
                    boardFunc[i][j] = false;
                } else if (!board[i][j] && aliveCount == 3) {
                    boardFunc[i][j] = true;
                } else {
                    boardFunc[i][j] = board[i][j];
                }
            }
        }

        int aliveCount = 0;
        for (int i = 0; i < boardFunc.length; i++) {
            StringBuilder hexString = new StringBuilder();
            for (int j = 0; j < boardFunc[i].length; j += 4) {
                StringBuilder bin = new StringBuilder();
                for (int column = j; column < j + 4; column++) {
                    if (boardFunc[i][column]) {
                        bin.append("1");
                        aliveCount++;
                    } else {
                        bin.append("0");
                    }
                }
                int tempBinToDec = Integer.parseInt(bin.toString(), 2);
                String tempDecToHex = Integer.toHexString(tempBinToDec);
                hexString.append(tempDecToHex);
            }
            hexStorage[i] = hexString.toString();
        }

        genCount++;
        return aliveCount;
    }

    public void fitness(String[] input) {
        for (int i = 12; i < 20; i++) {
            hexStorage[i] = "000" + input[i - 12] + "000";
        }
        int aliveCount = 0;
        genCount = 0;
        for (int i = 0; i < 1000; i++) {
            aliveCount = generate();
        }

        if (aliveCount > best) {
            best = aliveCount;
            bestStart = input;
            StringBuilder pattern = new StringBuilder();
            pattern.append("[ ");
            for (String s : input) {
                pattern.append(s);
                pattern.append(",");
                pattern.append(" ");
            }
            pattern.delete(32, 33);
            pattern.append(']');

            String patternString = pattern.toString();
            String patternUpper = patternString.toUpperCase();
            System.out.println("Generations: " + testCount);
            System.out.println("The current best fitness is: " + best);
            System.out.println("The pattern is: " + patternUpper);
            System.out.println("");
        }
        testCount++;
    }

    public void dumpLast() {
        System.out.println("Final generation count: " + testCount);
        StringBuilder pattern = new StringBuilder();
        pattern.append("[ ");
        for (String s : bestStart) {
            pattern.append(s);
            pattern.append(",");
            pattern.append(" ");
        }
        pattern.delete(32, 33);
        pattern.append(']');
        String patternString = pattern.toString();
        String patternUpper = patternString.toUpperCase();
        System.out.println("The final pattern is: " + patternUpper);
        System.out.println("The best fitness is: " + best);
        for (int i = 0; i < 32; i++) {
            hexStorage[i] = "00000000";
        }
        for (int i = 12; i < 20; i++) {
            hexStorage[i] = "000" + bestStart[i - 12] + "000";
        }
        boolean[][] board = new boolean[32][32];
        for (int i = 0; i < hexStorage.length; i++) {
            for (int j = 0; j < hexStorage[i].length(); j++) {
                int tempHexToDec = Integer.parseInt(hexStorage[i].substring(j, j + 1), 16);
                StringBuilder tempDecToBin = new StringBuilder(Integer.toBinaryString(tempHexToDec));
                while (tempDecToBin.length() < 4) {
                    tempDecToBin.insert(0, "0");
                }
                for (int t = 0; t < tempDecToBin.length(); t++) {
                    String temp = tempDecToBin.substring(t, t + 1);
                    board[i][j * 4 + t] = !temp.equals("0");
                }
            }
        }
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                if (board[i][j]) {
                    System.out.print("*");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
