import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class main {

    public static void main( String[]arg ){

        try{

            
            ArrayList<ArrayList<Float>> blur = new ArrayList<ArrayList<Float>>();
            ArrayList<ArrayList<Float>> edge = new ArrayList<ArrayList<Float>>();
            ArrayList<ArrayList<Float>> sharpen = new ArrayList<ArrayList<Float>>();


            blur.add(0, new ArrayList<Float>());
            blur.add(1, new ArrayList<Float>());
            blur.add(2, new ArrayList<Float>());

            sharpen.add(0, new ArrayList<Float>());
            sharpen.add(1, new ArrayList<Float>());
            sharpen.add(2, new ArrayList<Float>());
        
            
            edge.add(0, new ArrayList<Float>());
            edge.add(1, new ArrayList<Float>());
            edge.add(2, new ArrayList<Float>());

            blur.get(0).add(0,1/9f); blur.get(1).add(0,1/9f); blur.get(2 ).add(0,1/9f);
            blur.get(0).add(1,1/9f); blur.get(1).add(1,1/9f); blur.get(2 ).add(1,-1/9f);
            blur.get(0).add(2,1/9f); blur.get(1).add(2,1/9f); blur.get(2 ).add(2,1/9f);

            edge.get(0).add(0,-1f); edge.get(1).add(0,-1f); edge.get(2 ).add(0,-1f);
            edge.get(0).add(1,-1f); edge.get(1).add(1,4f); edge.get(2 ).add(1,-1f);
            edge.get(0).add(2,-1f); edge.get(1).add(2,-1f); edge.get(2 ).add(2,-1f);

            sharpen.get(0).add(0,0f); sharpen.get(1).add(0,-1f); sharpen.get(2 ).add(0,0f);
            sharpen.get(0).add(1,-1f); sharpen.get(1).add(1,-1f); sharpen.get(2 ).add(1,-1f);
            sharpen.get(0).add(2,0f); sharpen.get(1).add(2,-1f); sharpen.get(2 ).add(2,0f);



            BufferedImage img = ImageIO.read(new File("./TREE.jpg"));
            
          // GrayScale gs = new GrayScale(img);
           Convolution convolution = new Convolution(sharpen, img);

            


           // ImageIO.write(gs.redGrayScale(), "PNG", new File("RedOut.png"));
          //  ImageIO.write(gs.greenGrayScale(), "PNG", new File("GreenOut.png"));
          // ImageIO.write(convolution.Convolute(), "PNG", new File("./blurOut.png"));
           BufferedImage img2 = ImageIO.read(new File("./animal.png"));
           Convolution convolution2 = new Convolution(edge, img);
           ImageIO.write(convolution.Convolute(), "PNG", new File("./Edge.png"));

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    

}