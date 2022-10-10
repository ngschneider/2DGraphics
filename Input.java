import java.util.ArrayList;
import java.util.Random;

public class Input {
    // Matrix input
    private ArrayList<ArrayList<Float>> matrix ;
    int iteratorX = 0;
    int iteratorY = 0;
    boolean iteratorEndOfMatrix = false;
    float iteratorMatrixValue;

    public Input(ArrayList<ArrayList<Float>> input){
        this.matrix = input; 
    }

    public Input(){}

    public void iterateSetValue(float val){
        this.matrix.get(iteratorY).add(iteratorX, val);
    }

    public void setMatrixValue(Float val){
        this.matrix.get(iteratorY).set(iteratorX,val);
    }
    
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
    public void populate(int rows, int columns){
        while(this.iterateMatrixByRow()){
            setMatrixValue( new Random().nextFloat() * 256);
        }
    }
    public void expandMatrix(int rowExpansionValue, int columnExpansionValue){

        Float defaultValue = 0f;
        int desiredRowLength = this.matrix.size() + rowExpansionValue;
        int desiredColumnLength = this.matrix.size() + columnExpansionValue;
        // Expands Columns
            for(int column = this.matrix.get(0).size(); column < desiredColumnLength; column++){
                for(int row = 0; row < this.matrix.size() ; row++){
                    this.matrix.get(0).add(column, defaultValue);
                }
            }
        
    }
    // Each matrix is atleast 1 by 1;
    public void  initializeMatrix(){
        Float defaultValue = 0f;
        this.matrix = new ArrayList<ArrayList<Float>>();
            
        
        this.matrix.add(0, new ArrayList<Float>());
        this.matrix.get(0).add(defaultValue);
        this.matrix.get(0);
        
    }
    public String toString(){

        String str = "";
        while(this.iterateMatrixByRow()){
            str += " " + this.iteratorMatrixValue;
        }
        return str;


    }
}

