package com.jack.task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloader {
  public static void main(String[] args) {
    String imageUrl = "https://www.faegredrinker.com/-/media/images/professionals/a/matthewamodeo.jpg";
    String destinationFile = "image.jpg";
    
    try {
      URL url = new URL(imageUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestProperty("User-Agent", "Mozilla/5.0");
      InputStream is = connection.getInputStream();
      FileOutputStream os = new FileOutputStream(".//files/imgs/"+destinationFile);
    
      byte[] b = new byte[2048];
      int length;
    
      while ((length = is.read(b)) != -1) {
        os.write(b, 0, length);
      }
    
      is.close();
      os.close();
    } catch (IOException e) {
      System.out.println("Error downloading image: " + e.getMessage());
    }
  }
}
