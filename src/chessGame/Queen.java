package chessGame;

public class Queen extends Pieces{
    
    private final int STEPS = 0;
    private final String name;
    
    public Queen(int xPosition, int yPosition, String color){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        name = color + " Queen";
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
        
        //Queen is moving on the angle
        if(xDifference == yDifference){
            //Check there are no pieces in between path
            //Queen is moving north east on board
            if(xDirection == true && yDirection == true){
                while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                    if(isAPiece(++tempXPos,++tempYPos, black, white)){
                        return false;
                    }
                }
                return true;
            //Queen is moving south east on the board
            }else if(xDirection == true && yDirection == false){
                while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                    if(isAPiece(++tempXPos,--tempYPos, black, white)){
                        return false;
                    }
                }
                return true;
            //Queen is moving north west on the board
            }else if(xDirection == false && yDirection == true){
                while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                    if(isAPiece(--tempXPos,++tempYPos, black, white)){
                        return false;
                    }
                }
                return true;
            //Queen is moving south west on the board
            }else{
                while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                    if(isAPiece(--tempXPos,--tempYPos,black, white)){
                        return false;
                    }
                }
                return true;
            }
        //Queen is moving East or West
        }else if(xDifference == 0 && yDifference != 0){
          //Check there are no pieces in between path
            if(xDirection == true){
                while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                    if(isAPiece(++tempXPos,tempYPos, black, white)){
                        return false;
                    }
                }
                return true;
            }else{
                while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                    if(isAPiece(--tempXPos,tempYPos, black, white)){
                        return false;
                    }
                }
                return true;
            }
        //Queen is moving North or South
        }else if(yDifference == 0 && xDifference != 0){
          //Check there are no pieces in between path
            //Queen is moving North
            if(yDirection == true){
                while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                    if(isAPiece(tempXPos,++tempYPos,black, white)){
                        return false;
                    }
                }
                return true;
            //Queen is moving South
            }else{
                while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                    if(isAPiece(tempXPos,--tempYPos, black, white)){
                        return false;
                    }
                }
                return true;
            }
        }else{// xDifference and Difference do not follow legal moves
            return false;
        }
    }
}
