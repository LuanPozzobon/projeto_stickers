import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        API api = API.TOP_LANGUAGES;
        
        String url = api.getUrl();
        ContentExtractor extractor = api.getExtractor();

        ClientHttp client = new ClientHttp();
        String body = client.getApiBody(url);

        // Exibir dados p/ usuário
        List<Content> contents = extractor.extractContents(body);
        
        StickerGenerator generator = new StickerGenerator();

        File directory = new File("../output");
        directory.mkdir();

        for (int i = 0; i < 10; ++i) {
            switch(i){
                case 0:
                    System.out.printf("\uD83E\uDD47 ");
                    break;
                case 1:
                    System.out.printf("\uD83E\uDD48 ");
                    break;
                case 2:
                    System.out.printf("\uD83E\uDD49 ");
                    break;
                default:
                    System.out.printf("\uD83C\uDFC5 ");
            }
            Content content = contents.get(i);

            InputStream inputStream = new URL(content.imageUrl()).openStream();
            String fileName = directory.getAbsolutePath() + "\\" + (i + 1) + " - " + content.title() + ".png";
            generator.createSticker(inputStream, fileName);
            System.out.println(content.title());
        } 
    } 
}
