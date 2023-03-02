package de.bsi.openai.chatgpt;


import java.io.IOException;


public record CompletionRequest(String model, String prompt, 
		double temperature, int max_tokens) {
	
	public static CompletionRequest defaultWith(String prompt) throws IOException {
		

		return new CompletionRequest("text-davinci-003", prompt, 0.7, 200);
	}
	
}