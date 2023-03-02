package de.bsi.openai.chatgpt;

import java.util.List;
import java.util.Optional;
 
import java.io.IOException;

public record CompletionResponse(Usage usage, List<Choice> choices) {
    
    public Optional<String> firstAnswer() throws IOException {
        
        if (choices == null || choices.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(choices.get(0).text());
    }
    
    

    record Usage(int total_tokens, int prompt_tokens, int completion_tokens) {}
    
    public record Choice(String text) {}
}
