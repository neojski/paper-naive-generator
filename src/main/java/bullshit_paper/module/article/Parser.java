package bullshit_paper.module.article;

import java.text.BreakIterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Parser {
	static BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(new Locale("pl","PL"));
	static BreakIterator wordIterator = BreakIterator.getWordInstance(new Locale("pl","PL"));
	
	public static List<String> parseText(String text){
		List<String> sentences = new LinkedList<String>();
	    sentenceIterator.setText(text);
	    
	    int start = sentenceIterator.first();
	    int end = sentenceIterator.next();

	    while (end != BreakIterator.DONE) {
	        String sentence = text.substring(start, end);
	        if (Character.isLetterOrDigit(sentence.charAt(0))) {
	        	if(sentence.endsWith(".")) sentence += ' ';
	            sentences.add(sentence);
	        }
	        
	        start = end;
	        end = sentenceIterator.next();
	    }
		return sentences;
	}
	
	public static List<String> parseSentence(String sentence){
		List<String> words = new LinkedList<String>();
	    wordIterator.setText(sentence);
	    int start = wordIterator.first();
	    int end = wordIterator.next();

	    while (end != BreakIterator.DONE) {
	        String word = sentence.substring(start,end);
	        if (Character.isLetterOrDigit(word.charAt(0))) {
	        	words.add(word);
	        }
	        
	        start = end;
	        end = wordIterator.next();
	    }
	    
		return words;
	}
	
}
