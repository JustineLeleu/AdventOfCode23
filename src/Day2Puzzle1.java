import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day2Puzzle1
{
    public enum Colors
    {
        RED("red", 12),
        BLUE("blue", 14),
        GREEN("green", 13);

        final String color;
        final int maxNum;

        Colors(String color, int maxNum)
        {
            this.color = color;
            this.maxNum = maxNum;
        }
    }
    static List<Integer> getPossibleGames()
    {
        List<Integer> list;
        try(InputStream inputFS = new FileInputStream("src/inputs/puzzle2.text"); BufferedReader br = new BufferedReader(new InputStreamReader(inputFS)))
        {
            list = br.lines()
                    .map(mapToItem)
                    .collect(Collectors.toList());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return list;
    }

    private static final Function<String, Integer> mapToItem = (line) -> {
        int index = Integer.parseInt(line.split(":")[0].split(" ")[1]);
        String[] games = line.split(":")[1].split(";");


        for (String game : games)
        {
            String[] cubes = game.split(",");
            for (String cube : cubes)
            {
                int num = Integer.parseInt(cube.split(" ")[1]);
                String cubeColor = cube.split(" ")[2];
                for (Colors color : Colors.values())
                {
                    if (Objects.equals(cubeColor, color.color) && num > color.maxNum) return 0;
                }
            }
        }
        return index;
    };

    static int sum (List<Integer> list)
    {
        int result = 0;

        for (int x : list)
        {
            result += x;
        }

        return result;
    }
    public static void main(String[] args)
    {
        List<Integer> list = getPossibleGames();
        int sum = sum(list);
        System.out.println("List: " + list);
        System.out.println("Sum: " + sum);
    }
}