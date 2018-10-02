package Tour.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertyManager {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadApplicationProperties();
    }

    private static void loadApplicationProperties() {
        try {
            PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }


//    private static final Properties PROPERTIES;
//
//    static {
//        Path resourcesFilePath = Paths.get("resources", "application");
//        PROPERTIES = new Properties();
//        try {
//            PROPERTIES.load(new FileReader(resourcesFilePath.toFile()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String get(String key) {
//        return PROPERTIES.getProperty(key);
//    }
}
