package main.java.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.java.chat.component.Keyword;
import main.java.chat.util.SentenceParser;
import main.java.chat.util.ReadABook;

public class ActuallyResponder implements Responder {
	
	//Class Instances
	private static List<Keyword> keywords;
	Random randgen=new Random();

	//Default Constructor
	public ActuallyResponder() {
	
	}

	@Override
	public void readConfigFile() {

		ReadABook.fileReader("src\\main\\java\\chat\\input\\input.txt");

        ActuallyResponder.keywords = new ArrayList<Keyword>();
        ActuallyResponder.keywords.addAll(ReadABook.fileReader("src\\main\\java\\chat\\input\\input.txt"));

	}

	@Override
	public String respond(String inputSentence) {
		// Method Instance Variables
		SentenceParser parser = new SentenceParser();
		String[] arrayParsedInput;
		boolean done=false;
		Keyword match=null;
		String responseMatch=null;

		//(1) First Step: Parse inputSentence into local arrayParsedInput[] String array.
		arrayParsedInput = parser.userInputParser(inputSentence);


		//(2) Second Step: Use SentenceParser.compareKeywordToInput() method to compare each -
		// - Keyword from "keywords" list against the parsed user input "arrayParsedInput".  
		while(!done){
			//Using the SentenceParser.compareKeywordToInput() method to return the directly matching keyword.
			//We use a for iterator to hand the method each Keyword k in "keywords" list.
			for(int k=0;k<keywords.size();k++){ 											
				//Assign Keyword "match" to either null or the correct keyword.
				match=parser.compareKeywordToInput(keywords.get(k), arrayParsedInput);
				
				//If match is NOT null, break the "keywords" for loop. This is the exit conditional.
				if(match!=null){
					break;
				}else if(k==keywords.size()-1){
					//If no keywords have been identified (i.e: match==null) by this last iteration, it is about to break the "keywords" for loop. 
					//So, we need to assign "match" to something before it leaves. A random keywords keyword element is assigned to "match".
//					//!@#randgen
//					int tempNum=randgen.nextInt(keywords.size()-1);
//					match=keywords.get(tempNum); 
					//non-randomly-selected
					match=keywords.get(0);
				}
			}//k
			
			//Now, with the appropriate keyword in hand ("match"), we use the parser's SentenceParser.compareKeywordResponseToInput() method. 
			//So, for each of the Responses listed in match's "response[]" Response array, as soon as one is hit, -
			//- that string is set to the "resoponseMatch" string.
			for(int r=0;r<match.getResponses().size();r++){
				responseMatch=parser.compareKeywordResponsesToInput(match.getResponses().get(r), arrayParsedInput);
				
				//If "responseMatch" is NOT null, break the response for loop. This is the exit conditional.
				if(responseMatch!=null){
					//Now, with a value for responseMatch, set "done"=true to stop the whole ActuallyResponder.respond() method.
					done=true;
					break;
				}else if(r==match.getResponses().size()-1){
					//If no "responseMatch" strings have been identified (i.e: responseMatch==null), it is set to a random response[] string.
//					//!@#randgen
//					int tempNum=randgen.nextInt(match.getResponses().size()-1);
//					responseMatch=match.getResponses().get(tempNum).toString();
					//non-randomly-selected
					responseMatch=match.getResponses().get(0).toString();
					
					//Now, with a value for responseMatch, set "done"=true to stop the whole ActuallyResponder.respond() method.
					done=true;
				}
			}//r
			
		//By this point, the "responseMatch" string now has a value(i.e: "the computers response").
			
		}//!done
		
		//Return the computer's response in String format.
		return responseMatch;
		
	}// respond

}// class
