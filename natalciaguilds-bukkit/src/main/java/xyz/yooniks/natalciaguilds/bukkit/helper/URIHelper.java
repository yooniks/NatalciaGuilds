package xyz.yooniks.natalciaguilds.bukkit.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

public final class URIHelper {

  private static final String MAIN_URL = "https://github.com/yooniks/NatalciaGuilds/%s";
  public static final String RELEASES_URL = String.format(MAIN_URL, "releases");
  public static final String VERSION_URL = String.format(MAIN_URL, "version.txt");


  private URIHelper() {
  }

  public static String readContent(URI uri) throws IOException {
    final StringBuilder content = new StringBuilder();

    String currentLine;

    final BufferedReader reader = new BufferedReader(
        new InputStreamReader(
            uri.toURL().openConnection().getInputStream()));

    while ((currentLine = reader.readLine()) != null) {
      content.append(currentLine);
    }

    reader.close();

    return content.toString();
  }

}
