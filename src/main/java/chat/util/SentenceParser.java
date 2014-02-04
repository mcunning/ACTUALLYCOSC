package main.java.chat.util;

public class SentenceParser {

	// Class Instance Variables
	public boolean isQuestion, atEnd, atStart, contains = false;

	// inputParser (V.2)
	public String inputParser(String keyword, String userInput) {
		// Instances
		String[] parsedUserInput, parsedUserInputSymbols;
		String delimsPuctuation = "[ |,.?/;:{}\\-\\!\\)\\(]";
		String delimsAlphabet = "[ qwertyuiopasdfghjklzxcvbnm]+";
		// Parsers:
		// Parse out punctuation
		parsedUserInput = userInput.toLowerCase().split(delimsPuctuation, 0);
		// Parse out letters
		parsedUserInputSymbols = userInput.toLowerCase().split(delimsAlphabet,
				0);

		// Word Properties/Positions:
		// isQuestion
		isQuestion = userInput.contains("?");
		// atStart
		if (parsedUserInput[0].matches(keyword)) {
			atStart = true;
		}
		// atEnd
		if (parsedUserInput[parsedUserInput.length - 1].matches(keyword)) {
			atEnd = true;
		}
		// contains
		for (String s : parsedUserInput) {
			if (s.matches(keyword)) {
				contains = true;
			}
		}

		
		//!@# Call to Responder.respond()
		
		
		return null;
	}


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
