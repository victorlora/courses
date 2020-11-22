import java.awt.Color;

public class Circle extends Shape {
    public void draw(){}
    public Circle() { super(); }
    
    public Circle(Color c) { super(c);}
    
    public void main(String[] args){
        Shape  s1 = new Circle();  // part d.
    }

} // end class