package chessGame;

public class Pawn extends Pieces{
    
    private final int STEPS = 1;
    private String name;
    private boolean firstStep = false; //false means it has not made its first
                                       //move in the game
    
    public Pawn(int xPosition, int yPosition, String color){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        name = color + " Pawn";
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
        
        //Pawn is making its first move in the game
        if(firstStep == false){
            //Pawn is moving backwards
            if(yDirection == false){
                return false;
            }
            //Pawn is moving forward 1 or 2 spaces
            if(yDirection == true && yDifference < 3){//forward angle still works
                while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                    if(isAPiece(tempXPos,++tempYPos, black, white)){
                        return false;
                    }
                }
            }
            firstStep = true;
            return true;
        //Pawn is not making its first move in the game
        }else{
            if(yDirection == true && yDifference == 1){//forward angle still works
                return true;
            }else{
                return false;
            }
        }
    }

}
