package main.java.chat;

import java.util.List;
import java.util.Random;

import main.java.chat.component.Keyword;
import main.java.chat.component.Response;
import main.java.chat.util.SentenceParser;
import main.java.chat.util.ReadABook;

public class ActuallyResponder implements Responder {
	private static List<Keyword> keywords;

	public ActuallyResponder() {
		// TODO
	}

	@Override
	public void readConfigFile() {
		ReadABook.fileReader("src\\main\\java\\chat\\input\\input.txt");
	}

	@Override
	public String respond(String inputSentence) {
		// Method Instance Variables
		//1
		SentenceParser parser = new SentenceParser();
		String[] arrayParsedInput;
		//2
		boolean done=false;
		Keyword match=null;
		String responseMatch=null;

		// First Step: Parse inputSentence into local arrayParsedInput string array.
		arrayParsedInput = parser.userInputParser(inputSentence);


		//Second Step : User parser's 'compare..' method to compare each Keyword from keyword list against the parsed user input (arrayParsedInput).  
		while(!done){
			
			//Using the SentenceParser.compareKeywordToInput() method to return the directly matching keyword,-
			//- hand it each Keyword k in keywords
			
			for(int k=0;k<keywords.size();k++){ 											
				//assign 'match' to either null or the correct keyword
				match=parser.compareKeywordToInput(keywords.get(k), arrayParsedInput);
				
				//if match is non-null, break the keyword for. This is the exit conditional.
				if(match!=null){
					break;
				}else if(k==keywords.size()-1){
					//this is where no keywords have been identified, and it is about to break the keyword for. 
					//Need to assign 'match' something, so first keywords keyword element.
					match=keywords.get(0);
				}
			}//k
			
			//Now, with the appropriate keyword in hand ("match"), we again use the parser's 'compare response' method, - 
			//- So for each of the Responses listed in match's response[], as soon as one is hit, it's string is set to the resoponseMatch string.
			for(int r=0;r<match.getResponses().size();r++){
				responseMatch=parser.compareKeywordResponsesToInput(match.getResponses().get(r), arrayParsedInput);
				
				//if responseMatch is non-null, break the response for. This is the exit conditional.
				if(responseMatch!=null){
					done=true; //this is the overall "resopnd()" method termination boolean
					break;
				}else if(r==match.getResponses().size()-1){
					//if responseMatch is unset by end of method
					Random randgen=new Random();
					int temp=randgen.nextInt(match.getResponses().size()-1);
					responseMatch=match.getResponses().get(temp).toString();
					//done=true
					done=true;
				}
			}//r
			
		//By this point, the responseMatch String now has a value(i.e: "the computers response").
			
		}//!done
		
		//return the computer's string response.
		return responseMatch;
		
	}// respond

}// class
