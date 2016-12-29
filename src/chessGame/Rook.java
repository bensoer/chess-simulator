package chessGame;

public class Rook extends Pieces{

    private final int STEPS = 0;
    private final String name;
        
    public Rook(int xPosition, int yPosition, String color){
        this.color = color;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        name = color + " Rook";
    }
    @Override
    public String getColor() {
        return color;
    }
    @Override
    public String getName() {
        return name;
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
        
        //Rook is moving on an angle
        if(xDifference == yDifference){
            return false;
        }else{
            //Rook is moving North or South
            if(xDifference == 0 && yDifference != 0){
                //Rook is moving North
                if(yDirection == true){
                    while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                        if(isAPiece(tempXPos,++tempYPos, black, white)){
                            return false;
                        }
                    }
                //Rook is moving South
                }else if(yDirection == false){
                    while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                        if(isAPiece(tempXPos,--tempYPos, black, white)){
                            return false;
                        }
                    }
                }
                return true;
            //Rook is moving East or West
            }else if(xDifference != 0 && yDifference == 0){
                //Rook is moving East
                if(xDirection == true){
                    while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                        if(isAPiece(++tempXPos,tempYPos, black, white)){
                            return false;
                        }
                    }
                //Rook is moving West
                }else if(xDirection == false){
                    while(tempXPos != xMoveTo && tempYPos != yMoveTo){
                        if(isAPiece(--tempXPos,tempYPos, black, white)){
                            return false;
                        }
                    }
                }
                return true;
            }else{ //xDifferences are not going in legal directions
                return false;
            }
        }
    }

}
