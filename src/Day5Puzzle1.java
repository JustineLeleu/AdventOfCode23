import java.io.*;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Day5Puzzle1 {
    public static void main(String[] args){
        long[] seeds;
        Map<Integer, List<long[]>> maps = new HashMap<>();
        List<String> lines;

        try(InputStream inputFS = new FileInputStream("src/puzzle5.text"); BufferedReader br = new BufferedReader(new InputStreamReader(inputFS)))
        {
            seeds = Arrays.stream(br.readLine()
                    .split(":")[1]
                    .substring(1)
                    .split("\\s"))
                    .mapToLong(Long::parseLong)
                    .toArray();
            lines = br.lines()
                    .filter(line -> line.length() > 1)
                    .collect(Collectors.toList());
            lines.add("End map");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        List<long[]> numbers = new ArrayList<>();
        int key = 0;
        for (String line : lines){
            if (line.contains("map")){
                if(!numbers.isEmpty()){
                    maps.put(key, new ArrayList<>(numbers));
                    numbers.clear();
                    key++;
                }
                continue;
            }

            long[] nums = Arrays.stream(line.split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();
            numbers.add(nums);
        }

        long[] locations = new long[seeds.length];
        for (int i = 0; i < seeds.length; i++)
        {
            long source = seeds[i];
            for (List<long[]> map : maps.values()){
                for (long[] ranges : map){
                    if (source >= ranges[1] && source <= ranges[1] + ranges[2]){
                        source = ranges[0] + (source - ranges[1]);
                        break;
                    }
                }
            }
            locations[i] = source;
        }

        System.out.println(Arrays.toString(locations));
        long lowest = locations[0];
        for (long loc : locations){
            if (loc < lowest) lowest = loc;
        }
        System.out.println(lowest);
    }
}
