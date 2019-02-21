package com.text.analysis.preprocess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class CustomContextSensitiveSpellChecker {

  static String host = "https://api.cognitive.microsoft.com";
  static String path = "/bing/v7.0/spellcheck";
  static String key = System.getenv("AZURE_BING_SAMPLES_API_KEY");

  static String mkt = "en-US";
  static String mode = "proof";

  public static StringBuilder check (List<String> sentences) throws IOException, InterruptedException {

    String params = "?mkt=" + mkt + "&mode=" + mode;
    URL url = new URL(host + path + params);
    StringBuilder stringBuilder = new StringBuilder();

    for (String text : sentences) {
      HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      connection.setRequestProperty("Ocp-Apim-Subscription-Key", key);
      connection.setDoOutput(true);
      connection.setRequestProperty("Content-Length", "" + text.length() + 5);

      DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
      wr.writeBytes("text=" + text);
      wr.flush();
      wr.close();

      BufferedReader in = new BufferedReader(
          new InputStreamReader(connection.getInputStream()));

      String line;
      while ((line = in.readLine()) != null) {
        stringBuilder.append(text + " : " + line).append(System.lineSeparator()).append(System.lineSeparator());
      }
      in.close();
      connection.disconnect();
      Thread.sleep(1000);
    }

    return stringBuilder;
  }

  public void writeCSSpellCorrectedStringToFile(String filePath, String csSpellCorrectedString) throws IOException{
    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
    writer.write(csSpellCorrectedString);
    writer.close();
  }
}
