package com.text.analysis.preprocess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import opennlp.tools.lemmatizer.SimpleLemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

public class CustomLemmatizer {

  public StringBuilder lemmatizeToken(String filePath) throws IOException {
    BufferedReader reader =
        new BufferedReader(
            new FileReader(filePath));
    String line;
    StringBuilder stringBuilder = new StringBuilder();
    List<String> sentences = new ArrayList<>();
    String[] setenceArr = new String[sentences.size()];

    InputStream posInputStream = new FileInputStream("src/main/resources/models/en-pos-maxent.bin");
    InputStream lemmaInputStream = new FileInputStream("src/main/resources/models/en-lemmatizer.dict");
    POSModel model = new POSModel(posInputStream);
    POSTaggerME tagger = new POSTaggerME(model);

    while ((line = reader.readLine()) != null) {
      sentences.add(line);
    }

    String[] tags = tagger.tag(sentences.toArray(setenceArr));

    SimpleLemmatizer simpleLemmatizer = new SimpleLemmatizer(lemmaInputStream);

    for(int i = 0 ; i < sentences.size(); i++) {
      stringBuilder.append(simpleLemmatizer.lemmatize(sentences.get(i), tags[i])).append(System.lineSeparator());
    }

    return stringBuilder;
  }

  public void writeLemmatizedStringToFile(String filePath, String lemmatizedString) throws IOException{
    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
    writer.write(lemmatizedString);
    writer.close();
  }
}
