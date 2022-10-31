import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Color;

public class Convolution {
    private BufferedImage img;
    private ArrayList<ArrayList<ArrayList<Float>>> matrix3D;
    private ArrayList<ArrayList<Float>> kernal ;
    private ArrayList<ArrayList<ArrayList<Float>>> transposeMatrix; 

    public Convolution(ArrayList<ArrayList<Float>> kernal, BufferedImage img){
        this.kernal = kernal;
        this.img = img;
    }

    public ArrayList<ArrayList<ArrayList<Float>>> imageToMatrix(BufferedImage myImage){
        ArrayList<ArrayList<ArrayList<Float>>> matrixOfImage = new ArrayList<ArrayList<ArrayList<Float>>>();
        for(int z = 0; z < 3;z++){
            matrixOfImage.add(z, new ArrayList<ArrayList<Float>>());
        }
            for(int y = 0; y < img.getHeight(); y++){
                matrixOfImage.get(0).add(y, new ArrayList<Float>());
                matrixOfImage.get(1).add(y, new ArrayList<Float>());
                matrixOfImage.get(2).add(y, new ArrayList<Float>());
                for(int x = 0; x < img.getWidth();x++){
                    Color tempColor = new Color(myImage.getRGB(x, y));
                    matrixOfImage.get(0).get(y).add(x, (float) tempColor.getRed() );
                    matrixOfImage.get(1).get(y).add(x, (float) tempColor.getGreen() );
                    matrixOfImage.get(2).get(y).add(x, (float) tempColor.getBlue() );

                }
            }
        //this.matrix3D = matrixOfImage;
        return matrixOfImage;
    }
    public BufferedImage Convolute(){
        BufferedImage imgCopy = this.img;
        this.matrix3D = this.imageToMatrix(imgCopy);
        this.matrix3D = this.normaizePixels(this.matrix3D);
        this.transposeMatrix = this.copyMatrixSize(imageToMatrix(imgCopy));
       // this.transposeMatrix = this.nullMatrix(transposeMatrix);
        ArrayList<ArrayList<Float>> redPixelSubset;
        ArrayList<ArrayList<Float>> greenPixelSubset;
        ArrayList<ArrayList<Float>> bluePixelSubset;
        Float resultRed;
        Float resultGreen;
        Float resultBlue;

        for(int y = 0; y < this.matrix3D.get(0).size(); y++ ){
            for(int x = 0; x < this.matrix3D.get(0).get(y).size(); x++){

                redPixelSubset = this.getRequiredPixels(x, y,0);
                greenPixelSubset = this.getRequiredPixels(x, y,1);
                bluePixelSubset = this.getRequiredPixels(x, y,2);

            //    System.out.println(redPixelSubset.toString());
                
                resultRed = matrixMultiplication(kernal, redPixelSubset);
                resultGreen = matrixMultiplication(kernal, greenPixelSubset);
                resultBlue = matrixMultiplication(kernal, bluePixelSubset);
               
                transposeMatrix.get(0).get(y).set(x, resultRed);
                /////////////////
                transposeMatrix.get(1).get(y).set(x, resultGreen);
                transposeMatrix.get(2).get(y).set(x, resultBlue);

               // float newRed = maxValue(resultRed.get(0));
                //float newGreen = maxValue(resultGreen.get(1));
               // float newBlue = maxValue(resultBlue.get(2));

               // System.out.println("Red " + newRed + "\nGreen " + newGreen + "\nBlue " + newBlue );

               // Color c = new Color(   resultRed , resultGreen ,resultBlue);
                //System.out.print(c.toString());
//              imgCopy.setRGB(x, y, c.getRGB());
            }
        }

        //transposeMatrix = normaizePixels(transposeMatrix);

        return matrixToBufferedImage(transposeMatrix);
    }

    private ArrayList<ArrayList<ArrayList<Float>>> copyMatrixSize(ArrayList<ArrayList<ArrayList<Float>>> matrix){
        for(int channel = 0; channel < 3; channel++){
            for(int y = 0; y <  matrix.get(channel).size(); y++){
                for(int x = 0; x < matrix.get(channel).get(y).size(); x++){
                    matrix.get(channel).get(y).set(x ,0f);
                }
            }
        }
        return matrix;
    }
    // Return a null matrix
    private ArrayList<ArrayList<ArrayList<Float>>> nullMatrix(ArrayList<ArrayList<ArrayList<Float>>> matrix){
        for(int channel = 0; channel < 3; channel++){
            for(int y = 0; y <  matrix.get(channel).size(); y++){
                for(int x = 0; x < matrix.get(channel).get(y).size(); x++){
                    matrix.get(channel).get(y).set(x, 0f);
                    System.out.println(" x  : " + x + ", y = " + y + "\n " + matrix.get(channel).size() + " \n" +  matrix.get(channel).get(0).size());
                }
            }
        }
        return matrix;
    }
    public BufferedImage matrixToBufferedImage (ArrayList<ArrayList<ArrayList<Float>>> matrix) {
        BufferedImage imgCopy = this.img;
       
            for(int y = 0; y < img.getHeight(); y++){
                for(int x = 0; x < img.getWidth();x++){
                    Float red = Math.min(Math.abs(matrix.get(0).get(y).get(x)),1);
                    Float green = Math.min(Math.abs(matrix.get(1).get(y).get(x)),1);
                    Float blue = Math.min(Math.abs(matrix.get(2).get(y).get(x)),1);
                    System.out.println(red);

                    Color c = new Color(red , green ,blue);

                    imgCopy.setRGB(x, y, c.getRGB());
                }
            }
        
        return imgCopy;
    }

