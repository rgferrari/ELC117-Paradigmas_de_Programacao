import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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

    public void addAresta(Vertice v1, Vertice v2, Line l, String rgb) {
        Aresta a = new Aresta(v1, v2, l, rgb);
        this.arestas.add(a);
        v1.addLink(a);
        v2.addLink(a);
        this.numeroDeArestas++;
    }


    public void addVertice(Circle c, String rgb){
        Vertice v = new Vertice(c, rgb);
        vertices.add(v);
        this.numeroDeVertices++;
    }

    public boolean dentroDoCirulo(double x, double y, Circle circulo){
        double distX = x - circulo.getCenterX();
        double distY = y - circulo.getCenterY();
        double distancia = Math.sqrt(distX*distX + distY*distY);
        if (distancia <= circulo.getRadius()){
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
            double distancia = Math.sqrt(distX*distX + distY*distY);
            if (distancia <= raio + v.getVerticeRadius()){
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
        return (a1.getInicio().equals(a2.getInicio()) || a1.getInicio().equals(a2.getFim()) ||
                a1.getFim().equals(a2.getInicio()) || a1.getFim().equals(a2.getFim()));
    }

    // Checa se ja existe uma aresta entre os dois pontos
    public boolean mesmaAresta(Line line){
        for(Aresta a1: this.arestas){
            if((a1.getInicio().equals(buscarVertice(line.getStartX(), line.getStartY())) && a1.getFim().equals(buscarVertice(line.getEndX(), line.getEndY())) || a1.getFim().equals(buscarVertice(line.getStartX(), line.getStartY())) && a1.getInicio().equals(buscarVertice(line.getEndX(), line.getEndY())))){
                return true;
            }
        }
        return false;
    }

    // Verifica se há intersecao entre duas arestas
    private boolean intersecao(Aresta a1, Aresta a2){
        return (Line2D.linesIntersect(a1.getInicio().getVerticeX(), a1.getInicio().getVerticeY(), a1.getFim().getVerticeX(), a1.getFim().getVerticeY(),
                a2.getInicio().getVerticeX(), a2.getInicio().getVerticeY(), a2.getFim().getVerticeX(), a2.getFim().getVerticeY()));
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
                writer.println("<line x1=\""+a.getInicio().getVerticeX()+"\" y1=\""+a.getInicio().getVerticeY()+"\" x2=\""+a.getFim().getVerticeX()+"\" y2=\""+a.getFim().getVerticeY()+"\" style=\"stroke:"+a.getHexa()+";stroke-width:"+a.getLine().getStrokeWidth()+"\" />");
            }
            for (Vertice v: this.vertices){
                writer.println("<circle cx=\""+v.getVerticeX()+"\" cy=\""+v.getVerticeY()+"\" r=\""+v.getVerticeRadius()+"\" fill=\""+v.getHexa()+"\" />");
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


    public Aresta getAresta(double cX, double cY){
        for (Aresta a: this.arestas){
            if(a.getInicio().getVerticeCircle().getCenterX() == cX && a.getInicio().getVerticeCircle().getCenterY() == cY){
                return a;
            }
            if(a.getFim().getVerticeCircle().getCenterX() == cX && a.getFim().getVerticeCircle().getCenterY() == cY){
                return a;
            }
        }
        return null;
    }

    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    public int getNumeroDeArestas() {
        return numeroDeArestas;
    }

    public ArrayList<Aresta> getArestas(){
        return arestas;
    }

    public ArrayList<Vertice> getVertices(){
        return vertices;
    }
    

    public void gerarGrafo(int fase){
        Random rand = new Random();

        for (int i = 0; i < fase; i++) {
            this.addVertice(new Circle(rand.nextInt(300) + 50.0, rand.nextInt(400) + 50.0, 10), "#ff0000");
            if(i>1){
                Vertice v = buscarVertice(vertices.get(i).getVerticeX(),vertices.get(i).getVerticeY());
                Vertice v1 = buscarVertice(vertices.get(i-1).getVerticeX(),vertices.get(i-1).getVerticeY());
                Vertice v2 = buscarVertice(vertices.get(i-2).getVerticeX(),vertices.get(i-2).getVerticeY());
                this.addAresta(v, v1, new Line(v.getVerticeX(), v.getVerticeY(), v1.getVerticeX(), v1.getVerticeY()), "#ff0000");
                this.addAresta(v, v2, new Line(v.getVerticeX(), v.getVerticeY(), v2.getVerticeX(), v2.getVerticeY()), "#ff0000");
            }
        }
        /*
        for (Vertice vIni : vertices) {
            for (Vertice vFim : vertices) {
                if (vIni == vFim)
                    continue;
                if (arestas.size() == (3 * vertices.size() - 6))
                    continue;
                this.addAresta(vIni, vFim, new Line(vIni.getVerticeX(), vIni.getVerticeY(), vFim.getVerticeX(), vFim.getVerticeY()), "#ff0000");
            }
        }
        */
    }

}
