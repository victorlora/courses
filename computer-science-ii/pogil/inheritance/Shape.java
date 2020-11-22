/**
 * Write a description of class Shape here.
 * 
 * @author Victor Lora
 * @version (date submitted)
 * 
 * estimated time: XX min
 *    actual time: XX min
 * expected grade: 10/10
 *
 * NOTES:
 *
 */
import java.awt.Color;

public abstract class Shape {
    private Color color = Color.BLACK;

    protected Shape(Color c) { setColor(c);       }

    protected Shape()        { this(Color.BLACK); }

    public Color  getColor() { return this.color; } 

    public void setColor(Color aColor) {
        if (null != aColor) { this.color = aColor; }
    }
    
    public abstract void draw();
} // end class
