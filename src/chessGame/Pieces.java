package chessGame;

import java.security.InvalidParameterException;
import java.util.Arrays;
/**
 * Pieces represents a piece on the ChessBoard. All pieces have an xPosition,
 * a yPosition, a color and a state telling whether they are alive or still
 * in play. The changing of these states are also taken care of by the Pieces
 * class. Pieces also takes care of checking whether a piece can move to its
 * destination using the checkMove method. checkMove uses the private methods
 * checkWhite and checkBlack to see if any of those pieces are in the position
 * the user or computers piece wants to move to. Methods, getName and isAMove
 * are defined by each individual piece that extends Pieces.
 * 
 * @author Ben
 *
 */
public abstract class Pieces {

    protected int xPosition;
    protected int yPosition;
    protected String color;
    protected boolean alive = true;
    
    
    
    /**
     * checkMove checks whether the piece being moved is a valid move. It
     * checks whether the piece has landed on a white or black piece and pending
     * what your piece is, decifers whether that move can occur. If the user
     * lands on an enemy piece, that piece is removed. If it lands on the user's
     * own piece, checkMove notifies the user and fails. CHECKMOVE DOES NOT MOVE
     * THE USER's PIECE.
     * 
     * @param newX the new X position the user would like to move to
     * @param newY the new Y position the user would like to move to
     * @param row the X position the piece being moved is currently in
     * @param column the Y position the piece being moved is currently in
     * @param white the array of whitePieces
     * @param black the array of blackPieces
     * @return the status of whether the piece can be moved or not
     * @throws InvalidParameterException
     */
    public boolean checkMove(int newX,int newY,int row,int column,
            Pieces white[][], Pieces black[][]) throws InvalidParameterException{
        if( column < 0){
            throw new InvalidParameterException();
        }else if(row < 0){
            throw new InvalidParameterException();
        }else if(newY < 0 || newY > 7){
            System.out.println("You can not place a piece outside of the length of the board");
            return false;
        }else if(newX < 0 || newX > 7){
            System.out.println("You can not place a piece outside of the width of the board");
            return false;
        }
        //----------------------------------------------------------
        boolean status = false;//move is currently not possible
        //check move is possible, can piece move like this ?eg. 1 step, 3 steps
        if(this.isAMove(newX,newY,black,white)){
            status = true;
            //check if it is on a white piece
            if(checkWhite(white,column,row,newX,newY) && status == true){
                status = false;
            }
            //check if it is on a black piece
            if(checkBlack(black,column,row,newX,newY) && status == true){
                status = false;
            }
            
        } else{
            status = false;
        }
        
        return status;
        
    }
    /**checks if the piece landed on is a white piece**/
    private boolean checkWhite(Pieces white[][],int column,int row,int newX, 
            int newY){
        
        boolean isWhite = false;
        outerloop:
        for(int i =0; i< white.length;i++){
            for(int j=0; j <white[i].length;j++){
                if( i == row && j ==column){/*While checking, you haven't 
                actually moved yet, so we don't want to having a match up that
                you have landed on yourself */
                    continue;
                }else if(newX == white[i][j].getXPosition() 
                        && newY == white[i][j].getYPosition()
                        && white[i][j].isAlive()){
                    //if you landed on a white piece and are black
                    if(color.equals("black")){
                        System.out.println("You have landed on the white piece: " 
                                + white[i][j].getName());
                            white[i][j].setAlive(false);
                            isWhite = false;
                            break outerloop;
                    //if you landed on a white piece and are white
                    }else{
                        System.out.println("A white piece already exsists here" 
                                + " Please make another move");
                        isWhite = true;
                        break outerloop;
                    }
                }
            } 
        }
        return isWhite;
    }
    /**checks if the piece landed on is a black piece and if it is not
     * removes the white piece from the game**/
    private boolean checkBlack(Pieces black[][],int column,int row,int newX, 
            int newY){
        
        boolean isBlack = false;
        outerloop:
        for(int i =0; i< black.length;i++){
            for(int j=0; j <black[i].length;j++){
                if( i == row && j == column){ /*While checking, you haven't 
                actually moved yet, so we don't want to having a match up that
                you have landed on yourself */
                    continue;
                }else if(newX == black[i][j].getXPosition() 
                        && newY == black[i][j].getYPosition()
                        && black[i][j].isAlive()){
                        //if you landed on a black piece and you are white
                        if(color.equals("white")){
                            System.out.println("You have landed on the black piece: " 
                                    + black[i][j].getName());
                                black[i][j].setAlive(false);
                                isBlack = false;
                                break outerloop;
                        //if you landed on a black piece and you are black
                        }else{
                            System.out.println("A black piece already exsists here"
                                    + " Please make another move");
                            isBlack = true;
                            break outerloop;
                    }
                    
                    
                }
            }
        }
        return isBlack;
        
    }
    //Returns true if there is a piece at the passed location, false if not
    public boolean isAPiece(int xPosition, int yPosition, Pieces black[][], Pieces white[][]){
        if(contains(xPosition,yPosition,black) || contains(xPosition,yPosition, white)){
            return true;
        }else{
            return false;
        }
    }
    private boolean contains(int xCoord, int yCoord, Pieces array[][]){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                if(array[i][j].getXPosition() == xCoord && array[i][j].getYPosition() == yCoord){
                    return true;
                }
            }
        }
        return false;
    }
    public void setYPosition(int yPosition){
        this.yPosition = yPosition;
    }
    public void setXPosition(int xPosition){
        this.xPosition = xPosition;
    }
    public int getXPosition(){
        return xPosition;
    }
    public int getYPosition(){
        return yPosition;
    }
    public boolean isAlive(){
        return alive;
    }
    public void setAlive(boolean state){
        alive = state;
    }
    public String getColor(){
        return color;
    }
    public abstract String getName();
    
    public abstract boolean isAMove(int xMoveTo, int yMoveTo, Pieces black[][], Pieces white[][]);
    
   
    
    


}
