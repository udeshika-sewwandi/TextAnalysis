# TextAnalysis

1. Clone the project
Go to the directory where project located and run git clone https://github.com/udeshika-sewwandi/TextAnalysis.git

2. To compile the project
Go to the directory where project located and run mvn clean instal

3. To execute the project
run the command mvn exec:java -Dexec.mainClass="com.text.analysis.TextAnalysisApplication"

Alternatively
1. Go to the project in a preferred IDE
2. Run the main method in TextAnalysisApplication class

# The output is located in resources folder of the project
1. Tokenized output contains in resources/tokenized folder
2. Lemmatized output contains in resources/lemmatized_tokens folder
3. Stemmed output contains in resources/stemmed_tokens folder
4. Isolated word spell correction output contains in resources/spell-corrected folder
5. Context sensitive spell correction output contains in resources/context-sensitive_spell-corrected folder