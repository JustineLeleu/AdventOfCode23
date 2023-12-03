import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day2Puzzle2
{
    static List<Integer> getMinSetPow()
    {
        List<Integer> list;
        try(InputStream inputFS = new FileInputStream("src/puzzle2.text"); BufferedReader br = new BufferedReader(new InputStreamReader(inputFS)))
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
        String[] games = line.split(":")[1].split(";");
        Map<String, Integer> colors = new HashMap<>();
        int pow = 1;

        for (String game : games)
        {
            String[] cubes = game.split(",");
            for (String cube : cubes)
            {
                int num = Integer.parseInt(cube.split(" ")[1]);
                String cubeColor = cube.split(" ")[2];
                if (!colors.containsKey(cubeColor) || num > colors.get(cubeColor)) colors.put(cubeColor, num);
            }
        }

        for (int color : colors.values())
        {
            pow *= color;
        }

        return pow;
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
        List<Integer> list = getMinSetPow();
        int sum = sum(list);
        System.out.println("List: " + list);
        System.out.println("Sum: " + sum);
    }
}