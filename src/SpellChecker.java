/**
 * A java class to define a spell checking class using a hash table.
 * The program will: read a set of words W, from a words.txt file
 * and store them in a hash table; implement a spellCheck function
 * that performs a spell check on a string s, from demo.txt file,
 * using the words in the hash table as the dictionary. Return
 * an iterable collection of correctly spelled words, or if not in the
 * hash table, return a list of words in W that could be a correct
 * spelling of s. The program will also handle all the ways that s might
 * be a mispelling of a word in W, including swapping adjacent characters,
 * inserting a single character between two adjacent characters, omitting
 * a single character, replacing a character with another character, and
 * possibly phonetic substitutions.
 * 
 * Author Emmanuel Agbakpe
 * Course COSC 2436
 * Assignment Project 2 Part I
 * Instructor Professor Steve Johnson
 * Due Date December 10, 2022
 * 
 * 
 * Data Files
 * ========== 
 * dictionary => words.txt
 * words/sentence to check => demo.txt
 * 
 * User can replace these files with new files with the same name.
 * 
 * 
 * Code
 * ====
 * SpellChecker.java
 * SpellCheckerDemo.java
 * 
 */

 // Import java classes
import java.io.*;
import java.util.*;

// Class declaration
public class SpellChecker {
    
    private Set<String> dictionary = new HashSet<>(); // To hold words
    private String dictionaryFileName; // Full path of the words file
    private String userFile; // Full path of the user input file to check
    private List<String> correctWords; // Words spelled correctly
    private List<String> inCorrectWords; // Misspelled words
    
    // No argument constructor
    public SpellChecker(){}

    /**
     * The getDictionaryFileName method returns the value stroed
     * in the dictionaryFileName
     * @return the value stored in the dictionaryFileName field.
     */    
    public String getDictionaryFileName() {
        return dictionaryFileName;
    }


    /**
     * Mutator function to set the full path of the words dictionbary file.
     * @param fileName The full path of the dictionary words file
     */
    public void setDictionaryFileName(String fileName) {
        File dictionaryFileName = new File(fileName);
        
        try{
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String currentLine = reader.readLine();
                
                while(currentLine!=null){
                    // Remove punctuation and split words by blank space
                    String linePunctuationRemoved = currentLine.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    String [] wordsOnLine = linePunctuationRemoved.split("\\s+");
                    
                    //adds each word from dictionary file to dictionary set
                    for(String eachWord : wordsOnLine){
                        dictionary.add(eachWord);
                    }
                    
                    currentLine = reader.readLine();
                }
            }
        }
        
        catch(Exception excep){
            excep.printStackTrace();
        }
    }


    /**
     * The getCorrectWords method returns the list of all the
     * correct words in the correctWords field
     * @return the list of correct words in the correctWords field.
     */
    public List<String> getCorrectWords() {
        return correctWords;
    }

 
    
    /**
     * The setInCorrectWords method finds words in input file that are
     * not in the dictionary, adds them to the incorrectWords list, and
     * creates a list of suggestions for each.
     * @param userFileName
     */
    public List<String> setIncorrectWords(String userFileName){
        File userFile = new File(userFileName);
        
        // List of all misspelled words
        List<String> incorrectWords = new ArrayList<>();

        // List of all correctly spelled words
        correctWords = new ArrayList<>();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFileName));
            String currentLine = reader.readLine();
            
            while(currentLine!=null){
                if(!currentLine.equals("")){
                    String [] wordsOnLine = currentLine.split("\\s+");
                    
                    for(String curWord : wordsOnLine){
                        
                        // Removes punctuation of word
                        String wordPunctuationRemoved = curWord.replaceAll("[^a-zA-Z]", "").toLowerCase();
                        
                        // If not in dictionary, it is added to misspelled words list
                        if(false == dictionary.contains(wordPunctuationRemoved)){
                            incorrectWords.add(wordPunctuationRemoved);
                        } else {
                            correctWords.add(wordPunctuationRemoved);
                        }
                    }
                }
                
                currentLine = reader.readLine();
            }
        }
        
        catch(Exception excep){
            excep.printStackTrace();
        }
        
        // Returns a list of all misspelled words
        return incorrectWords;
    }


    /**
     * The getSuggestions creates a set of set of suggested words
     * for each misspelled word.
     * @return The set of words in the suggestionSet field
     */    
    public Set<String> getSuggestions(String word){
        Set<String> suggestionSet = new HashSet<>(); //set of all suggested words
        
        String potentialWord = "";
        
        // Loop to remove a character
        for(int i=0; i<word.length(); i++){
            potentialWord = word.toLowerCase().substring(0,i) + word.toLowerCase().substring(i+1,word.length());
            
            // If word with removed character is in dictionary, add it to suggested
            if(dictionary.contains(potentialWord)){
                
                suggestionSet.add(potentialWord);
            }
        }
        
        // Test for adding a character
        
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        
        for(int i=0; i<word.length(); i++) {

            for(char charac : alphabet) {
                
                potentialWord = word.toLowerCase().substring(0,i) + charac + word.toLowerCase().substring(i,word.length());
                
                // If word with added character is in dictionary, add it to suggested
                if(dictionary.contains(potentialWord)){
                    
                    suggestionSet.add(potentialWord);
                }
                
                // Addresses the special case of adding letter at the end of the word
                if(i==word.length()-1){
                    
                    potentialWord = word + charac;
                    
                    if(dictionary.contains(potentialWord)){
                        suggestionSet.add(potentialWord);
                    }
                }
            }
        }
        
        // Test for swapping characters        
        for(int i=1; i<word.length(); i++){
            
            // If at any index other than the last
            if(i!=word.length()-1){
                
                // Swaps two adjacent characters in word
                potentialWord = word.toLowerCase().substring(0,i-1) +word.toLowerCase().substring(i,i+1) + word.toLowerCase().substring(i-1,i) + word.toLowerCase().substring(i+1,word.length());
            }
            
            // Addresses special case of last index
            else{
                
                potentialWord = word.toLowerCase().substring(0,i-1) +word.toLowerCase().substring(i,i+1) + word.toLowerCase().substring(i-1,i);
            }
            
            // If word with swapped characters is in dictionary, add it to suggested
            if(dictionary.contains(potentialWord)){
                suggestionSet.add(potentialWord);
            }
        }
        
        // Returns the suggested set of words for each misspelled word
        return suggestionSet;
    }
}
