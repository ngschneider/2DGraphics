import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class main {

    public static void main( String[]arg ){

        try{

            BufferedImage img = ImageIO.read(new File("./images/tsa.jpg"));
            BufferedImage img2 = ImageIO.read(new File("./images/animal.png"));
          
           Float edge[][] = {
            {-1f,-1f,-1f},
            {-1f,8f,-1f},
            {-1f,-1f,-1f}
           };
           Float blur[][] = {
            {1/9f,1/9f,1/9f},
            {1/9f,1/9f,1/9f},
            {1/9f,1/9f,1/9f}
           };
           Float gaussianBlur[][] = {
            {1/16f,2/16f,1/16f},
            {2/16f,4/16f,2/16f},
            {1/16f,2/16f,1/16f}
           };
           Float sharpen[][] = {
            {0f,-1f,0f},
            {-1f,5f,-1f},
            {0f,-1f,0f}
           };
           Float edge2[][] = {
            {0f,1f,0f},
            {1f,4f,1f},
            {0f,1f,0f}
           };
           Float edge3[][] = {
            {0f,0f,0f},
            {-1f,1f,0f},
            {0f,0f,0f}
           };
           Float emboss[][] = {
            {-2f,-1f,0f},
            {-1f,1f,1f},
            {0f,1f,2f}
           };
           Float subtract[][] = {
            {-1/8f,-1/8f,-1/8f},
            {1/8f,1f,1/8f},
            {-1/8f,-1/8f,-1/8f}
           };
           Convolution convolution = new Convolution(subtract, img);
           
           Convolution convolution2= new Convolution(edge3, convolution.Convolute());
           
        
           
           ImageIO.write(convolution2.Convolute(), "PNG", new File("./images/segulltest2.jpg"));
            
           
          

           // ImageIO.write(gs.redGrayScale(), "PNG", new File("RedOut.png"));
            
          // ImageIO.write(convolution.Convolute(), "PNG", new File("./blurOut.png"));
          // BufferedImage img2 = ImageIO.read(new File("./animal.png"));
          // GrayScale gs2 = new GrayScale(img2);
         //  ImageIO.write(gs2.trueGrayScale(), "PNG", new File("./GreenOut.png"));
           

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    

}