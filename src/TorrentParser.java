import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import com.google.gson.Gson;


public class TorrentParser {


    public static void parseTorrent(Object decodedBencode) {

        Map<String, Object> torrent = (Map<String, Object>) decodedBencode;
        String tracker_URL = (String) torrent.get("announce");
        Long length;

        Object infoValue = torrent.get("info");
        Map<String, Object> info = (Map<String, Object>) infoValue;


        length = (Long) info.get("length");

        System.out.println("Tracker URL: " + tracker_URL);
        System.out.println("Length: " + String.valueOf(length));
    }

}