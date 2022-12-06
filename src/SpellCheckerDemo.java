/**
 * A java class to define a spell checking class using a hash table.
 * The program will: read a set of words W, from a words.txt file
 * and store them in a hash table; implement a spellCheck function
 * that performs a spell check on a string s, entered by user at the
 * console, using the words in the hash table as the dictionary. Return
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
 */

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class SpellCheckerDemo {
    //private static Object dictionary;
    private static boolean suggestWord;

    public static void main(String[] args) throws IOException {
        String dictionaryFileName = "words.txt"; // Dictionary file name 

        ChainingSpellChecker.getNumOfItemsInFile(dictionaryFileName);
        int numWords = ChainingSpellChecker.getNumOfItemsInFile(dictionaryFileName);
        System.out.println(
            "Number of words in " + dictionaryFileName + " is " + numWords
            );
        int tableSize = numWords;
        tableSize *= 2;
        // Check if maxSize isPrime
        if (!ChainingSpellChecker.isPrime(tableSize)) {
            tableSize = ChainingSpellChecker.nextPrime( tableSize );
        }

        Scanner sc = new Scanner(new File(dictionaryFileName));
		ChainingSpellChecker hashTable = new ChainingSpellChecker(tableSize);
		
		while(sc.hasNext()){
			String s = sc.nextLine();
			hashTable.insert(s);			
		}

        numWords = hashTable.getNumOfItemsInFile(dictionaryFileName);

        System.out.println("\nSpell Checker Demo\n\n");
        System.out.println(
            "Number of words in " + dictionaryFileName + " is:\t\t" + numWords
            );
        System.out.println(
            "The number of words currently in hash table is:\t" 
            + hashTable.getSize()
            );
        System.out.println(
            "The Capacity of the hash table is:\t\t" + tableSize
            );
        hashTable.printHashTable ();

               
		Scanner scanWord = new Scanner(System.in);
        // Prompt the user for text to check the spelling 
        System.out.print("\nType a sentence to check the spelling: ");

        
        
        String stringInput = scanWord.nextLine();
        
        System.out.print(
            "\nYou entered: " +stringInput 
            +"\n\nConverting to all lowercase and initiating spell check...\n\n"
            );
        stringInput = stringInput.toLowerCase();
        String[] splitInput = stringInput.split(" ");
        for (String word : splitInput) {
            if (hashTable.containsKey(word)) {
                System.out.println(word + " : correct\n");
            }else{
                System.out.println(word + " : incorrect\n");
//                printSuggestions(suggest(word));
            }
        }
	}
/*
    private static void printSuggestions(Object object) {
        Set<String> suggestions = new HashSet<>();
        Object word;
        Set<String> alternates = makeAlternates(word);
        for (String alternate : alternates) {
            if (hashTable.contains(alternate) {
                suggestions.add(alternate);
            }
        }
        return suggestions;
    }*/

    private static Object suggest(String word) {
        return null;
    }
}
