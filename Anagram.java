import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Anagram {

    private int currentSize, m,m2;
    private String hashTable[];

    public Anagram(int m){
        this.m = m;
        this.m2 = 241271; // Some other random prime number
        this.currentSize = 0;
        this.hashTable = new String[m];
    }

    public int getSize(){
        return this.currentSize;
    }

    public boolean isEmpty() {
        return this.getSize() == 0;
    }
    
    public boolean contains(String key) {
        int k = hash(key);
        int pointer = k;
        int h = 1;
        do {
            if (hashTable[pointer]==null) {
                return false;
            }
            else if (hashTable[pointer].equals(key)) {
                return true;
            }
            else {
                //i = (i + h * h++) % maxSize;
                pointer = (pointer + h*h) % this.m;
                h++; 
            }
        } while (pointer!=k);
        return false;
    }
 
    private int getPrimeKey(char s){
		final int[] primes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79,
								83, 89, 97, 101, 103,107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163};
		final String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789'";

		for (int i = 0; i < primes.length; i++) {
			if (s==alphabet.charAt(i)) {
				return primes[i];
			}
		}
		return 0;
	}

    private boolean isAnagram(String s1, String s2){
		int h1 = 1;
		char[] s_arr = s1.toCharArray();
		for (int i = 0; i < s_arr.length; i++) {
			h1 = h1 * getPrimeKey(s_arr[i]);
			h1 = h1 % this.m2;
		}
        int h2 = 1;
		s_arr = s2.toCharArray();
		for (int i = 0; i < s_arr.length; i++) {
			h2 = h2 * getPrimeKey(s_arr[i]);
			h2 = h2 % this.m2;
		}

        return h1==h2;
    }

	private int hash(String s) {
		int m = this.m;
		int hash_value = 1;
		char[] s_arr = s.toCharArray();

		for (int i = 0; i < s_arr.length; i++) {
			hash_value = hash_value * getPrimeKey(s_arr[i]);
			hash_value = hash_value % m;
		}
		return hash_value;
	}

    public void insert(String s){
        int k = hash(s);
        int pointer = k;
        int h = 1;

        do {
            if (hashTable[pointer]==null) {
                hashTable[pointer] = s;
                currentSize++;
                return;
            }
            else if (hashTable[pointer].equals(s)) {
                return;
            }
            else {
                //i = (i + h * h++) % maxSize;
                pointer = (pointer + h*h) % this.m;
                h++; 
            }
        } while (pointer!=k);
    }

    public void printAnagrams(String s){
        int k = hash(s);
        //System.out.println("Printing anagrams for " + s + " ( k = " + k + " ) : ");
        int pointer = k;
        int h = 1;

        do {
            if (hashTable[pointer]==null) {
                break;
            }
            else {
                    if (this.isAnagram(hashTable[pointer], s)) {
                        System.out.println(hashTable[pointer]);
                    }
                    pointer = (pointer + h*h) % this.m;
                    h++; 
            }
        } while (pointer!=k);
    }

    public String[] getSingleWordAnagrams(String s){
        GrowableArray all_anagrams = new GrowableArray();
        int k = hash(s);
        int pointer = k;
        int h = 1;
        int counter = 0;

        do {
            if (hashTable[pointer]==null) {
                break;
            }
            else {
                if (this.isAnagram(hashTable[pointer], s)) {
                    all_anagrams.insertKey(hashTable[pointer]);
                    counter+=1;
                }
                pointer = (pointer + h*h) % this.m;
                h++; 
            }
        } while (pointer!=k);

        String final_all_anagrams[] = new String[counter];
        for (int i = 0; i < final_all_anagrams.length; i++) {
            final_all_anagrams[i] = (String) all_anagrams.arr_elements[i];
        }
        return final_all_anagrams;
    }

    public void printAllAnagrams(String s){
        AllPossibleCombinations allPossibilitiesMaker = new AllPossibleCombinations(s);
        String[] AllPossibleCombinations = allPossibilitiesMaker.generate_all_possible_anagrams();

        AvlTree finalAnagrams = new AvlTree();
        String[] allSingleWordAnagrams1 = new String[0];
        String[] allSingleWordAnagrams2 = new String[0];
        String[] allSingleWordAnagrams3 = new String[0];

        for (int i = 0; i < AllPossibleCombinations.length; i++) {
            String[] splitted_anagram = AllPossibleCombinations[i].trim().split("\\s+");

            if (splitted_anagram.length==1) {
                String theAnagramPart1 = splitted_anagram[0];
                allSingleWordAnagrams1 = this.getSingleWordAnagrams(theAnagramPart1);
                for (String ana : allSingleWordAnagrams1) {
                    finalAnagrams.insertKey(ana);
                }
                
            }
            else if (splitted_anagram.length==2) {
                String theAnagramPart1 = splitted_anagram[0];
                String theAnagramPart2 = splitted_anagram[1];
                
                allSingleWordAnagrams1 = this.getSingleWordAnagrams(theAnagramPart1);
                allSingleWordAnagrams2 = this.getSingleWordAnagrams(theAnagramPart2);

                for (String ana1 : allSingleWordAnagrams1) {
                    for (String ana2 : allSingleWordAnagrams2) {
                        finalAnagrams.insertKey(ana1 + " " + ana2);
                    }
                }

            }
            else if (splitted_anagram.length==3) {
                String theAnagramPart1 = splitted_anagram[0];
                String theAnagramPart2 = splitted_anagram[1];
                String theAnagramPart3 = splitted_anagram[2];
                
                allSingleWordAnagrams1 = this.getSingleWordAnagrams(theAnagramPart1);
                allSingleWordAnagrams2 = this.getSingleWordAnagrams(theAnagramPart2);
                allSingleWordAnagrams3 = this.getSingleWordAnagrams(theAnagramPart3);

                for (String ana1 : allSingleWordAnagrams1) {
                    for (String ana2 : allSingleWordAnagrams2) {
                        for (String ana3 : allSingleWordAnagrams3) {
                            finalAnagrams.insertKey(ana1 + " " + ana2 + " " + ana3);
                        }
                    }
                }
            }
            else{
                System.out.println("NOT Possible");
            }
        }
        finalAnagrams.inorderTraversal();
    }

    
    
    public static void main(String[] args) {
        Anagram anagram = new Anagram(10000007);

        File vocab = new File(args[0]);
        Scanner read;
        try {
			read = new Scanner(vocab);
		} catch (FileNotFoundException e) {
			System.out.println("File " + args[0] + " NOT found!");
			return;
		}
		int t = read.nextInt();
		String s = read.nextLine(); // Move to next line
		for (int i = 1; i < t; i++) {
			s = read.nextLine(); // Read each line
			anagram.insert(s);
		}
        read.close();

        File input = new File(args[1]);
		try {
			read = new Scanner(input);
		} catch (FileNotFoundException e) {
			System.out.println("File " + args[1] + " NOT found!");
			return;
		}
		t = read.nextInt();
		read.nextLine(); // Move to next line
		for (int i = 0; i < t; i++) {
			s = read.nextLine(); // Read each line
			anagram.printAllAnagrams(s);
			System.out.println(-1);
		}
		read.close();
    }
    
    
    /*

    public static void main(String[] args) {
        Anagram anagram = new Anagram(10000007);
        String file = "new_vocab.txt";
        File vocab = new File(file);
        Scanner read;
        try {//,"i","h","ieh","ies","ash","ime","ieh","ameh"};
			read = new Scanner(vocab);
		} catch (FileNotFoundException e) {
			System.out.println("File " + file + " NOT found!");
			return;
		}
		int t = read.nextInt();
		String s = read.nextLine(); // Move to next line
		for (int i = 1; i < t; i++) {
			s = read.nextLine(); // Read each line
			anagram.insert(s);
		}
        read.close();

        String tests[] = {"lmaniowsi","rve"}; 
        for (String string : tests) {
            anagram.printAnagrams(string);
            System.out.println(-1);
        }
    }
    

    
    public static void main(String[] args) {
        Anagram anagram = new Anagram(10000007);
        String file = "new_vocab.txt";
        File vocab = new File(file);
        Scanner read;
        try {//,"i","h","ieh","ies","ash","ime","ieh","ameh"};
			read = new Scanner(vocab);
		} catch (FileNotFoundException e) {
			System.out.println("File " + file + " NOT found!");
			return;
		}
		int t = read.nextInt();
		String s = read.nextLine(); // Move to next line
		for (int i = 1; i < t; i++) {
			s = read.nextLine(); // Read each line
			anagram.insert(s);
		}
        read.close();

        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789'";
        char alphs[] = alphabet.toCharArray();
        for (char c : alphs) {
            anagram.printAnagrams(Character.toString(c));
            System.out.println("-1");
        }        
    }
    */
}
