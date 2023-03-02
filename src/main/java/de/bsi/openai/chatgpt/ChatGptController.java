package de.bsi.openai.chatgpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.io.FileWriter;


import com.fasterxml.jackson.databind.ObjectMapper;

import de.bsi.openai.FormInputDTO;
import de.bsi.openai.OpenAiApiClient;
import de.bsi.openai.OpenAiApiClient.OpenAiService;

@Controller
public class ChatGptController {
	
	private static final String MAIN_PAGE = "index";
	
	@Autowired private ObjectMapper jsonMapper;
	@Autowired private OpenAiApiClient client;

	
	
	private String chatWithGpt3(String message) throws Exception {
		
		
		var completion = CompletionRequest.defaultWith(message);
		var postBodyJson = jsonMapper.writeValueAsString(completion);
	
		var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiService.GPT_3);
		
		var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);
		
		return completionResponse.firstAnswer().get();
	}
	
	
	
	@GetMapping(path = "/")
	public String index() {
		return MAIN_PAGE;
	}
	
	@PostMapping(path = "/")
	public String chat(Model model, @ModelAttribute FormInputDTO dto) throws Exception{
		
		FileWriter pw = new FileWriter("src/main/java/de/bsi/openai/chatgpt/data.csv",true);  
// feed in your array (or convert your data to an array) 
		String[]data = {dto.prompt(),chatWithGpt3(dto.prompt()).replaceAll("\r", "").replaceAll("\n", "")};
		pw.append(data[0]);
		pw.append(";");
		pw.append(data[1]+"\n");

		pw.close();
		try {
				
			model.addAttribute("request", data[0]);
			model.addAttribute("response", data[1]);
		
			
		} catch (Exception e) {
			model.addAttribute("response", "Error in communication with OpenAI ChatGPT API.");
		}
		return MAIN_PAGE;
	}

	
	
	
}
