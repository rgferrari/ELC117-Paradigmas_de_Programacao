import javafx.scene.shape.Circle;
import java.util.ArrayList;

public class Vertice {
    private Circle circle;
    private ArrayList<Aresta> connections;
    String rgb = new String();

    public Vertice(Circle c, String rgb) {
        this.circle = c;
        this.rgb = rgb;
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

    public String getRgb(){ return this.rgb; }

}

