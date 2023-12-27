import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day1Puzzle2
{
    public enum Numbers
    {
        ONE("o1e", "one"),
        TWO("t2o", "two"),
        THREE("t3e", "three"),
        FOUR("f4r", "four"),
        FIVE("f5e", "five"),
        SIX("s6x", "six"),
        SEVEN("s7n", "seven"),
        EIGHT("e8t", "eight"),
        NINE("n9e", "nine");

        final String num;
        final String stringNum;

        Numbers(String num, String stringNum)
        {
            this.num = num;
            this.stringNum = stringNum;
        }
    }
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

    private static final Function<String, Integer> mapToItem = (line) -> {
        for (Numbers value : Numbers.values())
        {
            line = line.replace(value.stringNum, value.num);
        }

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
    public static void main (String[] args)
    {
        List<Integer> list = createList();
        int sum = sum(list);
        System.out.println("List: " + list);
        System.out.println("Sum: " + sum);
    }
}