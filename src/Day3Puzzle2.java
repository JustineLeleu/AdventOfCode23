import java.io.*;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Day3Puzzle2 {
    public static void main(String[] args){
        ArrayList<String> lines;
        ArrayList<Integer> validNums = new ArrayList<>();
        Map<String, LinkedList<Integer>> stars = new HashMap<>();

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
                        String loc = null;

                        if (index > 0 && line.charAt(index - 1) == '*'){
                            loc = String.valueOf(i) + "/" + String.valueOf(index - 1);
                        }
                        else if (j < line.length() - 1 && line.charAt(j) == '*'){
                            loc = String.valueOf(i) + "/" + String.valueOf(j);
                        }
                        else if (i > 0 && lines.get(i - 1).substring(lineStart, lineStop).contains("*")){
                            loc = String.valueOf(i - 1) + "/" + String.valueOf(lines.get(i - 1).substring(lineStart, lineStop).indexOf("*") + lineStart);
                        }
                        else if (i < line.length() - 1 && lines.get(i + 1).substring(lineStart, lineStop).contains("*")) {
                            loc = String.valueOf(i + 1) + "/" + String.valueOf(lines.get(i + 1).substring(lineStart, lineStop).indexOf("*") + lineStart);
                        }

                        if (loc != null){
                            if (stars.containsKey(loc)) stars.get(loc).add(num);
                            else{
                                stars.put(loc, new LinkedList<Integer>());
                                stars.get(loc).add(num);
                            }
                        }

                        x = 0;
                    }
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        System.out.println(stars);

        for (LinkedList<Integer> nums : stars.values())
        {
            if (nums.size() == 2) validNums.add(nums.get(0) * nums.get(1));
        }

        int sum = sum(validNums);
        System.out.println(sum);
    }

    static int sum (ArrayList<Integer> nums)
    {
        int result = 0;

        for (int x : nums)
        {
            result += x;
        }

        return result;
    }
}
