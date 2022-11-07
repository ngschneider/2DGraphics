import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CrannyEdge {
        public BufferedImage image;
        public BufferedImage grayScaleImage;
        public BufferedImage gaussianBlurImage;
        public BufferedImage sobelX;
        public BufferedImage sobelY;
        public BufferedImage magnitudeImage;
        public BufferedImage arctanImage;

    
        public CrannyEdge(BufferedImage img){
            this.image = img;
            this.grayScale();
            this.gaussianBlur();
            this.sobelX();
            this.sobelY();
            this.magnitudeOf2Images(this.sobelX,this.sobelY);
          //  this.arctangentOfImage();
        }
        private BufferedImage grayScale(){
            GrayScale gs = new GrayScale(this.image);
            this.grayScaleImage = gs.redGrayScale();
            return this.grayScaleImage;
        }
        private BufferedImage gaussianBlur(){
            Convolution conv = new Convolution(new Convolution().gaussianBlur, this.grayScaleImage);
            this.gaussianBlurImage = conv.Convolute();
            return this.gaussianBlurImage;
        }
        private BufferedImage sobelX(){
            Float sobelX[][] = {
                {-1f,0f,1f},
                {-2f,0f,2f},
                {-1f,0f,1f}
               };
               Convolution conv = new Convolution(sobelX, this.gaussianBlurImage);
               this.sobelX = conv.Convolute();
               return this.sobelX;
        }
        private BufferedImage sobelY(){
            Float sobelY[][] = {
                {1f,2f,1f},
                {0f,0f,0f},
                {-1f,-2f,-1f}
               };
               Convolution conv = new Convolution(sobelY, this.gaussianBlurImage);
               this.sobelY = conv.Convolute();
               return this.sobelY;
        }
        private BufferedImage magnitudeOf2Images(BufferedImage img1, BufferedImage img2){
            Convolution conv = new Convolution();
            ArrayList<ArrayList<ArrayList<Float>>> matrixImg1 = conv.imageToMatrix(img1);
            ArrayList<ArrayList<ArrayList<Float>>> matrixImg2 = conv.imageToMatrix(img2);
            ArrayList<ArrayList<ArrayList<Float>>> magnitudeMatrix = conv.copyMatrixSize(matrixImg2);

            for(int channel = 0; channel < 3; channel++){
                for(int y = 0; y <  magnitudeMatrix.get(channel).size(); y++){
                    for(int x = 0; x < magnitudeMatrix.get(channel).get(y).size(); x++){
                        double sum = Math.pow(matrixImg1.get(channel).get(y).get(x),3) + Math.pow(matrixImg2.get(channel).get(y).get(x),3);
                        Float magnitudeI = (float) Math.sqrt(sum);
                        this.angle( matrixImg2.get(channel).get(y).get(x), matrixImg1.get(channel).get(y).get(x));
                        magnitudeMatrix.get(channel).get(y).set(x, magnitudeI);
                    }
                }
            }
            this.magnitudeImage = conv.matrixToBufferedImage(magnitudeMatrix);
            return this.magnitudeImage;
        }

        // Rounds all angles to 90, 135, 45, and 0 
        private int angle(Float float1, Float float2){
            double angle = Math.atan2(float2, float1);
            angle = Math.toDegrees(angle);
           // System.out.println("___________________");
            double range = 22.5;
            if( ((90 - range) <= angle) && ((90 + range) >= angle) ){
               // System.out.println("90");
               return 90;
            }
            if( ((45 - range) <= angle) && ((45 + range) >= angle) ){
              //  System.out.println("45");
              return 45;
            }
            if( ((135 - range) <= angle) && ((135 + range) >= angle) ){
              //  System.out.println("135");
              return 135;
            }
            if( (((180 - range) <= angle) & ((180 + range) >= angle)) || (( (0 - range) <= angle) & ((0 + range) >= angle) ) ){
              //  System.out.println("0");
              return 0;
            }
           // System.out.println("Angle : " + angle);
            
            return 0;
        }
    
    
    
}
