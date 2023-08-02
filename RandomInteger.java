/**
 * @author Liam Tyler
 * @version 1.0 4/28/2023
 */

public class RandomInteger {
    private int min;
    private int max;
    private int range;

    public RandomInteger() {
        this.min = 0;
        this.max = 100;
        range = max - min + 1;
    }

    public RandomInteger(int min, int max) {
        this.min = min;
        this.max = max;
        range = max - min + 1;
    }

    public void SetMax(int max) {
        this.max = max;
        range = max - min +  1;
    }

    public void SetMin(int min) {
        this.min = min;
        range = max - min +  1;
    }

    public int GetMin() {
        return min;
    }

    public int GetMax() {
        return max;
    }
    
    public int Generate(){
        int num = 0;
        while(num == 0){
            num = (int)(range *  Math.random()) + min;
        }
        //System.out.println(num);
        return num;
    }

    public int GenerateWait(){
        return (int)(range *  Math.random()) + min;
    }
}
