import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Grafo {

    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestas;
    private int numeroDeVertices;
    private int numeroDeArestas;

    public Grafo() {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
        this.numeroDeVertices = 0;
        this.numeroDeArestas = 0;
    }

    public void addAresta(Vertice v1, Vertice v2, Line l) {
        Aresta a = new Aresta(v1, v2, l);
        this.arestas.add(a);
        v1.addLink(a);
        v2.addLink(a);
        this.numeroDeArestas++;
    }


    public void addVertice(Circle c){
        Vertice v = new Vertice(c);
        vertices.add(v);
        this.numeroDeVertices++;
    }

    public boolean dentroDoCirulo(double x, double y, Circle circ){
        double distance = Math.hypot(x-circ.getCenterX(), y-circ.getCenterY());
        if (distance <= circ.getRadius() + 4){
            return true;
        }else{
            return false;
        }
    }

    // For Each que verifica se a aresta esta dentro de algum vertice da lista
    public boolean verificarPosicaoAresta(double x, double y){
        for (Vertice v: this.vertices){
            if (dentroDoCirulo(x, y, v.getVerticeCircle())){
                return true;
            }
        }
        return false;
    }

    // For Each que verifica se o Vertice esta em cima de algum outro vertice
    public boolean verificarPosicaoVertice(double x, double y, double raio){
        for (Vertice v: this.vertices){
            double distX = x - v.getVerticeX();
            double distY = y - v.getVerticeY();
            double distance = Math.sqrt(distX*distX + distY*distY);
            if (distance <= raio + v.getVerticeRadius()){
                return false;
            }
        }
        return true;
    }

    public Vertice buscarVertice(double x, double y){
        for (Vertice v: this.vertices){
            if (dentroDoCirulo(x, y, v.getVerticeCircle())){
                return v;
            }
        }
        return null;
    }

    // Verifica se as arestas estao no mesmo vertice
    private boolean mesmoVertice(Aresta a1, Aresta a2){
        return (a1.getIni().equals(a2.getIni()) || a1.getIni().equals(a2.getFim()) ||
                a1.getFim().equals(a2.getIni()) || a1.getFim().equals(a2.getFim()));
    }

    // Checa se ja existe uma aresta entre os dois pontos
    public boolean mesmaAresta(Line line){
        for(Aresta a1: this.arestas){
            if((a1.getIni().equals(buscarVertice(line.getStartX(), line.getStartY())) && a1.getFim().equals(buscarVertice(line.getEndX(), line.getEndY())) || a1.getFim().equals(buscarVertice(line.getStartX(), line.getStartY())) && a1.getIni().equals(buscarVertice(line.getEndX(), line.getEndY())))){
                return true;
            }
        }
        return false;
    }

    // Verifica se há intersecao entre duas arestas
    private boolean intersecao(Aresta a1, Aresta a2){
        return (Line2D.linesIntersect(a1.getIni().getVerticeX(), a1.getIni().getVerticeY(), a1.getFim().getVerticeX(), a1.getFim().getVerticeY(),
                a2.getIni().getVerticeX(), a2.getIni().getVerticeY(), a2.getFim().getVerticeX(), a2.getFim().getVerticeY()));
    }

    public int getNumeroDeIntersecoes(){
        int cont = 0;
        for (Aresta a1: this.arestas){
            for (Aresta a2: this.arestas){
                if (mesmoVertice(a1, a2)){
                    continue;
                }
                if (intersecao(a1, a2)){
                    cont++;
                }
            }
        }
        return cont/2;
    }

    public void criarSVG(){
        try {
            PrintWriter writer = new PrintWriter(new Date().getTime() + ".svg", "UTF-8");
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<svg height=\"1366\" width=\"768\">");
            for (Aresta a: this.arestas){
                writer.println("<line x1=\""+a.getIni().getVerticeX()+"\" y1=\""+a.getIni().getVerticeY()+"\" x2=\""+a.getFim().getVerticeX()+"\" y2=\""+a.getFim().getVerticeY()+"\" style=\"stroke: rgb(255,0,0);stroke-width:"+a.getLinha().getStrokeWidth()+"\" />");
            }
            for (Vertice v: this.vertices){
                writer.println("<circle cx=\""+v.getVerticeX()+"\" cy=\""+v.getVerticeY()+"\" r=\""+v.getVerticeRadius()+"\" stroke=\"green\" stroke-width=\""+v.getVerticeCircle().getStrokeWidth()+"\" fill=\"\" />");
            }
            writer.println("</svg>");
            writer.println("</body>");
            writer.println("</html>");
            writer.close();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    public int getNumeroDeArestas() {
        return numeroDeArestas;
    }
}
