import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception{
        //Leitura da Imagem
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();

        //InputStream inputStream = new FileInputStream("entrada/filme.jpg");

        //BufferedImage imageOriginal = ImageIO.read(new File("entrada/filme.jpg"));
        BufferedImage imageOriginal = ImageIO.read(inputStream);

        //cria nova imagem em memória, com transparência e com o tamanho novo
        int largura = imageOriginal.getWidth();      
        int altura  = imageOriginal.getHeight();      
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original para a nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imageOriginal, 0, 0, null);

        //configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);


        //escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 100, novaAltura - 80);


        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/"+nomeArquivo));

    }


}
