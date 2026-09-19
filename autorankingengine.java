import java.util.Arrays;

class Player implements Comparable<Player> {
    private String name;
    private int matchesPlayed;
    private double battingAverage;
    private boolean injured;

    Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;
    }

    static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= 5 && !injured;
    }

    public int compareTo(Player other) {
        if (this.battingAverage < other.battingAverage)
            return 1;
        if (this.battingAverage > other.battingAverage)
            return -1;
        return 0;
    }

    static String draftAndRank(Player[] players) {
        Player[] draftable = new Player[players.length];
        int count = 0;

        for (int i = 0; i < players.length; i++) {
            if (isDraftable(players[i].matchesPlayed) ||
                isDraftable(players[i].matchesPlayed, players[i].injured)) {
                draftable[count++] = players[i];
            }
        }

        Player[] result = new Player[count];

        for (int i = 0; i < count; i++)
            result[i] = draftable[i];

        Arrays.sort(result);

        String output = "";

        for (int i = 0; i < result.length; i++) {
            output += (i + 1) + ". " + result[i].name;

            if (i < result.length - 1)
                output += " | ";
        }

        return output;
    }

    public static void main(String[] args) {
        Player[] players = {
            new Player("Virat", 15, 48.0, false),
            new Player("Rahul", 7, 55.0, false),
            new Player("Sameer", 3, 60.0, false),
            new Player("Dev", 12, 20.0, true)
        };

        System.out.println(draftAndRank(players));
    }
}