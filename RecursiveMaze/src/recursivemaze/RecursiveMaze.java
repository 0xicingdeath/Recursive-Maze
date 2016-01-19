/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recursivemaze;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * @author nataliechin
 */
public class RecursiveMaze implements MouseListener
{
        private JFrame mainFrame = new JFrame("The Maze");
        private JPanel mainPanel = new JPanel(new BorderLayout());
        private JPanel eastPanel = new JPanel(new GridLayout(2,0));
        private Squares mazeMap[][] = new Squares[15][15];
        private JLabel displayToUser[][] = new JLabel[15][15];
        private final Font MAIN_FONT = new Font("Harry P", Font.PLAIN, 25);
        private final Font TITLE_FONT = new Font("Harry P", Font.BOLD, 30);
        private final JButton CREATE_MAZE = new JButton(new ImageIcon("CreateMaze.png"));
        private final JButton SOLVE_MAZE = new JButton(new ImageIcon("SolveMaze.png"));
        private final Color PURPLE = new Color(155, 122, 221);
        private final Color BLUE = new Color (70, 183, 247);    
        private boolean tracing = true;
        private final char WALL = 'W', EXIT = 'X', OPEN = '_', ATTEMPTED = 'A', GOOD = 'G', ENTRANCE = 'E', TRIED = '.';
        private JButton okay = new JButton("Continue");
        private BufferedReader infile;
        private int entranceX = 0, entranceY = 0, exitY = 0, exitX = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        new RecursiveMaze();
    }
    
    /************************************************************************************************************
     * Method Description: Overrides the default Object Constructor
     * Preconditions: There are none, this constructor simply overrides the default constructor. Nothing has to be true before it runs. 
     * Postconditions: The methods will: initialize, readToFile, and createMaze will be called. 
     */
    
    RecursiveMaze()
    {
        initialize();
        instructions();
    } //end of constructor method. 
    
    /************************************************************************************************************
     * Method Description: Initializes all the variables to its' default setting so the user can create another maze. 
     * Preconditions: When the user decides to create another maze, this method would run. If the user wants to exist the program, there would be no need to run this program. 
     * Postconditions: After the method runs, all the variables and values will be set to their default so another maze can be created. 
     */    
    
    private void initialize()
    {
        //Declaring the GUI
                    mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    mainFrame.setFont(TITLE_FONT);

                //JPanel mazeSquares[][] = new JPanel[15][15];

                //Code for creating board game.
                    CREATE_MAZE.setFont(MAIN_FONT);
                        CREATE_MAZE.setEnabled(true);
                        CREATE_MAZE.setForeground(BLUE);
                        CREATE_MAZE.setBackground(PURPLE);
                        CREATE_MAZE.addMouseListener(this);
                    SOLVE_MAZE.setFont(MAIN_FONT);
                        SOLVE_MAZE.setEnabled(false);
                        SOLVE_MAZE.setEnabled(true);
                        SOLVE_MAZE.setForeground(BLUE);
                        SOLVE_MAZE.setBackground(PURPLE); 
                        SOLVE_MAZE.addMouseListener(this);

                        eastPanel.add(CREATE_MAZE);
                        eastPanel.add(SOLVE_MAZE);

                mainFrame.setContentPane(mainPanel);
                mainFrame.setSize(800, 800);
                mainFrame.setVisible(false);        
        
        for (int row = 0; row<mazeMap.length; row++)
        {
            for (int col = 0; col<mazeMap[row].length; col++)
            {
                mazeMap[row][col] = new Squares();
                mazeMap[row][col].setWhat(OPEN);
                if (tracing) System.out.println("mazeMap declared. row: " +row+ " col: " +col);
            }  //end of for loop col. 
        } //end of for loop row. 
        
            int randomCoordinate = (int)(Math.random()*14);
                if (tracing) System.out.println("Random Coordinate: " +randomCoordinate);
            int randomEnd = (int)(Math.random()*14);        
                if (tracing) System.out.println("Random End: " +randomEnd);
        
            int counter = (int)(Math.random()*50);
            
            if (mazeMap[0][0].getWhat() == OPEN) //The maze hasn't been created yet. By default, it is set to 'A'. 
            {
                    //Sets the entrance and exit of the maze. 
                    if (counter%3==0) //if counter is an even number
                    {
                        mazeMap[randomCoordinate][0].setWhat(ENTRANCE); //The Entrance is somewhere along the left side.
                        entranceX = randomCoordinate;
                        entranceY = 0;
                        
                        mazeMap[randomEnd][14].setWhat(EXIT); //Exit is somewhere on the right side
                        exitX = randomEnd; 
                        exitY = 14; 
                    } //end of if statement
                    else if (counter%4 == 0)
                    {
                        mazeMap[0][randomCoordinate].setWhat(ENTRANCE); //The entrance is somewhere along the top.
                        entranceX = 0; 
                        entranceY = randomCoordinate;
                        
                        mazeMap[14][randomEnd].setWhat(EXIT); //exit is somewhere along the bottom. 
                        exitX = 14; 
                        exitY = randomEnd;
                    } //end of else statement
                    else if (counter%5 == 0)
                    {
                        mazeMap[randomCoordinate][14].setWhat(ENTRANCE); //The entrance is somewhere along the right side. 
                        entranceX = randomCoordinate; 
                        entranceY = 14;
                        
                        mazeMap[randomEnd][0].setWhat(EXIT);
                        exitX = randomCoordinate; 
                        exitY = 0; 
                    } //end of else statement
                    else
                    {
                        mazeMap[14][randomCoordinate].setWhat(ENTRANCE); //the entrance is somewhere along the bottom. 
                        entranceX = 14; 
                        entranceY = randomCoordinate;
                        
                        mazeMap[0][randomEnd].setWhat(EXIT);
                        exitX = 0; 
                        exitY = randomEnd;
                    } //end of else statement
            }//end of if statement   
            
            for (int row = 0; row<displayToUser.length; row++)
            {
                for (int col = 0; col<displayToUser[row].length; col++)
                {
                 displayToUser[row][col] = new JLabel();
                }
            } //end of if statement
        
    } //end of initialize method. 
    
    /************************************************************************************************************
     * Method Description: Gives the users instructions on how to use it. 
     * Preconditions: The text file must exist, as it is being used to read in information and display all of it. 
     * Postconditions: The instruction screen would have been played, and the continue button will allow the user to continue into the interface.
     */       
    
    private  void instructions()
    {
        String tempString = ""; 
        JFrame instructions = new JFrame("Instructions");
            instructions.setFont(TITLE_FONT);
        JPanel mainPanel = new JPanel(new BorderLayout());
            okay.addMouseListener(this);
        JTextArea text = new JTextArea(); 
            text.setFont(MAIN_FONT);
            text.setForeground(PURPLE);
        try //Reading into first file.
        {
                infile = new BufferedReader(new FileReader("Instructions.txt")); 

                for (int i = 0; i<5; i++) //Takes what it is inside the file and reads it into a variable called tempString. 
                {
                    tempString += infile.readLine() + "\n";
                    if (tracing) System.out.println(tempString);
                } //end of for loop

                text.setText(tempString);

                infile.close();
        } //end of try
        catch (Exception e)
        {
                 System.out.println("Cannot access file.");
        } //end of catch
        
        mainPanel.add(text, BorderLayout.CENTER);
        mainPanel.add(okay, BorderLayout.SOUTH);
        
        instructions.setContentPane(mainPanel);      
        instructions.setSize(600, 300); 
        instructions.setVisible(true);
        
    } //end of instruction method.
    
    /************************************************************************************************************
     * Method Description: Creates a maze that will later be solved. 
     * Preconditions: If the user chooses to "Create Maze." This method would only run if called my the constructor method or the mouseClicked methods. 
     * Postconditions: The maze would have been created, and the "Solve Maze" button would become enabled. 
     */    
    
    private void createMaze(int row, int column)
    {   
	if (row == exitX && column == exitY)
	{
		gameBoard();
                if (tracing) System.out.println("Inside if statement");
	}
	else
	{
            if (tracing) System.out.println("Inside less statement");
		int randomGenerator = (int)(Math.random()*4)+1; 
                    if (tracing) System.out.println("Random Generator Value: " +randomGenerator);

		if (randomGenerator == 1 && row>0 && row<15) //go north. 
		{
                    if (tracing) System.out.println("Inside north if statement");
			if (mazeMap[row-1][column].getWhat() == TRIED) //if it was previously on this one. 
			{
				createMaze(row, column); //use original counter
                                if (tracing) System.out.println("Random Generator is 1, and the square has already been tried. ");
			}
			
			else
			{
                                if (tracing) System.out.println("random generator is 2, and the square has not been tried. ");
				mazeMap[row][column].setWhat(TRIED); 
				createMaze(row-1, column);
                                    if (tracing) System.out.println("Recursively called the method.");                                   
			}
		}

		if (randomGenerator == 2 && column>=0 && column<14) //go east
		{
                    if (tracing) System.out.println("randomGenerator is 2. ");
			if (mazeMap[row][column+1].getWhat() == TRIED) //if it was previously on this one. 
			{
				createMaze(row, column);                               
			}
			
			else
			{
				mazeMap[row][column].setWhat(TRIED); 
				createMaze(row, column+1);                            
			}			
		}

		if (randomGenerator == 3 && row>=0 && row<14) //go south
		{
                    if (tracing) System.out.println("random generator is 3. ");
			if (mazeMap[row+1][column].getWhat() == TRIED) //if it was previously on this one. 
			{
				createMaze(row, column);                             
			}
			
			else
			{ 
				mazeMap[row][column].setWhat(TRIED); 
				createMaze(row+1, column);                              
			}			
		}

		if (randomGenerator == 4 && column>0 && column<14) //go west
		{
                    if (tracing) System.out.println("random generator is 4. ");
			if (mazeMap[row][column-1].getWhat() == TRIED) //if it was previously on this one. 
			{
				createMaze(row, column);                               
			}
			
			else
			{
				mazeMap[row][column].setWhat(TRIED); 
				createMaze(row, column-1);                            
			}			
		}
	} 
    } //end of createMaze method. 
    
    /************************************************************************************************************
     * Method Description: Creates the game board where the maze will appear. This is home to all the buttons that the user has access to. 
     * Preconditions: The buttons that are used in this method must exist.
     * Postconditions: The game board will have been displayed, so that the createMaze(); method can insert walls and/or exits, etc. 
     */    
    
    private void gameBoard()
    {  
        JPanel mazePanel = new JPanel(new GridLayout(15, 15));
        for (int row = 0; row<mazeMap.length; row++)
        {
            for (int col = 0; col<mazeMap[row].length; col++)
            {
                displayToUser[row][col].setText(""+mazeMap[row][col].getWhat());
                if (tracing) 
                    System.out.println("Adding the " + mazeMap[row][col].getWhat() +"  to + " + row + " and " + col);
                mazePanel.add(displayToUser[row][col]);
            } //end of for loop col
        } //end of for loop row
        
		for (int mapRow = 0; mapRow<mazeMap.length; mapRow++)
		{
			for (int mapCol = 0; mapCol<mazeMap[mapRow].length; mapCol++)
			{
				mazeMap[mapRow][mapCol].setWhat(OPEN);
                                if (tracing) System.out.println("Setting: " +mapRow + " and " + mapCol+ " to open.");
			}
		}        
        
        mainPanel.add(mazePanel, BorderLayout.CENTER);
        mainPanel.add(eastPanel, BorderLayout.EAST);        
        mainFrame.setVisible(true);
    } //end of gameBoard method.    
    
    /************************************************************************************************************
     * Method Description: Solves the maze.
     * Preconditions: When the maze map has already been created, the program can solve it here. 
     * Postconditions: The maze will have a solution
     */     
    
    private boolean solveMaze(int row, int column)
    {
        boolean solved = false; 
        
            mazeMap[row][column].setWhat(ATTEMPTED);
            
            if (mazeMap[row-1][column].getWhat() == OPEN || mazeMap[row-1][column].getWhat() == EXIT) //North
            {
                solved = solveMaze(row-1, column);
            } //end of nested if statement
            
            if (!solved)
            {

                    if (mazeMap[row][column+1].getWhat() == OPEN || mazeMap[row][column+1].getWhat() == EXIT) //East
                    {
                        solved = solveMaze(row, column+1);
                    } //end of if statement
            } //end of not solved if statement
            
            if (!solved)
            {
                    if (mazeMap[row+1][column].getWhat() == OPEN || mazeMap[row+1][column].getWhat() == EXIT) //South
                    {
                        solved = solveMaze(row+1, column);
                    } //end of if statement
            } //end of not solved if statement
            
            if (!solved)
            {
                    if (mazeMap[row][column-1].getWhat() == OPEN || mazeMap[row][column-1].getWhat() == EXIT) //West
                    {
                        solved = solveMaze(row, column-1);
                    } //end of if statement
            } //end of if statement
            
            if (solved)
            {
                mazeMap[row][column].setWhat(GOOD);
            } //end of if statement solved. 
        
        return solved;        

    } //end of solveMaze method.
    
    @Override
    public void mouseClicked(MouseEvent whatClicked) 
    { 
            if (whatClicked.getSource() == CREATE_MAZE)
            {
                createMaze(entranceX, entranceY);
                  CREATE_MAZE.setEnabled(false); 
                  SOLVE_MAZE.setEnabled(true);
            } //end of if statement for create maze. 

            if (whatClicked.getSource() == SOLVE_MAZE)
            {
                //Insert code that sends the '
                SOLVE_MAZE.setEnabled(false); 
                CREATE_MAZE.setEnabled(true);
            } //end of if statement for solve maze. 

            if (whatClicked.getSource() == okay)
            {
                gameBoard();
            } //end of if statement
    } //end of mouseClicked method.
    
    @Override
    public void mousePressed(MouseEvent whatClicked) 
    {
        
    } //end of mousePressed method.
    
    @Override
    public void mouseReleased(MouseEvent whatClicked) 
    {
        
    } //end of mouseReleased method.
    
    @Override
    public void mouseEntered(MouseEvent whatClicked) 
    {
        
    } //end of mouseEntered method.
    
    @Override
    public void mouseExited(MouseEvent whatClicked) 
    {
        
    } //end of mouseExited method.    
    
}
