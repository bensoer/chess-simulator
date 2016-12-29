package chessGame;

import java.util.Random;
import java.util.Scanner;

public class ChessBoard {
    Pieces[][] whitePieces;
    Pieces[][] blackPieces;

    /** Chess Board builds all the pieces and places them in their locations
     * on the board. The co-ordinates assume that 0,0 is the bottom left corner 
     * viewing from the white teams side. The first indexes in the array assumes
     * the first index is 1 = pawns row, 0 = all other pieces row. The second 
     * index is left to right of the pieces on the board based on viewing the
     * board from the white teams side.
     */
    public ChessBoard(){
        whitePieces = new Pieces[2][8];
        blackPieces = new Pieces[2][8];
    
        whitePieces[0][0] = new Rook(0,0,"white");
        whitePieces[0][1] = new Knight(1,0,"white");
        whitePieces[0][2] = new Bishop(2,0,"white");
        whitePieces[0][3] = new Queen(3,0,"white");
        whitePieces[0][4] = new King(4,0,"white");
        whitePieces[0][5] = new Bishop(5,0,"white");
        whitePieces[0][6] = new Knight(6,0,"white");
        whitePieces[0][7] = new Rook(7,0,"white");
    
        whitePieces[1][0] = new Pawn(0,1,"white");
        whitePieces[1][1] = new Pawn(1,1,"white");
        whitePieces[1][2] = new Pawn(2,1,"white");
        whitePieces[1][3] = new Pawn(3,1,"white");
        whitePieces[1][4] = new Pawn(4,1,"white");
        whitePieces[1][5] = new Pawn(5,1,"white");
        whitePieces[1][6] = new Pawn(6,1,"white");
        whitePieces[1][7] = new Pawn(7,1,"white");

        
        blackPieces[0][0] = new Rook(0,7,"black");
        blackPieces[0][1] = new Knight(1,7,"black");
        blackPieces[0][2] = new Bishop(2,7,"black");
        blackPieces[0][3] = new Queen(3,7,"black");
        blackPieces[0][4] = new King(4,7,"black");
        blackPieces[0][5] = new Bishop(5,7,"black");
        blackPieces[0][6] = new Knight(6,7,"black");
        blackPieces[0][7] = new Rook(0,7,"black");
        
        blackPieces[1][0] = new Pawn(0,6,"black");
        blackPieces[1][1] = new Pawn(1,6,"black");
        blackPieces[1][2] = new Pawn(2,6,"black");
        blackPieces[1][3] = new Pawn(3,6,"black");
        blackPieces[1][4] = new Pawn(4,6,"black");
        blackPieces[1][5] = new Pawn(5,6,"black");
        blackPieces[1][6] = new Pawn(6,6,"black");
        blackPieces[1][7] = new Pawn(7,6,"black");
    }
    public void play(){
        Scanner scan = new Scanner(System.in);
        System.out.println("---Chess Game---");
        System.out.println("By Ben Soer");
        
        System.out.println("To play this game you will need an actual" 
                + "Chess Board.\n You will make moves by submitting co ordinates"
                + "of the piece you would like to move\n and where you would like"
                + "to move it.\n The computer will then return you "
                + " the piece it chose to move and where it moved it");
        System.out.println();
        System.out.println("Enjoy");
        System.out.println();
        
        System.out.println("White Always goes First");
        //04 is the kings on each team
        while(whitePieces[0][4].isAlive() && blackPieces[0][4].isAlive()){
            boolean match = false;/*when false, cycles through selecting a
            piece until an available piece is chosen*/
            boolean moveable = false;/*when false, cycles through until an
            available place to move the piece is chosen*/
            boolean compturn = true;/* when true, comp is cycling through
            to find a piece it can move */
            int row = 0;//first [] position in array of players piece
            int column = 0;//second [] position in array of players piece
            int newX = -1; //x position where player wants to put piece
            int newY = -1; //y position where player wants to put piece
            //new variables are -1 so that error in checkMove meaning assignment 
            //failed
            
            do 
            {
                System.out.println("Enter the coordinates of the piece you would "
                        + "like to move");
                int pointX = scan.nextInt();
                int pointY = scan.nextInt();
                //check point exsists
                outerloop:
                for(int i = 0; i < whitePieces.length; i++){
                    for(int j = 0; j < whitePieces[i].length; j++){
                        if(whitePieces[i][j].getXPosition() == pointX 
                                && whitePieces[i][j].getYPosition() == pointY
                                && whitePieces[i][j].isAlive()){
                            System.out.println("You chose: " 
                                    + whitePieces[i][j].getName());
                            match = true;
                            moveable = false;
                            row = i;
                            column = j;
                            break outerloop ;
                        }
                    }
                }
                if(match == false){
                    System.out.println("The piece you have chosen does not " +
                            "exsist. Please choose another");
                }
            }while(!match);
            
            while (!moveable){
                //if exsists ask where to go with piece
                System.out.println("Enter coordinates of where you " 
                        + "would like to move the piece");
                newX = scan.nextInt();
                newY = scan.nextInt();
                //check move is possible
                    try {
                        if(whitePieces[row][column].checkMove(newX,newY,row,column,whitePieces,blackPieces)){
                            whitePieces[row][column].setXPosition(newX);
                            whitePieces[row][column].setYPosition(newY);
                            moveable = true;
                            match = false;
                        } else {
                        System.out.println("This piece can not move that way " 
                                + "please try again");
                        }
                    } catch (Exception e) {
                        System.out.println("The checkMove failed. One of its parameters is probably less then zero");
                        e.printStackTrace();
                    }
            }
            
            //computers turn
            System.out.println("Computer's Turn");
            do{
                
                Random generator = new Random();
                int piece = generator.nextInt(14);
                if(piece > 7){
                    piece -= 7;
                    int genX = generator.nextInt(7);
                    int genY = generator.nextInt(7);
                    if(blackPieces[1][piece].checkMove(genX,genY, 1, piece, whitePieces, blackPieces)){
                        System.out.println(blackPieces[1][piece].getName() 
                                +" in position: " 
                                + blackPieces[1][piece].getXPosition()
                                + "," + blackPieces[1][piece].getYPosition());
                        System.out.println("Is moving to position: "
                                + genX + "," + genY);
                        
                        blackPieces[1][piece].setXPosition(genX);
                        blackPieces[1][piece].setYPosition(genY);
                        compturn = false;
                    }
                }else{
                    int genX = generator.nextInt(7);
                    int genY = generator.nextInt(7);
                    if(blackPieces[0][piece].checkMove(genX,genY, 0, piece, whitePieces, blackPieces)){
                        System.out.println(blackPieces[0][piece].getName() 
                                + " in position: " 
                                + blackPieces[0][piece].getXPosition()
                                + "," + blackPieces[0][piece].getYPosition());
                        System.out.println("Is moving to position: "
                                + genX + "," + genY);
                        
                        blackPieces[0][piece].setXPosition(genX);
                        blackPieces[0][piece].setYPosition(genY);
                        compturn = false;
                    }
                }
            }while(compturn == true);
        }
    }

}
