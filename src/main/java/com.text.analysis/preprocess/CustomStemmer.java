package com.text.analysis.preprocess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import opennlp.tools.stemmer.PorterStemmer;

public class CustomStemmer {

  public StringBuilder stemToken(String filePath) throws IOException {
    BufferedReader reader =
        new BufferedReader(
            new FileReader(filePath));
    String line;
    PorterStemmer stemmer = new PorterStemmer();
    StringBuilder stringBuilder = new StringBuilder();

    while ((line = reader.readLine()) != null) {
      stringBuilder.append(stemmer.stem(line)).append(System.lineSeparator());
    }

    return stringBuilder;
  }

  public void writeStemmedStringToFile(String filePath, String stemmedString) throws IOException{
    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
    writer.write(stemmedString);
    writer.close();
  }
}
