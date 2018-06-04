import javafx.scene.shape.Line;

public class Aresta {
    private Vertice ini;
    private Vertice fim;
    private Line l;
    String rgb = new String();

    public Aresta(Vertice ini, Vertice fim, Line l, String rgb) {
        this.ini = ini;
        this.fim = fim;
        this.l = l;
        this.rgb = rgb;
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

    public String getRgb(){ return rgb;}
}