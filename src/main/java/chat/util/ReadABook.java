package main.java.chat.util;
import main.java.chat.component.Keyword;
import main.java.chat.component.Keyword.*;
import main.java.chat.component.Response;
import main.java.chat.component.Response.*;

import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;

//
public class ReadABook {
	
	//main method for reading the file and parsing each line
	public static List<Keyword> fileReader(String filename){
		//will be the list which will contain each keyword object extracted from the file
		List<Keyword> keys = new ArrayList<Keyword>();
		
		//will contain the initial split up of the line with the first element being keyword
		//specific and each following element pertaining to responses
		ArrayList<String> subStrings = new ArrayList<String>();
		
		//used to store the split up response
		ArrayList<String> subResponses = new ArrayList<String>();
		
		//used to create a responses template to be added to the Keyword
		ArrayList<Response> responsesTemplate = new ArrayList<Response>();
		
		//used to convert strings to enumerated types
		MatchType matchType;
		MatchType matchType2;
		KeywordType keyType;
		QuestionFlag qFlag;
		
		//set the delimiters for splitting up each line according to the predefined syntax
		String responseDelims = "[R]";
		String subresponseDelims = "\\\\";
		String ORDelims = "[|]";
		String subKeyDelims = "#";
		
		//This will contain each line of the read file
		String KeywordLine;
		
		String[] ss = {"hi"};
		
		System.out.println(new Response(null, QuestionFlag.QUESTION_ONLY, ss, 0));
		
		
		try{
			//open the file called filename
			BufferedReader br = new BufferedReader(new FileReader(filename));

			//read the file line by line
			while ((KeywordLine = br.readLine()) != null) {
				
				
				StringTokenizer st = new StringTokenizer(KeywordLine, responseDelims);
				
				//initial split up of line
				while (st.hasMoreTokens()) {
			    	 subStrings.add(st.nextToken());
			         //System.out.println(st.nextToken());
			    }
				
				//Parse the initial keyword section
				String[] keywordSection = subStrings.get(0).split(subKeyDelims);
				/*System.out.println("Keyword Section:");
				for(String s: keywordSection){
					System.out.println(s);
				}*/
				
				//covert strings to enums
				
				//Sentence match
				if(keywordSection[2].toLowerCase().equals("starts-with")){
					matchType = MatchType.STARTS_WITH;
				}else if (keywordSection[2].toLowerCase().equals("exact")){
					matchType = MatchType.EXACT;
				}else if (keywordSection[2].toLowerCase().equals("ends-with")){
					matchType = MatchType.ENDS_WITH;
				}else {
					matchType = MatchType.CONTAINS;
				}
				
				//word match
				if(keywordSection[3].toLowerCase().equals("starts-with")){
					matchType2 = MatchType.STARTS_WITH;
				}else if (keywordSection[3].toLowerCase().equals("exact")){
					matchType2 = MatchType.EXACT;
				}else if (keywordSection[3].toLowerCase().equals("ends-with")){
					matchType2 = MatchType.ENDS_WITH;
				}else {
					matchType2 = MatchType.CONTAINS;
				}
				
				//type
				if(keywordSection[1].toLowerCase().equals("word")){
					keyType = KeywordType.WORD;
				}else {
					keyType = KeywordType.PHRASE;
				}
				
				//Parse the main keywords out of the main keyword section
			    String[] mainKeywords = keywordSection[0].split(ORDelims);
			    /*System.out.println("Main kewords:");
				for(String s: mainKeywords){
					System.out.println(s);
				}*/
				
				//deal with each response **WARNING THERE IS AN ERROR HERE
			    for(int i = 1; i < subStrings.size(); i++){
					//split up the specific response
					StringTokenizer st2 = new StringTokenizer(subStrings.get(i), subresponseDelims);
					
					//add the split response to the list
					while (st2.hasMoreTokens()) {
						subResponses.add(st2.nextToken());
				    }
					
					//if there is a variable at the beginning do, else
					if((subResponses.size() == 3 && i < subStrings.size()-1) || (subResponses.size() == 4 && i == subStrings.size()-1)){
					    //split keywords
						String[] responseKeywords = subResponses.get(0).split(ORDelims);
						//split responses
					    String[] responses = subResponses.get(2).split(ORDelims);
					    
					    //convert string to enum types
						if(subResponses.get(1).toLowerCase().equals("no") || subResponses.get(1).toLowerCase().equals("statement-only")){
							qFlag = QuestionFlag.STATEMENT_ONLY;
						}else if(subResponses.get(1).toLowerCase().equals("yes") || subResponses.get(1).toLowerCase().equals("question-only")){
							qFlag = QuestionFlag.QUESTION_ONLY;
						}else{
							qFlag = QuestionFlag.EITHER_QUESTION_OR_STATEMENT;
						}
						
						//create response
						responsesTemplate.add(new Response(responseKeywords, qFlag, responses, 0));
						
					}else {
						//split responses
					    String[] responses = subResponses.get(1).split(ORDelims);
					    
					    //convert string to enums
						if(subResponses.get(0).toLowerCase().equals("no") || subResponses.get(1).toLowerCase().equals("statement-only")){
							qFlag = QuestionFlag.STATEMENT_ONLY;
						}else if(subResponses.get(0).toLowerCase().equals("yes") || subResponses.get(1).toLowerCase().equals("question-only")){
							qFlag = QuestionFlag.QUESTION_ONLY;
						}else{
							qFlag = QuestionFlag.EITHER_QUESTION_OR_STATEMENT;
						}
					    
						//create response
						responsesTemplate.add(new Response(null, qFlag, responses, 0));
					}
					subResponses.clear();
			    }

				//for(Response r : responsesTemplate)
					//System.out.println(r);
			    
			   //something is wrong with this add
			    keys.add(new Keyword(mainKeywords, keyType, matchType, matchType2, responsesTemplate, Integer.parseInt(keywordSection[4])) );
			    responsesTemplate.clear();
				subStrings.clear();
				
				for(Keyword k : keys)
					System.out.println(k);
			    
			}
			br.close();
		}
		catch(Exception e){
			System.out.println("Error reading file " + e.getMessage());
			System.exit(1);
			}
		return keys;
	}
	

}
