import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day1Puzzle1
{
    static List<Integer> createList()
    {
        List<Integer> list;
        try(InputStream inputFS = new FileInputStream("src/inputs/puzzle1.text"); BufferedReader br = new BufferedReader(new InputStreamReader(inputFS)))
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

    private static Function<String, Integer> mapToItem = (line) -> {

        char[] arr = line.toCharArray();
        ArrayList<Character> nums = new ArrayList<>();

        for (char el : arr)
        {
            if (Character.isDigit(el))
            {
                nums.add(el);
            }
        }

        return (Integer) Integer.parseInt(String.valueOf(nums.get(0)) + nums.get(nums.size() - 1));
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
        List<Integer> list = createList();
        int sum = sum(list);
        System.out.println("Liste: " + list);
        System.out.println("Sum: " + sum);
    }
}