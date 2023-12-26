import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Day4Puzzle1 {
    public static void main(String[] args){
        ArrayList<String> lines;
        List<Integer> results = new LinkedList<>();
        try(InputStream inputFS = new FileInputStream("src/puzzle4.text"); BufferedReader br = new BufferedReader(new InputStreamReader(inputFS)))
        {
            lines = (ArrayList<String>) br.lines()
                    .collect(Collectors.toList());

            for (String line : lines){
                String[] winners = line.substring(8).split("\\|")[0].split("(?<=[0-9])\\s");
                String numbers = line.split("\\|")[1].replaceAll("(?<=[0-9])\\s", ".");
                int result = 0;

                for (String winner : winners){
                    if (numbers.contains(winner))
                    {
                        result = (result == 0) ? 1 : result * 2;
                    }
                }
                if (result != 0) results.add(result);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        System.out.println(results);

        int sum = 0;
        for (int x : results)
        {
            sum += x;
        }

        System.out.println(sum);
    }
}
