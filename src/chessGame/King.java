package chessGame;

public class King extends Pieces{
    
    private final int STEPS = 1;
    private final String name;
    
    public King(int xPosition, int yPosition, String color){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        name = color + " King";
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
        
        //If it is moving one spot North East South West or an Angle, it works
        //otherwise it fails.
        //Do not need to check path, because it is only moving one spot. It will
        //either be moving to the spot, or taking the piece in that spot
        if(xDifference == 1 && yDifference == 1){
            return true;
        }else if(xDifference == 1 && yDifference == 0){
            return true;
        }else if(xDifference == 0 && yDifference == 1){
            return true;
        }else{
            return false;
        }
    }
}
