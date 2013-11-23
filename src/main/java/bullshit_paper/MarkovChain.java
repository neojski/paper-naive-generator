package bullshit_paper;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

public class MarkovChain {
	private HashMap<String, LinkedList<String> > graph;
	
	MarkovChain(){
		graph = new HashMap<>();
	}
	
	public void build(List<String> sentences){
		
		for(String s: sentences){
			List<String> cur = Parser.parseSentence(s);
			cur.add(".");
			
			for(int i=-1; i<cur.size()-1; ++i){
				LinkedList<String> tmp;
				String from;
				
				if(i == -1){
					from = "#";
					tmp = graph.get(from);
				}
				else{
					from = cur.get(i);
					tmp = graph.get(from);
				}
				
				if(tmp == null){
					tmp = new LinkedList<String>();
				}
				
				tmp.add(cur.get(i+1));
				graph.put(from, tmp);
			}	
		}
		
		for(Entry<String, LinkedList<String>> entry: graph.entrySet()){
			LinkedList<String> tmp = entry.getValue();
			Collections.shuffle(tmp);
			graph.put(entry.getKey(),tmp);
		}
		
	}
	
	public String generateRandomText(int length){
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		
		for(int i=0; i<length; ++i){
			String cur = "#";
			
			while(cur != "."){
				LinkedList<String> list = graph.get(cur);
				int size = list.size();
				cur = list.get(random.nextInt(size));
				
				if(cur != "."){
					builder.append(" "+cur);
				}
			}
			
			builder.append(".");
		}

		return builder.toString();
	}

}
