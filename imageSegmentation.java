import java.util.ArrayList;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.File;
public class imageSegmentation {

    int clusters = 5;
    ArrayList<Point> meanList = new ArrayList<Point>();
    ArrayList<Color> colorList = new ArrayList<Color>();
    BufferedImage img ;
    ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
    
    public imageSegmentation(BufferedImage img){
        this.img = img;
        this.initalMean();
        this.clusterAlgorithm();
        this.addColor();
    }

    private void addColor(){
        colorList.add(new Color(0,0,255));
        colorList.add(new Color(255,0,0));
        colorList.add(new Color(0,255,0));
        colorList.add(new Color(255,255,0));
        colorList.add(new Color(0,255,255));
        colorList.add(new Color(255,0,255));
        colorList.add(new Color(122,122,204));
        colorList.add(new Color(0,0,204));
        colorList.add(new Color(0,0,204));
    }
    
    public void  clusterAlgorithm() {
      
        for(int iterations = 0; iterations < 1000; iterations++){
            for(int y = 0; y < this.img.getHeight();y++){
                matrix.add(y,new ArrayList<Integer>());
                for(int x = 0; x < this.img.getWidth();x++){
                    this.matrix.get(y).add(x, assignCluster(x, y));
                }
            }
            System.out.println(this.meanList);
            updateMean();
            String path = "./images/Segmentation/cluster" + iterations + ".jpg";
            System.out.println(iterations);
            try {
               
                    ImageIO.write(this.clusterImage(), "PNG", new File(path));
                
                    
            } catch (Exception e) {
                System.out.println(e);
                // TODO: handle exception
            }
            
        }   
        
    }
    public BufferedImage clusterImage(){
        BufferedImage imgCopy = this.img;
        for(int y = 0; y < imgCopy.getHeight(); y++ ){
            for(int x = 0; x < imgCopy.getWidth(); x++){
                Color outColor = this.colorList.get(this.matrix.get(y).get(x));
                imgCopy.setRGB(x, y, outColor.getRGB());
            }
        }
        return imgCopy;
    }
    private void initalMean(){
        for(int i = 0; i < this.clusters; i++){
            Random rand = new Random();
            meanList.add(new Point(rand.nextInt(this.img.getWidth()),rand.nextInt(this.img.getHeight())));
        }
    } 

    private void updateMean(){
        for(int cluster = 0;cluster  < this.clusters ;cluster++){
            double accumulatorx = 0;
            double accumulatory = 0;
            int clusterLength = 0;
            for(int y = 0; y < this.img.getHeight();y++){
                for(int x = 0;x  < this.img.getWidth();x++){
                    if(cluster == this.matrix.get(y).get(x)){
                        accumulatorx += x;
                        accumulatory += y; 
                        clusterLength++;
                    }
                }
            }
            int meanx =(int)(accumulatorx/clusterLength);
            int meany =(int)(accumulatory/clusterLength);
            Point p = new Point(meanx,meany);
            this.meanList.set(cluster,p);
        }
    }
    private int assignCluster(int x, int y){
        int closest = 0;

        for(int i = 1; i < clusters;i++){
            double distance = Point.distance(meanList.get(i).getX(), meanList.get(i).getY(), x, y);
            double currentClosest = Point.distance(meanList.get(0).getX(), meanList.get(0).getY(), x, y);
            if(distance < currentClosest){
                closest = i;
            }
            
        }
        return closest;
    }

    private int assignCluster2(int x, int y){
        int closest = 0;

        for(int i = 1; i < clusters;i++){
            double distance = Point.distance(meanList.get(i).getX(), meanList.get(i).getY(), x, y);
            double currentClosest = Point.distance(meanList.get(0).getX(), meanList.get(0).getY(), x, y);
            if(distance < currentClosest){
                closest = i;
            }
            
        }
        return closest;
    }


}