package org.apache.tika;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.*;

public class StringMatcher {

	    public static void main(String [] args)
	    {
	        try {
	            BufferedReader br1 = new BufferedReader(new FileReader("Hallo wie geht es dir Robbery. Vielen Dank sehr gut."));
	            String[] words = {"Hallo","robbed", "robbers"};
	            String word_re = words[0];   
	            String str="";

	            for (int i = 1; i < words.length; i++)
	                word_re += "|" + words[i];
	            word_re = "[^.]*\\b(" + word_re + ")\\b[^.]*[.]";
	            while(br1.ready()) { str += br1.readLine(); }
	            Pattern re = Pattern.compile(word_re, 
	                    Pattern.MULTILINE | Pattern.COMMENTS | 
	                    Pattern.CASE_INSENSITIVE);
	            Matcher match = re.matcher(str);
	            String sentenceString="";
	            while (match.find())
	            {
	                sentenceString=match.group(0);
	                if (sentenceString.matches("(?=.*(?:rob(?:ery|ed)?))")) {
	                    System.out.println(sentenceString);
	                }
	            }
	        } catch (Exception e) {}
	    }

	}
