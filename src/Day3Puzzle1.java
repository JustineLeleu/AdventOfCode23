import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Day3Puzzle1
{
    static int sum (ArrayList<Integer> nums)
    {
        int result = 0;

        for (int x : nums)
        {
            result += x;
        }

        return result;
    }
    public static void main(String[] args)
    {
        ArrayList<String> lines;
        ArrayList<Integer> nums = new ArrayList<>();

        try(InputStream inputFS = new FileInputStream("src/puzzle3.text"); BufferedReader br = new BufferedReader(new InputStreamReader(inputFS)))
        {
            lines = (ArrayList<String>) br.lines()
                    .collect(Collectors.toList());

            for (int i = 0; i < lines.size(); i++)
            {
                String line = lines.get(i);
                int x = 0;
                for (int j = 0; j < line.length(); j++)
                {
                    if (Character.isDigit(line.charAt(j)))
                    {
                        x++;
                    }
                    if ((!Character.isDigit(line.charAt(j)) && x > 0) || (Character.isDigit(line.charAt(j)) && j == line.length() - 1))
                    {
                        boolean isLastIndex = j < line.length() - 1 || !Character.isDigit(line.charAt(j));
                        int index = isLastIndex ? j - x : j - x + 1;
                        int endNum = isLastIndex ? j : j + 1;
                        int lineStart = (index > 0) ? index - 1 : index;
                        int lineStop = (j < line.length() - 1) ? j + 1 : j;
                        int num = Integer.parseInt(line.substring(index, endNum));
                        boolean isCorrect = false;

                        if (index > 0 && line.charAt(index - 1) != '.') isCorrect = true;
                        else if (j < line.length() - 1 && line.charAt(j) != '.') isCorrect = true;
                        else if (i > 0 && lines.get(i - 1).substring(lineStart, lineStop).split("[0-9.]").length > 0) isCorrect = true;
                        else if (i < line.length() - 1 && lines.get(i + 1).substring(lineStart, lineStop).split("[0-9.]").length > 0) isCorrect = true;

                        if (isCorrect) nums.add(num);

                        x = 0;
                    }
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        int sum = sum(nums);

        System.out.println(lines);
        System.out.println(nums);
        System.out.println(sum);
    }
}