package SpaceTravel.Utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    public static Properties load(String filename) {

        Properties properties = new Properties();

        try {
            properties.load(PropertyUtil.class.getClassLoader().getResourceAsStream(filename));
        } catch (IOException e) {
            throw new RuntimeException("Could not read " + filename + " resource file: " + e);
        }

            return properties;
    }
}
