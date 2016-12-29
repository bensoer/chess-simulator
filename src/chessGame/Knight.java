package chessGame;

public class Knight extends Pieces{

    private final int STEPS = 0;
    private final String name;
    
    public Knight(int xPosition, int yPosition, String color){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        name = color + " Knight";
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getColor() {
        return color;
    }
    @Override
    public boolean isAMove(int xMoveTo, int yMoveTo, Pieces black[][], Pieces white[][]) {
        int xDifference = -1;
        int yDifference = -2;
        int tempXPos = xPosition;
        int tempYPos = yPosition;
        boolean xDirection = false; //true means positive increment
        boolean yDirection = false;//false means negative increment
        
        if(xMoveTo > xPosition){
            xDifference = xMoveTo - xPosition;
            xDirection = true;
        }else if(xMoveTo < xPosition){
            xDifference = xPosition - xMoveTo;
        }
        if(yMoveTo > yPosition){
            yDifference = yMoveTo - yPosition;
            yDirection = true;
        }else if(yMoveTo < yPosition){
            yDifference = yPosition - yMoveTo;
        }
        
        if(xDifference == 1 && yDifference == 2){
            return true;
        }else{
            return false;
        }
    }

}
