import javafx.scene.shape.Circle;
import java.util.ArrayList;

public class Vertice {
    private Circle circle;
    private ArrayList<Aresta> connections;

    public Vertice(Circle c) {
        this.circle = c;
        this.connections = new ArrayList<Aresta>();
    }

    public void addLink(Aresta a){
        this.connections.add(a);
    }

    public Circle getVerticeCircle() {
        return circle;
    }

    public double getVerticeRadius(){
        return this.circle.getRadius();
    }

    public double getVerticeX(){
        return this.circle.getCenterX();
    }

    public double getVerticeY(){
        return this.circle.getCenterY();
    }
}