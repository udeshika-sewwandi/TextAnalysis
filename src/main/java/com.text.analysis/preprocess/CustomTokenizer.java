package com.text.analysis.preprocess;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CustomTokenizer {

  public StringBuilder tokenize(String filePath) throws FileNotFoundException {
    StringBuilder tokenizedString = new StringBuilder();

    PTBTokenizer<CoreLabel> ptbt =
        new PTBTokenizer<>(new FileReader(filePath), new CoreLabelTokenFactory(), "");
    while (ptbt.hasNext()) {
      CoreLabel label = ptbt.next();
      tokenizedString.append(label).append(System.lineSeparator());
    }

    return tokenizedString;
  }

  public void writeTokenizedStringToFile(String filePath, String tokenizedString) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
    writer.write(tokenizedString);
    writer.close();
  }
}
