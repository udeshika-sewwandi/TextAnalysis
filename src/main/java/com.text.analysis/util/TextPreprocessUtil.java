package com.text.analysis.util;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;
import java.util.ArrayList;
import java.util.List;

public class TextPreprocessUtil {

  public List<String> getSentences(String input){
    List<String> sentenceList = new ArrayList<>();
    // creates a StanfordCoreNLP object
//    Properties props = new Property();
//    props.put("annotators", "tokenize, ssplit");
//    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//    // create an empty Annotation just with the given text
//    Annotation document = new Annotation(input);
//    // run all Annotators on this text
//    pipeline.annotate(document);
//    // these are all the sentences in this document
//    // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
//    List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
//    for(CoreMap sentence:sentences){
//      List<CoreLabel> labels = sentence.get(CoreAnnotations.TokensAnnotation.class);
//      String originalString = edu.stanford.nlp.ling.Sentence.listToOriginalTextString(labels);
//      sentenceList.add(originalString);
//    }
//    StanfordCoreNLP pipeline = new StanfordCoreNLP(
//        PropertiesUtils.asProperties(
//            "annotators", "tokenize,ssplit,pos,lemma,parse,natlog",
//            "ssplit.isOneSentence", "true",
//            "parse.model", "src/main/resources/models/englishSR.ser.gz",
//            "tokenize.language", "en"));
//
//    Annotation document = new Annotation(input);
//// run all Annotators on this text
//    pipeline.annotate(document);
//    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
//    for(CoreMap sentence:sentences){
//      List<CoreLabel> labels = sentence.get(CoreAnnotations.TokensAnnotation.class);
//      String originalString = edu.stanford.nlp.ling.SentenceUtils.listToOriginalTextString(labels);
//      sentenceList.add(originalString);
//    }
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
