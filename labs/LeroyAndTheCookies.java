
import java.util.Scanner;

/**
 * This is a game where Leroy the Lynx tries to eat nine cookies in a
 * line.  Unfortunately, he can only do it in a certain way, by rolling dice.
 */

public class LeroyAndTheCookies {
    public static void main(String[] args) {
        System.out.println("Leroy has nine cookies from the Rat, numbered 1-9 in a line, like this:");
        System.out.println("123456789");
        System.out.println("Leroy wants to eat all nine cookies (he is a lynx, after all),\n  leaving the line of cookies looking like this:");
        System.out.println("---------");
        System.out.println("Rather than just scarfing them all down at once,\n  Leroy decides to roll two dice to choose which cookies to eat.");
        System.out.println("Leroy can eat any combination of cookies whose numbers add up to the sum of the two dice.");
        System.out.println("Leroy may continue to roll the dice and eat cookies as long as he wants.");
        System.out.println();

        // Set up which cookies are eaten vs not eaten and display them.
        boolean[] eaten = new boolean[10];
        for (int i = 1; i <= 9; i++) {
            System.out.print(i);
        }
        System.out.println();

        // Start the game.
        int score = 45;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int roll = (int)(1 + Math.random() * 6);  // first die
            if (eaten[7] && eaten[8] && eaten[9]) {
                System.out.println("Cookies 7, 8, and 9 are eaten, so Leroy will only roll one die.");
            } else {
                roll += (int)(1 + Math.random() * 6); // second die
            }
            System.out.println("Leroy rolls a " + roll + ".");

            // Pick how many cookies Leroy wants to eat.
            System.out.print("How many cookies should Leroy eat? ");
            int count = scanner.nextInt();
            if (count == 0) {
                System.out.println("Game over. Leroy doesn't win, but earns a final score of " + score + ".");
                break;
            }

            // Get the specific cookies Leroy wants to eat.  Make sure it's legal set of cookies.
            System.out.println("Enter the numbers of the cookies Leroy should eat\n  (all on one line, with spaces in between, or on separate lines):");
            int total = 0;
            for (int i = 0; i < count; i++) {
                int n = scanner.nextInt();
                if (eaten[n]) {
                    System.out.println("You can't pick a cookie that is already eaten. Leroy forfeits the game.");
                    break;
                }
                eaten[n] = true;
                score -= n;
                total += n;
            }
            if (roll != total) {
                System.out.println("Those numbers don't add up to " + roll + ". Leroy forfeits the game.");
                break;
            }

            // Display which cookies are eaten vs not eaten.
            for (int i = 1; i <= 9; i++) {
                if (eaten[i]) {
                    System.out.print("-");
                } else {
                    System.out.print(i);
                }
            }
            System.out.println();

            if (score == 0) {
                System.out.println("Leroy ate all the cookies!  Leroy wins!");
                break;
            }
        }
    }
}