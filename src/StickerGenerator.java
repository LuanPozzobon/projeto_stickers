import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {
    public void createSticker(InputStream inputStream, String fileName) throws Exception{

        // Ler imagem
        BufferedImage originalImage = ImageIO.read(inputStream);

        // Criar nova imagem modificada
        int width = originalImage.getWidth();
        int heigth = originalImage.getHeight();
        int newHeigth =  (int) (heigth + heigth*0.2);
        BufferedImage newImage = new BufferedImage(width, newHeigth, BufferedImage.TRANSLUCENT);

        // Copiar imagem original para nova imagem
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // Configurar a fonte
        int posX = (int) (width*0.25);
        int posY = heigth + (int) ((newHeigth - heigth)*0.7);
        Font fonte = new Font("Impact", Font.PLAIN, (int) (heigth*0.12));
        graphics.setColor(Color.CYAN);
        graphics.setFont(fonte);

        // Escrever na nova imagem
        graphics.drawString("PAPO10", posX, posY);
        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        TextLayout textLayout = new TextLayout("PAPO10", fonte, fontRenderContext);

        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();

        transform.translate(posX, posY);
        graphics.setTransform(transform);
        BasicStroke outlineStroke = new BasicStroke(width * 0.004f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        // Escrever nova imagem em arquivo
        ImageIO.write(newImage, "png", new File(fileName));

    }    
}