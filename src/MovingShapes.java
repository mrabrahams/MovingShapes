import java.util.*;
import java.awt.*;

public class MovingShapes<numShapes> {
    static Scanner scnr = new Scanner(System.in);
    static int numShapes; //number of shapes the user will want to display
    static int numMoves;  //number of times to move said shapes

    static String[] shapes = new String[20];//{"Square", "Circle"};
    static int[] shapeSize = new int[20];
    static String[] shapeColors = new String[20];//{"Red", "Blue", "Pink", "Yellow", "Green", "Magenta", "Orange", "Dark_gray", "Light_gray", "Gray"};
    static int[] moveDirection = new int[20];
    static int[] moveSpeed = new int[20];
    static int[] xTopLeft  = new int[20];
    static int[] yTopLeft  = new int[20];
    static Graphics pen;

    //obtain the information for every shape.
    public static void getShapeInformation(){
        int i = 0;
        while (i < numShapes){//obtain the information for the i'th shape.
            shapes[i] = scnr.next();
            shapeSize [i] = scnr.nextInt();
            shapeColors[i] = scnr.next();
            moveDirection[i] = scnr.nextInt();
            moveSpeed[i] = scnr.nextInt();

            ++i;
        }
    }

    //method will calculate the first position of all the shapes so that all shapes should be centered in the drawing panel.
    public static void initialPosition(DrawingPanel panel) {
        int i = 0;
        int h = panel.getHeight();
        int w = panel.getWidth();

        while (i<numShapes){
            if (w < h) {
                xTopLeft[i] = panel.getWidth() / 2;
                yTopLeft[i] = panel.getWidth() / 2;
            }
            else if (h < w){
                xTopLeft[i] = panel.getHeight() / 2;
                yTopLeft[i] = panel.getHeight() / 2;
            }
            else{
                xTopLeft[i] = panel.getWidth() / 2;
                yTopLeft[i] = panel.getHeight() / 2;
            }
            ++i;
        }
        showShapes(panel, true);
        panel.sleep(100);//lets panel delay for 1/10th of a second
    }

    //Will call methods to change color and show the specific forms
    public static void showShapes(DrawingPanel panel, boolean integrity) {
        int i = 0;
        while (i < numShapes){
            if (integrity){
                graphicsSetColor(panel, i);//calls method to set shape[i] color
            }
            else if (!integrity){
                setNoColor(panel);//calls method to set shape[i] to white
            }
            if (shapes[i].equalsIgnoreCase("square")){ //if the i-th shape is a "Square", it calls the method showSquare()
                showSquare(panel, i, integrity);
            }
            else if (shapes[i].equalsIgnoreCase("circle")){// if the i-th shape is a "Circle", it calls the method showCircle()
                showCircle(panel, i, integrity);
            }
            ++i;
        }
    }

    /* method will firstly instantiate the graphics object, draw a circle using the
    xPos, yPos, size, and size, all of them are the index of the shape number received as the second parameter.
    If the boolean parameter is true, it then changes the color to BLACK.
    The method finishes by drawing the shape's border with the same xPos, yPos, size, and size.
     */
    public static void showCircle(DrawingPanel panel, int i, boolean integrity) {
        pen.fillOval(xTopLeft[i],yTopLeft[i],shapeSize[i],shapeSize[i]);

        if (integrity){// If the boolean parameter is true, it then changes the color to BLACK
            pen.setColor(Color.BLACK);
        }

        pen.drawOval(xTopLeft[i],yTopLeft[i],shapeSize[i],shapeSize[i]);//drawing the shape's border with the same xPos, yPos, size, and size.
    }

    /* method will firstly instantiate the graphics object, draw a square using the
    xPos, yPos, size, and size, all of them are the index of the shape number received as the second parameter.
    If the boolean parameter is true, it then changes the color to BLACK.
    The method finishes by drawing the shape's border with the same xPos, yPos, size, and size.
     */
    public static void showSquare(DrawingPanel panel, int i, boolean integrity) {
        //Graphics pen = panel.getGraphics();
        pen.fillRect(xTopLeft[i],yTopLeft[i],shapeSize[i],shapeSize[i]);
        if (integrity){// If the boolean parameter is true, it then changes the color to BLACK
            pen.setColor(Color.BLACK);
        }
        pen.drawRect(xTopLeft[i],yTopLeft[i],shapeSize[i],shapeSize[i]);//drawing the shape's border with the same xPos, yPos, size, and size.
    }

