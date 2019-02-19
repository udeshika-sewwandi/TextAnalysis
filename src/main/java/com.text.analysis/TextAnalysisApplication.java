package com.text.analysis;

import com.text.analysis.preprocess.CustomIsolatedWordSpellChecker;
import com.text.analysis.preprocess.CustomLemmatizer;
import com.text.analysis.preprocess.CustomStemmer;
import com.text.analysis.preprocess.CustomTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import opennlp.tools.tokenize.SimpleTokenizer;

public class TextAnalysisApplication {

  public static void main(String[] args) throws Exception {

    //Tokenizing the text
    CustomTokenizer simpleTokenizer = new CustomTokenizer();

    StringBuilder tokenizedStudentFeedback =
        simpleTokenizer.tokenize("src/main/resources/student_feedback_data.txt");
    simpleTokenizer.writeTokenizedStringToFile(
        "src/main/resources/tokenized/tokenized_student_feedback_data.txt",
        tokenizedStudentFeedback.toString());

    StringBuilder tokenizedTwitterData =
        simpleTokenizer.tokenize("src/main/resources/twitter_data.txt");
    simpleTokenizer.writeTokenizedStringToFile(
        "src/main/resources/tokenized/tokenized_twitter_data.txt", tokenizedTwitterData.toString());

    StringBuilder tokenizedResearchPaper =
        simpleTokenizer.tokenize("src/main/resources/research_paper.txt");
    simpleTokenizer.writeTokenizedStringToFile(
        "src/main/resources/tokenized/tokenized_research_paper.txt",
        tokenizedResearchPaper.toString());

    //Isolated spell checking of the tokens
    CustomIsolatedWordSpellChecker customSpellChecker = new CustomIsolatedWordSpellChecker();

    BufferedReader reader =
        new BufferedReader(
            new FileReader("src/main/resources/tokenized/tokenized_student_feedback_data.txt"));
    String line;

    BufferedWriter writer =
        new BufferedWriter(
            new FileWriter(
                "src/main/resources/spell_corrected/spell_corrected_student_feedback_data.txt"));

    while ((line = reader.readLine()) != null) {
      StringBuilder stringBuilder = customSpellChecker.spellCheck(line.toLowerCase());
      writer.write(stringBuilder.toString());
    }
    writer.close();

    reader =
        new BufferedReader(
            new FileReader("src/main/resources/tokenized/tokenized_twitter_data.txt"));

    writer =
        new BufferedWriter(
            new FileWriter("src/main/resources/spell_corrected/spell_corrected_twitter_data.txt"));

    while ((line = reader.readLine()) != null) {
      StringBuilder stringBuilder = customSpellChecker.spellCheck(line.toLowerCase());
      writer.write(stringBuilder.toString());
    }
    writer.close();

    reader =
        new BufferedReader(
            new FileReader("src/main/resources/tokenized/tokenized_research_paper.txt"));

    writer =
        new BufferedWriter(
            new FileWriter(
                "src/main/resources/spell_corrected/spell_corrected_research_paper_data.txt"));

    while ((line = reader.readLine()) != null) {
      StringBuilder stringBuilder = customSpellChecker.spellCheck(line.toLowerCase());
      writer.write(stringBuilder.toString());
    }
    writer.close();

    //Stemming the tokens
    CustomStemmer simpleStemmer = new CustomStemmer();
    StringBuilder stemmedTokens =
        simpleStemmer.stemToken("src/main/resources/tokenized/tokenized_student_feedback_data.txt");
    simpleStemmer.writeStemmedStringToFile(
        "src/main/resources/stemmed_tokens/stemmed_student_feedback_data.txt",
        stemmedTokens.toString());

    stemmedTokens =
        simpleStemmer.stemToken("src/main/resources/tokenized/tokenized_twitter_data.txt");
    simpleStemmer.writeStemmedStringToFile(
        "src/main/resources/stemmed_tokens/stemmed_twitter_data.txt",
        stemmedTokens.toString());

    stemmedTokens =
        simpleStemmer.stemToken("src/main/resources/tokenized/tokenized_research_paper.txt");
    simpleStemmer.writeStemmedStringToFile(
        "src/main/resources/stemmed_tokens/stemmed_research_paper.txt",
        stemmedTokens.toString());

    //Lemmatizing the tokens
    CustomLemmatizer customLemmatizer = new CustomLemmatizer();
    StringBuilder lemmatizedTokens = customLemmatizer.lemmatizeToken("src/main/resources/tokenized/tokenized_student_feedback_data.txt");
    customLemmatizer.writeLemmatizedStringToFile("src/main/resources/lemmatized_tokens/lemmatized_student_feedback_data.txt", lemmatizedTokens.toString());
  }
}
