import java.io.*;
import java.util.*;
import java.util.regex.*;

public class FirstSpellCheckerDemo {
    //private static Object dictionary;
    private static boolean suggestWord;

    public static void main(String[] args) throws IOException {
        String dictionaryFileName = "words.txt"; // Dictionary file name 

        FirstChainingSpellChecker.getNumOfItemsInFile(dictionaryFileName);
        int numWords = FirstChainingSpellChecker.getNumOfItemsInFile(dictionaryFileName);
        System.out.println(
            "Number of words in " + dictionaryFileName + " is " + numWords
            );
        int tableSize = numWords;
        tableSize *= 2;
        // Check if maxSize isPrime
        if (!FirstChainingSpellChecker.isPrime(tableSize)) {
            tableSize = FirstChainingSpellChecker.nextPrime( tableSize );
        }

        Scanner sc = new Scanner(new File(dictionaryFileName));
		FirstChainingSpellChecker hashTable = new FirstChainingSpellChecker(tableSize);
		
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
