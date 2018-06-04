import javafx.scene.shape.Line;

public class Aresta {
    private Vertice inicio;
    private Vertice fim;
    private Line l;
    String hexa = new String();

    public Aresta(Vertice inicio, Vertice fim, Line l, String hexa) {
        this.inicio = inicio;
        this.fim = fim;
        this.l = l;
        this.hexa = hexa;
    }

    public Vertice getInicio() {
        return inicio;
    }

    public Vertice getFim() {
        return fim;
    }

    public Line getLine() {
        return l;
    }

    public String getHexa(){ return hexa;}
}