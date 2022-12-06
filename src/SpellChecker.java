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

        HashNode list = table[bucket]; // For traversing the list.
        while (list != null) {
            // If we find the key in this node, return true.
            if (list.key.equals(key)) {
                return true;
            }

            list = list.next;
        }

        // If we get to this point, we don't know that the key does not exist
        // in the table
        return false;
    }
   

    

    


    
/*
    public void displayTable() {
        for (int j = 0; j < table.length; j++)
			if(table[j]!=null)
			System.out.println(table[j].displayList());
    }  */
}
