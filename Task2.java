//Imports
import java.util.Random;

public class Task2 {

    public static void main(String[] args) {
        Generator g = new Generator();

        for (int i = 0; i< 50; i++)
        {
            Random rand = new Random();
            int value = rand.nextInt(50) + 1;
            g.generateStrings(value);
        }

        g.toString();
    }
}
