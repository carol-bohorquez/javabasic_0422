package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String GenerateUniqueID() {
        String id = null;
        for (int i = 0; i < 50; i++) {
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
            id = ft.format(dNow);
        }
        return id;
    }
}
