package com.text.analysis.preprocess;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import java.util.List;

public class SimpleIsolatedWordSpellChecker {


  private static LexicalizedParser lp;
  private String model;

  public SimpleIsolatedWordSpellChecker() {
    model ="src/main/resources/englishPCFG.ser.gz";
    lp = LexicalizedParser.loadModel(model);
  }

  public int spelling(String filePath) {
    int result = 0;

    DocumentPreprocessor dp = new DocumentPreprocessor(filePath);

    for (List<? extends HasWord> list : dp) {
      for (HasWord w : list) {
        if (! lp.getLexicon().isKnown(w.word().toLowerCase())) {
          System.out.format("misspelled: %s\n", w.word());

          result++;
        }
      }
    }

    return result;
  }
}
