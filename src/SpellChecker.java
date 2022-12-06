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

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SpellChecker {
    private HashNode[] table;
    private int size ;
    private static int tableSize;
    private static int numItemsInFile;
    private boolean containsKey = false;
 
    /* Constructor */
    public SpellChecker(int tableSize)
    {
        this.table = new HashNode[ nextPrime(tableSize) ];
        this.size = 0;
    }

    /** Accessor function to get max size of hash table */
    public static int getTableSize() {
        return tableSize;
    }

    /**
     * Accessor function to get number of items in the file
     * @return the number of items in the numItemsInFile field
     */
    public static int getNumOfItemsInFile() {
        return numItemsInFile;
    }


    /** Mutator function to setTableSize() 
     * @throws IOException
     */
    public static void setTableSize() throws IOException {
        tableSize = getNumOfItemsInFile(null);

        /* 
         * The number of buckets of the hash table should be about
         * twice the number of words in the dictionary 
         */
        tableSize *= 2;

        // Check if tableSize isPrime
        if (!isPrime(tableSize)) {
            tableSize = nextPrime( tableSize );
        }
    }




    /* Function to check if hash table is empty */
    public boolean isEmpty()
    {
        return size == 0;
    }
    /* Function to clear hash table */
    public void makeEmpty()
    {
        int l = table.length;
        table = new HashNode[l];
        size = 0;
    }
    /* Function to get size */
    public int getSize()
    {
        return size;
    }
    /* Function to insert an element */
    public void insert(String val)
    {
        size++;
        int pos = myhash(val);        
        HashNode nptr = new HashNode(val);
        HashNode start = table[pos];                
        if (table[pos] == null)
            table[pos] = nptr;            
        else
        {
            nptr.next = start;
            start.prev = nptr;
            table[pos] = nptr;
        }    
    }
    /* Function to remove an element */
    public void remove(String val)
    {
        try
        {
            int pos = myhash(val);    
            HashNode start = table[pos];
            HashNode end = start;
            if (start.data == val)
            {
                size--;
                if (start.next == null)
                {
                    table[pos] = null;
                    return;
                }                
                start = start.next;
                start.prev = null;
                table[pos] = start;
                return;
            }
 
            while (end.next != null && end.next.data != val)
                end = end.next;
            if (end.next == null)
            {
                System.out.println("\nElement not found\n");
                return;
            }
            size--;
            if (end.next.next == null)
            {
                end.next = null;
                return;
            }
            end.next.next.prev = end;
            end.next = end.next.next;
 
            table[pos] = start;
        }
        catch (Exception e)
        {
            System.out.println("\nElement not found\n");
        }
    }
    /* Function myhash */
    private int myhash(String key )
    {
        int hashVal = key.hashCode( );
        hashVal %= table.length;
        if (hashVal < 0)
            hashVal += table.length;
        return hashVal;
    }
    /* Function to generate next prime number >= n */
    static int nextPrime( int n )
    {
        if (n % 2 == 0)
            n++;
        for ( ; !isPrime( n ); n += 2);
 
        return n;
    }
    /* Function to check if given number is prime */
    static boolean isPrime( int n )
    {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }
    /* Function to print hash table */
    public void printHashTable ()
    {
        System.out.println();
        for (int i = 0; i < table.length; i++)
        {
            System.out.print ("Bucket " + i + ":  ");            
 
            HashNode start = table[i];
            while(start != null)
            {
                System.out.print(start.data +" ");
                start = start.next;
            }
            System.out.println();
        }
    }

    public static int getNumOfItemsInFile(String filename) throws IOException {
        // Declare a variable
        int numItemsInFile = 0;

        // Define the filename
        File file = new File(filename);

        // Create an object for the Scanner class
        Scanner myfile = new Scanner(file);

        // Read the file
        while(myfile.hasNext()) {
            // Read the file
            myfile.nextLine();
            // Increment the total
            numItemsInFile++;
        }
        // Close the file
        myfile.close();
        return numItemsInFile;

        // Return the value
        //return items;
    }

    public boolean containsKey(String key) {
        containsKey = false;
        int bucket = myhash(key); // In what location should the key be?

        HashNode data = table[bucket]; // For traversing the list.
        while (data != null) {
            // If we find the key in this node, return true.
            if (HashNode.data.equals(key)) {
                return true;
            } else {
                return false;
            }            
        }
        // If we get to this point, we don't know that the key does not exist
        // in the table
        //data = data.next;
        return containsKey;
    }
   

    

    


    
/*
    public void displayTable() {
        for (int j = 0; j < table.length; j++)
			if(table[j]!=null)
			System.out.println(table[j].displayList());
    }  */
}
