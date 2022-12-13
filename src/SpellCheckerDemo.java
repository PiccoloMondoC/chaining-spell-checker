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


import java.io.*;
import java.util.*;


// Class declaration
public class SpellCheckerDemo{

// main method
public static void main(String args[]) {

    String fileName;

    // Create spellchecker object    
    SpellChecker demoSpeller = new SpellChecker();

    // Instantiatiate the dictionary file
    demoSpeller.setDictionaryFileName("words.txt");
    fileName = demoSpeller.getDictionaryFileName();

    List<String> correctWords = demoSpeller.setIncorrectWords("demo.txt");
    
    System.out.println("");
    // Print correct words
    for (String eachWord : demoSpeller.getCorrectWords()) {
        System.out.println("Correct: " + eachWord);
    }
    System.out.println("");

    List<String> incorrectWords = demoSpeller.setIncorrectWords("demo.txt");
    
    for(String eachWord : incorrectWords){
        Set<String> suggSet = demoSpeller.getSuggestions(eachWord);

        // Print incorrect words and suggestions to replace them
        System.out.println("Incorrect: " + eachWord);
        System.out.println("Suggestions: ");
        System.out.println(suggSet + "\n");
    }
}
}
