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

//        try (InputStream fis = new FileInputStream(filename)) {
//            properties.load(fis);
//        } catch (IOException e) {
//            throw new RuntimeException("Could not read " + filename + " resource file: " + e);
//            return properties;
//        }

//        try (FileReader reader = new FileReader(filename)) {
//            properties.load(reader);
//        } catch (IOException e) {
//            throw new RuntimeException("Could not read " + filename + " resource file: " + e);
//        }

//        ResourceBundle rb = ResourceBundle.getBundle(filename);

            return properties;
    }
}
