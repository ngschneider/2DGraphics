import java.awt.image.BufferedImage;
import java.awt.Color;

// Methods for different Gray Scale methods
public class GrayScale {
    private BufferedImage img;

    public GrayScale(BufferedImage img){
        this.img = img;
    }

    // Red Gray Scale
    public BufferedImage redGrayScale(){
        BufferedImage imgCopy = this.img;
        for(int y = 0; y < imgCopy.getHeight(); y++ ){
            for(int x = 0; x < imgCopy.getWidth(); x++){

                int pixlInt = imgCopy.getRGB(x, y);
                Color pixelColor = new Color(pixlInt);
                int r = pixelColor.getRed();
                int g = pixelColor.getGreen();
                int b = pixelColor.getBlue();

                g = r;
                b = r;
                Color outColor = new Color(r,g,b);
                imgCopy.setRGB(x, y, outColor.getRGB());
            }
        }
        return imgCopy;
    }

    // Green Gray Scale
    public BufferedImage greenGrayScale(){
        BufferedImage imgCopy = this.img;
        for(int y = 0; y < imgCopy.getHeight(); y++ ){
            for(int x = 0; x < imgCopy.getWidth(); x++){

                int pixlInt = imgCopy.getRGB(x, y);
                Color pixelColor = new Color(pixlInt);
                int r = pixelColor.getRed();
                int g = pixelColor.getGreen();
                int b = pixelColor.getBlue();

                r = g;
                b = g;
                Color outColor = new Color(r,g,b);
                imgCopy.setRGB(x, y, outColor.getRGB());
            }
        }
        return imgCopy;
    }

   

    // Average RGB Gray Scale
    public BufferedImage averageGrayScale(){
        BufferedImage imgCopy = this.img;
        for(int y = 0; y < imgCopy.getHeight(); y++ ){
            for(int x = 0; x < imgCopy.getWidth(); x++){

                int pixlInt = imgCopy.getRGB(x, y);
                Color pixelColor = new Color(pixlInt);
                int r =  (int)(pixelColor.getRed() * .299);
                int g = (int)(pixelColor.getGreen() * .587);
                int b = (int)(pixelColor.getBlue() * .114);

               
          Color outColor = new Color(r,g,b);
                imgCopy.setRGB(x, y, outColor.getRGB());
            }
        }
        return imgCopy;
    }
    public BufferedImage binaryPhoto(){
        int averageAlpha = 0;
        int pixelNum = 0;
        BufferedImage imgCopy = this.img;
        for(int y = 0; y < imgCopy.getHeight(); y++ ){
            for(int x = 0; x < imgCopy.getWidth(); x++){
                int pixlInt = imgCopy.getRGB(x, y);
                Color pixelColor = new Color(pixlInt);
                pixelnum++;
               averageAlpha= pixelColor.getAlpha();
            }
        }
        averageAlpha = averageAlpha/pixelNum;
        for(int y = 0; y < imgCopy.getHeight(); y++ ){
            for(int x = 0; x < imgCopy.getWidth(); x++){
                int pixlInt = imgCopy.getRGB(x, y);
                Color pixelColor = new Color(pixlInt);
                int alpha = pixelColor.getAlpha();
                Color outColor;
                if(alpha >= averageAlpha){
                     outColor = new Color(255,255,255);
                    imgCopy.setRGB(x, y, outColor.getRGB());
                }else{
                    outColor = new Color(0,0,0);
                    imgCopy.setRGB(x, y, outColor.getRGB());
                }
            }
            return imgCopy;
        }
    }
    public BufferedImage testScale(){
        BufferedImage imgCopy = this.img;
        for(int y = 0; y < imgCopy.getHeight(); y++ ){
            for(int x = 0; x < imgCopy.getWidth(); x++){
                int pixelc;
                try{
                int pixlInt1 = imgCopy.getRGB(x, y);
                int pixlInt2 = imgCopy.getRGB(x + 1, y);
                int pixlInt3 = imgCopy.getRGB(x, y+ 1);
                int pixlInt4 = imgCopy.getRGB(x +1, y + 1);
                 pixelc = pixlInt1 + pixlInt2 + pixlInt3 + pixlInt4;
                pixelc = pixelc/4;
                }catch(Exception e){
                    pixelc = imgCopy.getRGB(x, y);
                }
                Color pixelColor = new Color(pixlInt);
              
                Color outColor = new Color(r,g,b);
                imgCopy.setRGB(x, y, pixelc);
            }
        }
        return imgCopy;
    }

}
