import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Day5Puzzle2 {
    public static void main(String[] args){
        long[] seeds;
        Map<Integer, List<long[]>> maps = new HashMap<>();
        List<String> lines;

        try(InputStream inputFS = new FileInputStream("src/inputs/puzzle5.text"); BufferedReader br = new BufferedReader(new InputStreamReader(inputFS)))
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

        System.out.println(Arrays.toString(seeds));

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

        long loc = -1;

        for (int i = 0; i < seeds.length/2; i++){
            for (int j = 0; j < seeds[i+1]; j++){
                long source = seeds[i] + j;
                System.out.println(source);
                for (List<long[]> map : maps.values()) {
                    for (long[] ranges : map) {
                        if (source >= ranges[1] && source <= ranges[1] + ranges[2]) {
                            source = ranges[0] + (source - ranges[1]);
                            break;
                        }
                    }
                }
                if (loc == -1) loc = source;
                else if (source < loc) loc = source;
            }
            i++;
        }

        System.out.println(loc);
    }
}
