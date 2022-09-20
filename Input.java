import java.util.ArrayList;

public class Input {
    // Matrix input
    public ArrayList<ArrayList<Float>> matrix ;
    int iteratorX = 0;
    int iteratorY = 0;
    boolean iteratorEndOfMatrix = false;
    public Input(ArrayList<ArrayList<Float>> input){
        this.matrix = input; 
    }

    public float iterateMatrixByRow(){
        float result = matrix.get(iteratorX).get(iteratorY);
        if(iteratorX == matrix.size()){
            iteratorY++;
            iteratorX = 0;
            if(iteratorY >= matrix.get(0).size()){
                iteratorY = 0;
            }
          
        }else {
            iteratorX++;
        }
        return result;
    }

    public String toString(){

        String str = ;
        return str;


    }
}

