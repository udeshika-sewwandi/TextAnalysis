package com.text.analysis.preprocess;

import java.io.File;
import java.io.IOException;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.JaroWinklerDistance;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class CustomIsolatedWordSpellChecker {

  private Directory directory;
  private SpellChecker spellChecker;
  private float accuracy;

  public CustomIsolatedWordSpellChecker() throws IOException {
    directory = FSDirectory.open(new File("src/main/resources/test"));
    spellChecker = new SpellChecker(directory);
    spellChecker.indexDictionary(
        new PlainTextDictionary(new File("src/main/resources/dictionary.txt")),
        new IndexWriterConfig(Version.LUCENE_36, null),
        false);
    spellChecker.setStringDistance(new JaroWinklerDistance());
  }

  public StringBuilder spellCheck(String wordForSuggestions) throws IOException {
    int suggestionsNumber = 5;
    StringBuilder stringBuilder = new StringBuilder();

    if(!spellChecker.exist(wordForSuggestions)) {
      String[] suggestions = spellChecker.
          suggestSimilar(wordForSuggestions, suggestionsNumber);

      if (suggestions!=null && suggestions.length>0) {
        stringBuilder.append("Suggestions for word " +wordForSuggestions + ": ");
        for (int i = 0 ; i < suggestions.length; i++) {
          if(i == suggestions.length -1) {
            stringBuilder.append(suggestions[i]);
          } else {
            stringBuilder.append(suggestions[i] + ", ");
          }
        }
        stringBuilder.append("\n");
      }
      else {
        stringBuilder.append("No suggestions found for word:"+wordForSuggestions + "\n");
      }
    } else {
      stringBuilder.append(wordForSuggestions + " is a correct word\n");
    }

    setAccuracy(spellChecker.getAccuracy());

    return stringBuilder;
  }

  public float getAccuracy() {
    return accuracy;
  }

  public void setAccuracy(float accuracy) {
    this.accuracy = accuracy;
  }
}