    // Normalize range to 0 to 1
    public ArrayList<ArrayList<ArrayList<Float>>> normaizePixels(ArrayList<ArrayList<ArrayList<Float>>> matrix){
        Float smallest = 0f;
        Float largest = 255f;
        for(int z =0; z < 3;z++){
            for(int y = 0; y < img.getHeight(); y++){
                for(int x = 0; x < img.getWidth();x++){
                    largest = matrix.get(z).get(y).get(x) > largest ? matrix.get(z).get(y).get(x): largest;
                    smallest = matrix.get(z).get(y).get(x) < smallest ? matrix.get(z).get(y).get(x) : smallest; 
                }
            }
        }
        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth();x++){

                Float red = matrix.get(0).get(y).get(x);
                Float green = matrix.get(1).get(y).get(x);
                Float blue = matrix.get(2).get(y).get(x);

                Float normalRed = (red - smallest)/(largest - smallest);
                Float normalGreen = (green - smallest)/(largest - smallest);
                Float normalBlue= (blue - smallest)/(largest - smallest);
                

                matrix.get(0).get(y).set(x, normalRed);
                matrix.get(1).get(y).set(x, normalGreen);
                matrix.get(2).get(y).set(x, normalBlue);
            }
        }
        return matrix;
    }

    public Float matrixMultiplication(ArrayList<ArrayList<Float>> kernal, ArrayList<ArrayList<Float>> imgPixels){
        float accumulator = 0f;

        for(int y = 0; y < 3; y ++) {
            for(int x = 0; x < 3; x++){
                accumulator += imgPixels.get(y).get(x) * kernal.get(y).get(x);    
            }
        }
        
        return accumulator;
    }

    public float maxValue(float pixColor){
        if(pixColor > 255f){
            return 255;
        }
        if(pixColor < 0){
            return 0;
        }
        return pixColor;
    }
    // Return required Pixels of the img
    public ArrayList<ArrayList<Float>> getRequiredPixels(int pixelLocationX, int pixelLocationY, int channel){
        ArrayList<ArrayList<Float>> requiredPixels = new  ArrayList<ArrayList<Float>> ();
        
        
        int xOffsetLeft = 0;
        int yOffsetTop = 0;
        int xOffsetRight = 0;
        int yOffsetBottum = 0;

        requiredPixels.add(0, new ArrayList<Float>());
        requiredPixels.add(1, new ArrayList<Float>());
        requiredPixels.add(2, new ArrayList<Float>());

        // Top pixel is outside of 
        if(pixelLocationX == 0){
            xOffsetLeft = 1;
        }
        if(pixelLocationY == 0){
            yOffsetTop =  + 1;
        }
        if(pixelLocationX >= this.img.getWidth() - 1){
            xOffsetRight = -1;
        }
        if(pixelLocationY >= this.img.getHeight() - 1){
            yOffsetBottum =  - 1;
        }
        
        // [X00]
        // [000]
        // [000]
        if(Float.isNaN(( this.matrix3D.get(channel).get(pixelLocationY - 1 + yOffsetTop).get(pixelLocationX -1 + xOffsetLeft)))){
            //System.out.print( this.matrix3D.get(channel).get(pixelLocationY - 1 + yOffsetTop).get(pixelLocationX -1 + xOffsetLeft));
            System.out.print("True");
        }
        requiredPixels.get(0).add(0, (float) this.matrix3D.get(channel).get(pixelLocationY - 1 + yOffsetTop).get(pixelLocationX -1 + xOffsetLeft));
        System.out.println( this.matrix3D.get(channel).get(pixelLocationY - 1 + yOffsetTop).get(pixelLocationX -1 + xOffsetLeft));
       // System.out.println(pixelLocationY - 1 + yOffsetTop);
       // System.out.println(pixelLocationX -1 + xOffsetLeft);
        //System.out.println((float) this.matrix3D.get(channel).get(pixelLocationY - 1 + yOffsetTop).get(pixelLocationX -1 + xOffsetLeft));
      
        // [0X0]
        // [000]
        // [000]
        requiredPixels.get(0).add(1, (float) this.matrix3D.get(channel).get(pixelLocationY - 1 + yOffsetTop).get(pixelLocationX) );
        // [00X]
        // [000]
        // [000]
        requiredPixels.get(0).add(2, (float) this.matrix3D.get(channel).get( pixelLocationY - 1 + yOffsetTop).get(pixelLocationX + 1 + xOffsetRight) );
        // [000]
        // [X00]
        // [000]
        requiredPixels.get(1).add(0, (float) this.matrix3D.get(channel).get( pixelLocationY).get(pixelLocationX -1 + xOffsetLeft) );
        // [000]
        // [0X0]
        // [000]
        requiredPixels.get(1).add(1, (float) this.matrix3D.get(channel).get( pixelLocationY ).get(pixelLocationX) );
        // [000]
        // [00X]
        // [000]
        requiredPixels.get(1).add(2, (float) this.matrix3D.get(channel).get(pixelLocationY ).get(pixelLocationX + 1 + xOffsetRight) );
        // [000]
        // [000]
        // [X00]
        requiredPixels.get(2).add(0, (float) this.matrix3D.get(channel).get(pixelLocationY + 1 + yOffsetBottum).get(pixelLocationX -1 + xOffsetLeft) );
        // [000]
        // [000]
        // [0X0]
        requiredPixels.get(2).add(1, (float) this.matrix3D.get(channel).get(pixelLocationY + 1 + yOffsetBottum).get(pixelLocationX) );
        // [000]
        // [000]
        // [00X]
        requiredPixels.get(2).add(2, (float) this.matrix3D.get(channel).get(pixelLocationY + 1 + yOffsetBottum).get(pixelLocationX + 1 + xOffsetRight) );

        System.out.println("REQUIRED PIXELS AFTER : \n" + requiredPixels.toString());

        return requiredPixels;
    }
    
}