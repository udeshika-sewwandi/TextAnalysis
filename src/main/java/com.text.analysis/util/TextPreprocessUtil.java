package com.text.analysis.util;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;
import java.util.ArrayList;
import java.util.List;

public class TextPreprocessUtil {

  public List<String> getSentences(String input){
    List<String> sentenceList = new ArrayList<>();
    DocumentPreprocessor dp = new DocumentPreprocessor(input);
    for (List<HasWord> sentence : dp) {
      StringBuilder stringBuilder = new StringBuilder();
      for (HasWord word : sentence) {

          stringBuilder.append(word).append(" ");
      }
      sentenceList.add(stringBuilder.toString());
    }

    return sentenceList;
  }
}
