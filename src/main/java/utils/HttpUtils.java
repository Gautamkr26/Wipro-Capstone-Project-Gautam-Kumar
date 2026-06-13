package utils;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    
    public static boolean isBroken(String link) {
        if (link == null || link.isEmpty()) return true;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(link).openConnection();
            conn.setRequestMethod("HEAD"); 
            conn.setConnectTimeout(3000); 
            conn.connect();
            return conn.getResponseCode() >= 400;
        } catch (Exception e) { 
            return true; 
        }
    }

    // --- Backward Compatibility ---
    public static boolean isLinkBroken(String link) {
        return isBroken(link);
    }
}