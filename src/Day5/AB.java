package Day5;

abstract class Shape{
    abstract void perim();
    public void area(){
        System.out.println("Area of rectangle");
    }
}
class circle extends Shape{
    public void perim(){
        System.out.println("Perimeter of circle");
    }
}
class Rectangle extends Shape{
    @Override
    void perim() {
        System.out.println("Perimeter of rectangle");
    }
}
public class AB {
    public static void main(String[] args) {
        Shape rec = new Rectangle();
        rec.perim();
        Shape cir = new circle();
        cir.perim();
    }

}