    /* method  will  instantiate the graphics object and assign the color to the graphics depending on the color of the index of
    the shape number received as the second parameter*/
    public static void graphicsSetColor(DrawingPanel panel, int i) {
        pen = panel.getGraphics();

        switch (shapeColors[i].toLowerCase()) {
            case "red" -> pen.setColor(Color.RED);
            case "blue" -> pen.setColor(Color.BLUE);
            case "pink" -> pen.setColor(Color.PINK);
            case "yellow" -> pen.setColor(Color.YELLOW);
            case "green" -> pen.setColor(Color.GREEN);
            case "magenta" -> pen.setColor(Color.MAGENTA);
            case "orange" -> pen.setColor(Color.ORANGE);
            case "dark gray" -> pen.setColor(Color.DARK_GRAY);
            case "light gray" -> pen.setColor(Color.LIGHT_GRAY);
            case "gray" -> pen.setColor(Color.GRAY);
            case "black" -> pen.setColor(Color.BLACK);
            default -> pen.setColor(Color.BLACK);
        }
    }

    //method will instantiate the graphics object and assign WHITE as the color.
    public static void setNoColor(DrawingPanel panel) {
        pen = panel.getGraphics();
        pen.setColor(Color.WHITE);
    }

    //method  will  loop  through  all  the  number  of  times the shapes will move
    public static void showShapesMoving(DrawingPanel panel) {
        int i = 0;
        while (i < numMoves){
            showShapes(panel,false);//draw shapes with white color within the loop
            changePosition();//calculate all  shapes'  new position
            showShapes(panel,true);//show the shapes with their configured color
            panel.sleep(100);//lets panel delay for 1/10th of a second
            ++i;
        }
    }

    //change the xPosition and yPosition of the top left corner of the shape, depending on their corresponding speed and Direction.
    public static void changePosition() {
        int direction;
        int speed;
        int newTopX;
        int newTopY;

        for (int i = 0; i < numShapes; ++i) {
            direction = moveDirection[i];
            speed = moveSpeed[i];
            newTopX = xTopLeft[i];
            newTopY = yTopLeft[i];

            //the switch uses the direction the speed should be applied to in order to find the new positions
            switch (direction) {
                case 0 -> {
                    newTopX = newTopX - (speed);
                    xTopLeft[i] = newTopX;
                }
                //upper left 135 degrees
                case 1 -> {
                    newTopX = newTopX - (speed);
                    xTopLeft[i] = newTopX;
                    newTopY = newTopY - (speed);
                    yTopLeft[i] = newTopY;
                }
                case 2 -> {
                    newTopY = newTopY - (speed);
                    yTopLeft[i] = newTopY;
                }
                //upper right 45 degrees
                case 3 -> {
                    newTopX = newTopX + (speed);
                    xTopLeft[i] = newTopX;
                    newTopY = newTopY - (speed);
                    yTopLeft[i] = newTopY;
                }
                case 4 -> {
                    newTopX = newTopX + (speed);
                    xTopLeft[i] = newTopX;
                }
                //bottom right 315 degrees
                case 5 -> {
                    newTopX = newTopX + (speed);
                    xTopLeft[i] = newTopX;
                    newTopY = newTopY + (speed);
                    yTopLeft[i] = newTopY;
                }
                case 6 -> {
                    newTopY = newTopY + (speed);
                    yTopLeft[i] = newTopY;
                }
                //bottom left 225 degrees
                case 7 -> {
                    newTopX = newTopX - (speed);
                    xTopLeft[i] = newTopX;
                    newTopY = newTopY + (speed);
                    yTopLeft[i] = newTopY;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("UTSA - Fall 2020 - CS1083 - Project 3 - written by DWIGHTABRAHAMS\n");

        System.out.println("Please input width, height of the panel, # of shapes, # of times to move followed by the shape, size, color, direction, and speed of every shape: ");

        int panelWidth = scnr.nextInt();
        int panelHeight = scnr.nextInt();
        numShapes = scnr.nextInt();
        numMoves = scnr.nextInt();

        getShapeInformation();

        DrawingPanel panel = new DrawingPanel(panelWidth, panelHeight);//Drawing panel declared

        initialPosition(panel);

        showShapesMoving(panel);

    }
}