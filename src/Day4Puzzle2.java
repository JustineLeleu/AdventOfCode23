import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Day4Puzzle2 {
    public static void main(String[] args){
        ArrayList<Card> cards;
        long sum = 0;

        try(InputStream inputFS = new FileInputStream("src/inputs/puzzle4.text"); BufferedReader br = new BufferedReader(new InputStreamReader(inputFS)))
        {
            cards = (ArrayList<Card>) br.lines()
                    .map(Card::new)
                    .collect(Collectors.toList());

            for (Card card : cards){
                for (int i = 0; i < card.getIteration(); i++)
                {
                    int result = card.getResult();
                    for (int j = 1; j <= result; j++){
                        cards.get(cards.indexOf(card) + j).addIteration();
                    }
                }
                sum += card.getIteration();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        System.out.println(sum);
    }
}

class Card{
    private int iterations;
    final private String[] winners;
    final private String numbers;

    Card(String line)
    {
        this.iterations = 1;
        this.winners = line.split(":")[1].substring(1).split("\\|")[0].split("(?<=[0-9])\\s");
        this.numbers = line.split("\\|")[1].substring(1).replaceAll("(?<=[0-9])\\s", ".");
    }

    public int getIteration(){
        return this.iterations;
    }

    public void addIteration(){
        this.iterations++;
    }

    public int getResult(){
        int result = 0;

        for (String winner : this.winners){
            if (this.numbers.contains(winner)) ++result;
        }
        return result;
    }
}
