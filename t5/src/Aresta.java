import javafx.scene.shape.Line;

public class Aresta {
    private Vertice ini;
    private Vertice fim;
    private Line l;

    public Aresta(Vertice ini, Vertice fim, Line l) {
        this.ini = ini;
        this.fim = fim;
        this.l = l;
    }

    public Vertice getIni() {
        return ini;
    }

    public Vertice getFim() {
        return fim;
    }

    public Line getLinha() {
        return l;
    }
}