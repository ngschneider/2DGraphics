import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.awt.Color;

public class main {

    public static void main( String[]arg ){

        try{

            
            BufferedImage img = ImageIO.read(new File("TREE.jpg"));
            GrayScale gs = new GrayScale(img);

           // ImageIO.write(gs.redGrayScale(), "PNG", new File("RedOut.png"));
          //  ImageIO.write(gs.greenGrayScale(), "PNG", new File("GreenOut.png"));
            ImageIO.write(gs.testScale(), "PNG", new File("AVERAGEOut.png"));



        }catch(Exception e){
            e.printStackTrace();
        }

    }

    

}