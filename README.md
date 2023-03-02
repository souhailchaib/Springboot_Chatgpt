# ChatGPT API used with Java & Spring

## How to get it running 
* Clone this GIT project.
* Make sure it is a Maven project and Maven is executed to load dependencies.
* Store the key in application.properties file in cloned project.
* Start it as Spring Boot application.
* For chatting with ChatGPT: http://localhost:8080/


*******Appelez l'API GPT-3 avec Java HttpClient******

L'API GPT-3 peut être utilisée avec n'importe quel langage de programmation ou client HTTP. Je vais vous montrer comment cela fonctionne avec HttpClient de Java .

Chaîne postBody = 
    """
    {
        " prompt": "Code en Java pour imprimer les 20 premiers nombres de Fibonacci",
	"max_tokens": 300,
	"modèle": "texte-davinci-003",
	"température": 0,7
    }
    """ ;



var requête = HttpRequest.newBuilder()
        .uri(URI.create("https://api.openai.com/v1/completions"))
	.header("Type de contenu", "application/json")
	.header("Autorisation", "Porteur " + OPENAI_API_KEY)
	.POST(BodyPublishers.ofString(postBody))
	.construire();
		
var client = HttpClient.newHttpClient();
var resp = client.send(request,HttpResponse.BodyHandlers.ofString());
System.out.println(resp.body());





Nous définissons une chaîne postBody au format JSON selon la documentation de l'API OpenAI. Dans le prompt, cette chaîne contient le message ou ici la tâche de programmation que nous envoyons à l'API du chatbot. 

HttpClient.newHttpClient() crée l'instance Java HTTP Client pour envoyer une requête HTTP.


Nous construisons le HttpRequest réel avec un constructeur. Ici, je n'utilise que les attributs les plus nécessaires du HttpRequest :
L'adresse internet avec uri
L'en-tête Content-Type spécifie que nous transférons un texte JSON.
L'en-tête d'autorisation transmet notre clé API en tant que jeton porteur.
Le corps de la requête POST composé de notre chaîne JSON.
Avec la méthode send , nous envoyons la requête POST et spécifions que le corps de la réponse est lu comme une chaîne. La réponse est alors un JSON, que nous écrivons dans la console :


{
  "id": "cmpl-6aVIuAySr6W83S0xvbCgq2RUNc3hQ",
  "object": "text_completion",
  "créé": 1674158160,
  "modèle": "texte-davinci-003",
  "les choix": [
    {
      "text": "classe publique Fibonacci {...",
      "index": 0,
      "logprobes": nul,
      "finish_reason": "arrêter"
    }
  ],
  "utiliser": {
    "prompt_tokens": 11,
    "completion_tokens": 131,
    "total_tokens": 142
  }
}

La réponse de l'intelligence artificielle se trouve dans l' attribut text de la liste de choix . J'ai raccourci le code Java pour la séquence de Fibonacci ici - essayez-le vous-même pour le voir en entier.


**Le fichier CSV**

le fichier data.csv se trouve dans dir : src\main\java\de\bsi\openai\chatgpt\data.csv

le résultat du fichier est comme le suivant : 


Question;Answer
who is the last king of morocco?;The last king of Morocco was King Mohammed VI, who ascended to the throne in 1999.
who is the last king of morocco?;The current King of Morocco is King Mohammed VI. He took the throne on July 23, 1999, upon the death of his father, King Hassan II.
who is the last king of morocco?;The current King of Morocco is King Mohammed VI, who ascended to the throne in 1999.
how are you doing ?;I'm doing well, thank you. And you?


***Test et containerisation***

En raison du manque de temps et d'un problème de docker, je n'ai pas pu conteneuriser l'application. sinon je pourrais la conteneuriser dans les prochains jours.
 

**********Conclusion********

  ChatGPT ou l'API GPT-3 est une intelligence artificielle impressionnante. C'est vraiment amusant à utiliser. Malheureusement, chaque appel d'API coûte - c'est pourquoi les attributs de jeton sont également définis dans la demande et la réponse pour rendre les coûts exacts transparents. Mais il y a un capital d'amorçage gratuit à essayer.


