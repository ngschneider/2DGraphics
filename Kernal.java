import java.util.ArrayList;
public class Kernal {

    private ArrayList<ArrayList<Float>> matrix;
    int iteratorX = 0;
    int iteratorY = 0;
    boolean iteratorEndOfMatrix = false;
    float iteratorMatrixValue;

    public Kernal(){ }
    
    public boolean iterateMatrixByRow(){
        float result = matrix.get(iteratorY).get(iteratorX);
        iteratorX++;
        int totalRowLength = matrix.get(iteratorY).size();
        int totalColumnLength = matrix.size();
        if(iteratorX == totalRowLength){
            iteratorY++;
            iteratorX = 0;
            if(iteratorY == totalColumnLength){
                this.iteratorEndOfMatrix = true;
                iteratorY = 0;
                return false;
            }
          
        }else {
            this.iteratorEndOfMatrix = false;
            
        }
        this.iteratorMatrixValue = result;
        return true;
    }
    
}