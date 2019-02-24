package com.text.analysis;

import com.text.analysis.preprocess.CustomContextSensitiveSpellChecker;
import com.text.analysis.preprocess.CustomIsolatedWordSpellChecker;
import com.text.analysis.preprocess.CustomLemmatizer;
import com.text.analysis.preprocess.CustomStemmer;
import com.text.analysis.preprocess.CustomTokenizer;
import com.text.analysis.util.Constants;
import com.text.analysis.util.TextPreprocessUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class TextAnalysisApplication {

  public static void main(String[] args) throws Exception {

    try {
      //       Tokenizing the text
      CustomTokenizer simpleTokenizer = new CustomTokenizer();

      StringBuilder tokenizedStudentFeedback =
          simpleTokenizer.tokenize(Constants.STUDENT_FEEDBACK_DATA);
      simpleTokenizer.writeTokenizedStringToFile(
          Constants.TOKENIZED_STUDENT_FEEDBACK_DATA, tokenizedStudentFeedback.toString());

      StringBuilder tokenizedTwitterData = simpleTokenizer.tokenize(Constants.TWITTER_DATA);
      simpleTokenizer.writeTokenizedStringToFile(
          Constants.TOKENIZED_TWITTER_DATA, tokenizedTwitterData.toString());

      StringBuilder tokenizedResearchPaper =
          simpleTokenizer.tokenize(Constants.RESEARCH_PAPER_DATA);
      simpleTokenizer.writeTokenizedStringToFile(
          Constants.TOKENIZED_RESEARCH_PAPER_DATA, tokenizedResearchPaper.toString());

      // Stemming the tokens
      CustomStemmer simpleStemmer = new CustomStemmer();
      StringBuilder stemmedTokens =
          simpleStemmer.stemToken(Constants.TOKENIZED_STUDENT_FEEDBACK_DATA);
      simpleStemmer.writeStemmedStringToFile(
          Constants.STEMMED_STUDENT_FEEDBACK_DATA, stemmedTokens.toString());

      stemmedTokens = simpleStemmer.stemToken(Constants.TOKENIZED_TWITTER_DATA);
      simpleStemmer.writeStemmedStringToFile(
          Constants.STEMMED_TWITTER_DATA, stemmedTokens.toString());

      stemmedTokens = simpleStemmer.stemToken(Constants.TOKENIZED_RESEARCH_PAPER_DATA);
      simpleStemmer.writeStemmedStringToFile(
          Constants.STEMMED_RESEARCH_PAPER_DATA, stemmedTokens.toString());

      // Lemmatizing the tokens
      CustomLemmatizer customLemmatizer = new CustomLemmatizer();
      StringBuilder lemmatizedSFTokens =
          customLemmatizer.lemmatizeToken(Constants.TOKENIZED_STUDENT_FEEDBACK_DATA);

      customLemmatizer.writeLemmatizedStringToFile(
          Constants.LEMMATIZED_STUDENT_FEEDBACK_DATA, lemmatizedSFTokens.toString());

      StringBuilder lemmatizedTDTokens =
          customLemmatizer.lemmatizeToken(Constants.TOKENIZED_TWITTER_DATA);

      customLemmatizer.writeLemmatizedStringToFile(
          Constants.LEMMATIZED_TWITTER_DATA, lemmatizedTDTokens.toString());

      StringBuilder lemmatizedRDTokens =
          customLemmatizer.lemmatizeToken(Constants.TOKENIZED_RESEARCH_PAPER_DATA);

      customLemmatizer.writeLemmatizedStringToFile(
          Constants.LEMMATIZED_RESEARCH_PAPER_DATA, lemmatizedRDTokens.toString());

      // Isolated spell checking of the tokens
      CustomIsolatedWordSpellChecker customSpellChecker = new CustomIsolatedWordSpellChecker();
      String line;
      StringBuilder stringBuilder;

      BufferedReader reader =
          new BufferedReader(new FileReader(Constants.TOKENIZED_STUDENT_FEEDBACK_DATA));
      BufferedWriter writer =
          new BufferedWriter(new FileWriter(Constants.ISOLATED_SC_STUDENT_FEEDBACK_DATA));

      while ((line = reader.readLine()) != null) {
        stringBuilder = customSpellChecker.spellCheck(line.toLowerCase());
        writer.write(stringBuilder.toString());
      }

      reader = new BufferedReader(new FileReader(Constants.TOKENIZED_TWITTER_DATA));
      writer = new BufferedWriter(new FileWriter(Constants.ISOLATED_SC_TWITTER_DATA));


      while ((line = reader.readLine()) != null) {
        stringBuilder = customSpellChecker.spellCheck(line.toLowerCase());
        writer.write(stringBuilder.toString());
      }

      reader = new BufferedReader(new FileReader(Constants.TOKENIZED_RESEARCH_PAPER_DATA));
      writer = new BufferedWriter(new FileWriter(Constants.ISOLATED_SC_RESEARCH_PAPER_DATA));

      System.out.println("Isolated word spell correction for research text");
      while ((line = reader.readLine()) != null) {
        stringBuilder = customSpellChecker.spellCheck(line.toLowerCase());
        System.out.println(stringBuilder.toString());
        writer.write(stringBuilder.toString());
      }

      // Context sensitive spell correction
      TextPreprocessUtil util = new TextPreprocessUtil();
      CustomContextSensitiveSpellChecker customContextSensitiveSpellChecker =
          new CustomContextSensitiveSpellChecker();

      List<String> sentences = util.getSentences(Constants.STUDENT_FEEDBACK_DATA);

      StringBuilder studentFeedbackCSSpellCorrected =
          CustomContextSensitiveSpellChecker.check(sentences);

      customContextSensitiveSpellChecker.writeCSSpellCorrectedStringToFile(
          Constants.CONTEXT_SENSITIVE_SC_STUDENT_FEEDBACK_DATA,
          studentFeedbackCSSpellCorrected.toString());

      List<String> twitterSentences = util.getSentences(Constants.TWITTER_DATA);

      StringBuilder twitterCSSpellCorrected =
          CustomContextSensitiveSpellChecker.check(twitterSentences);

      customContextSensitiveSpellChecker.writeCSSpellCorrectedStringToFile(
          Constants.CONTEXT_SENSITIVE_SC_TWITTER_DATA, twitterCSSpellCorrected.toString());

      List<String> researchPaperSentences = util.getSentences(Constants.RESEARCH_PAPER_DATA);

      StringBuilder researchPaperCSSpellCorrected =
          CustomContextSensitiveSpellChecker.check(researchPaperSentences);
      customContextSensitiveSpellChecker.writeCSSpellCorrectedStringToFile(
          Constants.CONTEXT_SENSITIVE_SC_RESEARCH_PAPER_DATA,
          researchPaperCSSpellCorrected.toString());

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
