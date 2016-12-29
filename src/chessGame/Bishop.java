package chessGame;

public class Bishop extends Pieces{

   private final String name;
   private final int STEPS = 0;

    public Bishop(int xPosition, int yPosition, String color){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        name = color + " Bishop";
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
        
        //Bishop is moving on an angle
        if(xDifference != yDifference){
            return false;
        }else{
            //Bishop is moving North East
            if(xDirection == true && yDirection == true){
                while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                    //If there is a piece along its path
                    if(isAPiece(++tempXPos,++tempYPos,black, white)){
                        return false;
                    }
                }
                return true;
            //Bishop is moving North West
            }else if(xDirection == false && yDirection == true){
                while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                    //If there is a piece along its path
                    if(isAPiece(--tempXPos,++tempYPos, black, white)){
                        return false;
                    }
                }
                return true;
            //Bishop is moving South East
            }else if(xDirection == true && yDirection == false){
                while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                    //If there is a piece along its path
                    if(isAPiece(++tempXPos,--tempYPos, black, white)){
                        return false;
                    }
                }
                return true;
            //Bishop is moving South West
            }else if(xDirection == false && yDirection == false){
                while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                    //If there is a piece along its path
                    if(isAPiece(--tempXPos, --tempYPos, black, white)){
                        return false;
                    }
                }
                return true;
            }else{//xDifference and yDifference do not meet legal moves
                return false;
            }
            
        }
    }
}
