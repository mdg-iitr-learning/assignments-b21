/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    public HashMap<Integer, String> wordList;



    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        wordList = new HashMap<>();
        String line;
        int a=1;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.put(a,word);
            a++;
        }
    }
    public String sortWord (String original ){
       String sorted = null;
       int n = original.length();
       char b[] = new char[n];
       for (int i = 0 ; i<n; i++){
           b[i] = original.charAt(i);
       }
        char t;
        for (int j=0; j<n-1; j++){
           for (int k=0; k<n-1-j; k++){
               if (b[k]>b[k+1]){
                   t = b[k];
                   b[k] = b[k+1];
                   b[k+1] = t;
               }
           }
       }
//           sorted = new String(b);
       sorted = String.valueOf(b);
       return sorted;
    }

    public boolean isGoodWord(String word, String base) {
        if (wordList.containsValue(word) && !word.contains(base)){
        return true;
        } else return false;
    }

    public HashMap<Integer, String> getAnagrams(String targetWord) {
        HashMap<Integer, String> result = new HashMap<>();
        int a = 1;
        String targetWordSorted = sortWord(targetWord);
        Log.d("Sorted", "Sorted target word: " + String.valueOf(sortWord(targetWord)));
        Log.d("WordList", "Size of wordList: " + String.valueOf(wordList.size()));
        for (int i =0; i<wordList.size(); i++){
            String dictSort = sortWord(wordList.get(i+1));
            if (dictSort.equals(targetWordSorted) && !wordList.get(i+1).equals(targetWord)){
                result.put(a,wordList.get(i+1));
                a++;
            }
        }

        Log.d("Anagram List", "Size of anagram list: " + String.valueOf(result.size()));
        return result;
    }

    public HashMap<Integer, String> getAnagramsWithOneMoreLetter(String word) {
        HashMap<Integer, String> result = new HashMap<>();
        int a = 1;
        int size = word.length();
        char letters[] = new char[size];
        for(int i=0; i<size; i++){
            letters[i] = word.charAt(i);
        }
        Log.d("Letters: ", "Letters: " + String.valueOf(letters));
        int decision = 0;
        for (int i =0; i<wordList.size(); i++){
            if (wordList.get(i+1).length() == word.length()+1){
                String dictSort = sortWord(wordList.get(i+1));
                HashSet<Character> DictLetters = new HashSet<>();
                for (int b = 0; b<wordList.get(i+1).length(); b++){
                    DictLetters.add(dictSort.charAt(b));
                }
                for (int c=0; c<size; c++){
                    if (DictLetters.contains(letters[c])) decision = 1;
                    else {decision = 0; break;}
                }
                if (decision == 1){
                    result.put(a,wordList.get(i+1));
                    a++;
                }
            }

        }
        return result;
    }
    public String pickGoodStarterWord() {
        int select = random.nextInt(wordList.size());
        while (getAnagramsWithOneMoreLetter(wordList.get(select)).size() < MIN_NUM_ANAGRAMS){
            select = random.nextInt(wordList.size());
        }
            return wordList.get(select);
    }
}
