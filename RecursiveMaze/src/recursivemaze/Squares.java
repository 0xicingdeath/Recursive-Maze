/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recursivemaze;

/**
 *
 * @author nataliechin
 */
public class Squares 
{
        private char what;
        
    /************************************************************************************************************
     * Method Description: Allows the programmer to set the value inside this value. 
     * Preconditions: The class must exist before accessing this variable. Inside the program, all the 'what' variables must be declared. In this case, 'W' represents a wall, 'E' an entrance, 'X' and exit, '_' an open space and  '.' tried. The user won't see the '.' though. Any other value, and the method will make it A. 
     * Postconditions: The whatVariable in the class will have a value that the user assigned. Furthermore, the program will be able to test what is located here. 
     */           
        
        public void setWhat(char other)
        {

            if (other != 'W' && other != 'X' && other!= 'E' && other!= '_' && other != '.')
            {
                other = 'A';
            } //end of if statement
            
            what = other;             
            
        } //end of setWhat method.
        
    /************************************************************************************************************
     * Method Description: Allows the program to access the value that is inside this object.
     * Preconditions: The computer will use this value to determine what to place in this section. The array of objects using Squares must exist. 
     * Postconditions: The what variable in the class will be accessible using the method "getWhat();." This will be used to determine whether a wall here would be valid.
     */          
        
        public char getWhat()
        {
            return what;
        } //end of getWhat method. 
}
