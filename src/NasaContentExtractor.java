import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor{
    public List<Content> extractContents(String body){
        // Separar os dados importantes (Título / Poster / Classificação)
        JsonParser parser = new NasaJsonParser();
        List<Map<String, String>> atributeList = parser.parse(body);

        return atributeList.stream()
            .map(atributes -> new Content(atributes.get("title"), atributes.get("url")))
            .toList();
    }
}
