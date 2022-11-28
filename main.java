import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class main {

    public static void main( String[]arg ){

        try{
            BufferedImage img2 = ImageIO.read(new File("./images/segull.jpg"));
            imageSegmentation seg = new imageSegmentation(img2);
            seg.clusterAlgorithm();
            /* 
            
            Convolution conv = new Convolution(new Convolution().blur,img2);
            Convolution conv1 = new Convolution(new Convolution().emboss,img2);
            Convolution conv2 = new Convolution(new Convolution().edge,img2);
            Convolution conv22 = new Convolution(new Convolution().edge2,img2);
            Convolution conv23 = new Convolution(new Convolution().edge3,img2);
            Convolution conv3 = new Convolution(new Convolution().sharpen,img2);
            Convolution conv4 = new Convolution(new Convolution().subtract,img2);
            ImageIO.write(conv.Convolute(), "PNG", new File("./images/Convolution/tigerBlur.jpg"));
            ImageIO.write(conv1.Convolute(), "PNG", new File("./images/Convolution/tigerEmboss.jpg"));
            ImageIO.write(conv2.Convolute(), "PNG", new File("./images/Convolution/tigerEdge1.jpg"));
            ImageIO.write(conv22.Convolute(), "PNG", new File("./images/Convolution/tigerEdge2.jpg"));
            ImageIO.write(conv23.Convolute(), "PNG", new File("./images/Convolution/tigerEdge3.jpg"));
            ImageIO.write(conv3.Convolute(), "PNG", new File("./images/Convolution/tigerSharpen.jpg"));
            ImageIO.write(conv4.Convolute(), "PNG", new File("./images/Convolution/tigerSubtract.jpg"));
    


            BufferedImage img = ImageIO.read(new File("./images/valve.png"));
            CrannyEdge cranny = new CrannyEdge(img);
        
            ImageIO.write(cranny.grayScaleImage, "PNG", new File("./images/cranny2/crannyGrayscale.jpg"));
            ImageIO.write(cranny.gaussianBlurImage, "PNG", new File("./images/cranny2/crannyGaussian.jpg"));
            ImageIO.write(cranny.sobelX, "PNG", new File("./images/cranny2/crannySobelX.jpg"));
            ImageIO.write(cranny.sobelY, "PNG", new File("./images/cranny2/crannysobelY.jpg"));
            ImageIO.write(cranny.magnitudeImage, "PNG", new File("./images/cranny2/crannyMagnitude.jpg"));
            */
        
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    

}