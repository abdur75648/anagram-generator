# Anagram-Generator
A Java program that prints out all valid anagrams of an input string present in the a vocabulary

![image](https://user-images.githubusercontent.com/66300465/167696211-ff2140a0-0f95-4fda-ae2c-1cfb10bdff47.png)

## Description
We are given a vocabulary V of (lowercase) English words. It uses letters of the English alphabet [a-z], digits [0-9], and the apostrophe symbol [']. No other characters are used in the vocabulary V. Our goal is to print out all valid anagrams of an input string present in the vocabulary. The input string will be a sequence of at most 12 characters.

 In this implementation, we will load V (Vocabulary) from the text file and then compute anagrams. We'll provide an input file also in the text format. In both vocabulary and input files there should be one string written per line. Our goal will be to compute all valid anagrams (i.e., each word within your anagram must be present in V) of all input strings After computing all valid anagrams of one string we'll output a ‘-1’ to indicate that we are done computing anagrams of this string.

**Commands to run the code:**
```
 javac Anagram.java
 java Anagram vocabulary.txt input.txt
```

 - Anagram - Two strings are anagrams of each other if by rearranging letters of one string you can obtain the other. For example, “a bit” is an anagram of “bait”, and “super” is an anagram of “purse”. Note that we can add spaces at will, i.e., we won’t count spaces when matching characters for checking anagrams. However, we'll only compute anagrams with a maximum of 2 spaces in them (i.e., three words at most). Please note that each permutation of these words will also be a valid anagram.

 ## Note
 - Anything has not been imported from *java.utils*. Do NOT copy any part of the code at all anywhere
 - The code has been written completely by me and I have its copyright ownership
 - This code is a solution of assignment given in a IITD Course, COL106 ([Course Webpage](https://www.cse.iitd.ac.in/~parags/teaching/col106)). I've uploaded it here, only for being used as a help by those who're not registered in the course, if they get stuck somewhere.
