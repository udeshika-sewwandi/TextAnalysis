# TextAnalysis

1. Clone the project
Go to the directory where project located and run git clone https://github.com/udeshika-sewwandi/TextAnalysis.git

2. To compile the project
Go to the directory where project located and run mvn clean instal

3. To execute the project
run the command mvn exec:java -Dexec.mainClass="com.text.analysis.TextAnalysisApplication"

Alternatively to build and execute the project
1. Go to the project in a preferred IDE
2. Run the main method in TextAnalysisApplication class

A subscription key for Bing spell checker API used from a 7 days trial version. If it is expired please get a new key for
Bing spell checker API and add it as an environment variable as AZURE_BING_SAMPLES_API_KEY
Following are already obtained keys
Key 1: bcb3605303054b2996979978701a8cf6
Key 2: 36d0c40b9e4a4003a772c2458e27c2ba

# The output is located in resources folder of the project
1. Tokenized output contains in resources/tokenized folder
2. Lemmatized output contains in resources/lemmatized_tokens folder
3. Stemmed output contains in resources/stemmed_tokens folder
4. Isolated word spell correction output contains in resources/spell-corrected folder
5. Context sensitive spell correction output contains in resources/context-sensitive_spell-corrected folder