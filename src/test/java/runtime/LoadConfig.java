package runtime;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class LoadConfig {
    private static final String propPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "properties" + File.separator + "config.properties";

    public static String load(String key) {
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = Files.newInputStream(Paths.get(propPath));
            prop.load(is);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return prop.getProperty(key);
    }
}
