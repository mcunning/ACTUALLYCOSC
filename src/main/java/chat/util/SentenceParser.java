package main.java.chat.util;

import java.util.Random;

import main.java.chat.component.Keyword;
import main.java.chat.component.Response;

public class SentenceParser {

	// Class Instance Variables
	//public boolean isQuestion, atEnd, atStart, contains = false;

	//Default Constructor
	public SentenceParser(){
		
	}
	
	
	// User Input Parser
	public String[] userInputParser(String userInput){
		//Method Instances
		String[] parsedUserInput;
		String delimsPuctuation = "[ |,.?/;:{}\\-\\!\\)\\(]";
		
		// Parse out punctuation
		parsedUserInput = userInput.toLowerCase().split(delimsPuctuation, 0);
		
		return parsedUserInput;	
	}//userInputParser
	
	
	//compareKeyWordToInput
	public Keyword compareKeywordToInput(Keyword theKey,String[] theStringArray){
		//Instances
		Keyword result = null;
		
		// This is done by using each keyword key's enumerated MatchType
		switch (theKey.getSentenceMatch()) 
		{
		case ENDS_WITH:
			//for each element of Keyword theKey's keyword[] string array,-
			for(String s: theKey.getKeywords()){
				//-, if that keyword element matches the last entry of "theStringArray" (i.e. the parsed user input array)
				if(theStringArray[theStringArray.length-1].matches(s)){
					//Set theKey (as the appropriate keyword) as the returned Keyword variable
					result=theKey;
					break;
				}//if
			}//forS
			
		case STARTS_WITH:
			//for each element of Keyword theKey's keyword[] string array,-
			for(String s: theKey.getKeywords()){
				//-, if that keyword element matches the first entry of "theStringArray" (i.e. the parsed user input array)
				if(theStringArray[0].matches(s)){
					//Set theKey (as the appropriate keyword) as the returned Keyword variable
					result=theKey;
					break;
				}//if
			}//forS

		case CONTAINS: 	
			search://search break label
			//for each element of Keyword theKey's keyword[] string array,-
			for(String s: theKey.getKeywords()){
				//- and for each string element in "theStringArray" (i.e., the input string array)
				for(String r : theStringArray){
					if(s.equals(r)){
						//Set Keyword result to this keyword
						result=theKey;
						break search;
						
					}//if
				}//forR
			}//forS
		
		//default
		default:
			//Keyword not given MatchType
			System.out.println("Keyword not given MatchType");
		}//switch
		
		return result;		
	}//compareKeywordToInput
	
	
	//compareKeywordResponsesToInput
	public String compareKeywordResponsesToInput(Response theResponse,String[] theStringArray){
		//Instances
		String finalResponse=null;
		Random randgen=new Random();
		
		//Now dealing with the individual responses of the matched keyword, we can match the contained string[] keywords for further identity
		searchResponse:
		for(String k: theResponse.getKeywords()){
			for(int s=0;s<theStringArray.length;s++){
				if(k.matches(theStringArray[s])){
					int temp=randgen.nextInt(theResponse.getResponses().length-1);
					finalResponse=theResponse.getResponses()[temp];
					break searchResponse;
				}
			}//s
		}//k
		
		//conditional check to ensure 'finalResonse'!=null. This sets a random resopnse in case of no secondary keyword hit.
		if(finalResponse==null){
			//randomly generated number (appropriate to response[]) used to set 'finalResponse' string to the 'temp'th element of responses
			int temp=randgen.nextInt(theResponse.getResponses().length-1);
			finalResponse=theResponse.getResponses()[temp];
		}
		
		//end
		return finalResponse;
	}
	
	
//	// inputParser (V.2)
//	public String inputParser2(String keyword, String userInput) {
//		// Instances
//		String[] parsedUserInput, parsedUserInputSymbols;
//		String delimsPuctuation = "[ |,.?/;:{}\\-\\!\\)\\(]";
//		String delimsAlphabet = "[ qwertyuiopasdfghjklzxcvbnm]+";
//		// Parsers:
//		// Parse out punctuation
//		parsedUserInput = userInput.toLowerCase().split(delimsPuctuation, 0);
//		// Parse out letters
//		parsedUserInputSymbols = userInput.toLowerCase().split(delimsAlphabet,
//				0);
//
//		// Word Properties/Positions:
//		// isQuestion
//		isQuestion = userInput.contains("?");
	//**
//		// atStart
//		if (parsedUserInput[0].matches(keyword)) {
//			atStart = true;
//		}
//		// atEnd
//		if (parsedUserInput[parsedUserInput.length - 1].matches(keyword)) {
//			atEnd = true;
//		}
//		// contains
//		for (String s : parsedUserInput) {
//			if (s.matches(keyword)) {
//				contains = true;
//			}
//		}
//
//		return null;
//	}


	// Supporting Methods

	// ArrayIterator
	private String ArrayIterator(String[] array) {
		String result = "";
		for (String s : array) {
			result += s + " \n";
		}
		return result;
	}// ArrayItereator

}// class
