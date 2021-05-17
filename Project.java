/*
Ethan Mulder
project
*/
import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.net.*;
import javafx.geometry.*;
import javafx.scene.shape.Line;
import java.awt.Checkbox;
public class Project extends Application
{
   //create the background BorerPane
   BorderPane root = new BorderPane();
   //create the canvas
   Canvas CanvasP = new CanvasP("ProjectData.txt");
   //inisitalize the 2d array
   int[][]list = new int[21][21];
   //start method
   public void start(Stage stage)
   {
      //add the canvas to the BorderPane
      root.getChildren().add(CanvasP);
      //setting scene
      Scene scene = new Scene(root,525,525);
      stage.setScene(scene);
      stage.setTitle("A very cool maze game");
      stage.show();
      //graphics context
      GraphicsContext gc = CanvasP.getGraphicsContext2D();
      //request focus on canvas
      CanvasP.requestFocus();
   }  
   //canvas class
   public class CanvasP extends Canvas
   {
   //members file, xposition, and yposition
   String File;
   int xPosition;
   int yPosition;
      //constructor
      public CanvasP(String File)
      {
         this.File = File;
         //525x525 canvas
         setWidth(525);
         setHeight(525);
         //use keyListener
         root.setOnKeyPressed(new KeyListener());
         //create 2d array list
         int[][]list = new int[21][21];
         //try to read in file
         try
         {
            //scanner to read in file
            Scanner scan = new Scanner(new File("ProjectData.txt"));
            GraphicsContext gc = getGraphicsContext2D();
            //double for loop to read in array and draw the maze
            for(int i = 0;i<list.length;i++)
            {
               for(int j = 0; j<list.length; j++)
               {
                  //fill in the array
                  list[j][i] = scan.nextInt();
                  //if that number in the iteration is a 0, it should set the color as white to draw a white square
                  if(list[j][i] == 0)
                  {
                     gc.setFill(Color.WHITE);
                  }
                  //if that number in the iteration is a 1, it should set the color as black and draw a black square
                  else if(list[j][i] == 1)
                  {
                     gc.setFill(Color.BLACK);
                  }
                  //draw the square with the iteration positions times 25 to get the right pixels
                  gc.fillRect(j*25,i*25,25,25);
                  //fill in the original square
                  //to get the first square, you know that it is in the first row but need to know the column
                  if(list[i][0]==0)
                  {
                     xPosition = i;
                     yPosition = 0;
                     gc.setFill(Color.CYAN);
                     gc.fillRect(xPosition*25,yPosition*25, 25,25);
                  }
               }
            }
      }
      catch(FileNotFoundException fnfe)
      {
      }
      }
      //keyListener class
      public class KeyListener implements EventHandler<KeyEvent>
      {
      GraphicsContext gc = getGraphicsContext2D();
         public void handle (KeyEvent event)
         {
            //try to read file 
            try
            {
            //scanner
            Scanner scan = new Scanner(new File("ProjectData.txt"));
            GraphicsContext gc = getGraphicsContext2D();
               //double for loop to fill in the array again to use it in this class and get the instances of it
               for(int i = 0;i<list.length;i++)
               {
                  for(int j = 0; j<list.length; j++)
                  {
                     //fill the list
                     list[i][j] = scan.nextInt();
                  }
               }
            }
            catch(FileNotFoundException fnfe)
            {
            }
            //if the key pressed is up
            if(event.getCode() == KeyCode.UP )
            {
               //check the boundaries and also check to make sure that block up where you want to move is a white square
               if(yPosition > 0 && list[yPosition-1][xPosition] == 0)
               {
                  gc.clearRect(xPosition*25,yPosition*25,25,25);
                  yPosition--;
                  gc.setFill(Color.CYAN);
                  gc.fillRect(xPosition*25,yPosition*25,25,25);
               }  
            }
            //if down is pressed
            if (event.getCode() == KeyCode.DOWN)
            {
               //check the boundaries and also check to make sure that block down where you want to move is a white square
               if(yPosition < 20 && list[yPosition+1][xPosition] == 0)
               {
                  gc.clearRect(xPosition*25,yPosition*25,25,25);
                  yPosition++;
                  gc.setFill(Color.CYAN);
                  gc.fillRect(xPosition*25,yPosition*25,25,25);
               }   
            }
            //if left is pressed 
            if (event.getCode() == KeyCode.LEFT)
            {
               //check the boundaries and also check to make sure that block left where you want to move is a white square
               if(yPosition > 0 && list[yPosition][xPosition-1] == 0)
               {
                  gc.clearRect(xPosition*25,yPosition*25,25,25);
                  xPosition--;
                  gc.setFill(Color.CYAN);
                  gc.fillRect(xPosition*25,yPosition*25,25,25);
               }
            }
            //if right is pressed
            if (event.getCode() == KeyCode.RIGHT)
            {
               //check the boundaries and also check to make sure that block right where you want to move is a white square
               if(xPosition < 20 && list[yPosition][xPosition+1] == 0)
               {
                  gc.clearRect(xPosition*25,yPosition*25,25,25);
                  xPosition++;
                  gc.setFill(Color.CYAN);
                  gc.fillRect(xPosition*25,yPosition*25,25,25);
               }   
            }
            //if the block reaches the last row, you won the game
            if(yPosition == 20)
            {
               gc.clearRect(0,0,525,525);
               Label label = new Label();
               root.setCenter(label);
               label.setBackground(new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY, Insets.EMPTY))); 
               label.setText("You win!");     
            }
         }
      } 
   }
   public static void main(String[] args)
   {
      launch(args); 
   }
}