import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Filter;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class Main {
    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        System.out.println(output);

        Parser p = new Parser();
        p.parseInput(output);
        p.printMap();
    }
}

