import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;




public class EditorDeGrafo extends Application{

    int fase = 6;
    Circle c;
    Line l;

    @Override
    public void start(Stage stage){

        Grafo grafo = new Grafo();
        Pane pane = new Pane();




        grafo.gerarGrafo(fase);
        for(Aresta a: grafo.getArestas()){
            pane.getChildren().add(a.getLine());
        }
        for(Vertice v: grafo.getVertices()){
            pane.getChildren().add(v.getVerticeCircle());
        }

        Label labelIntersecoes = new Label("Interseções: " + grafo.getNumeroDeIntersecoes());


        /*----------------------Botões do Menu---------------------*/

        Button btnNext = new Button("Next");
        btnNext.setStyle("-fx-border-color: RED");

        Button btnShuffle = new Button("Shuffle");

        Button btnSair = new Button("Exit");

        /*----------------------Ação dos Botões--------------------*/

        btnSair.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        btnShuffle.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Grafo grafo = new Grafo();
                start(stage);
                grafo.gerarGrafo(fase);
                for(Aresta a: grafo.getArestas()){
                    pane.getChildren().add(a.getLine());
                }
                for(Vertice v: grafo.getVertices()){
                    pane.getChildren().add(v.getVerticeCircle());
                }
            }
        });

        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if( grafo.getNumeroDeIntersecoes() == 0){
                    fase++;
                    Grafo grafo = new Grafo();
                    start(stage);
                    grafo.gerarGrafo(fase);
                    for(Aresta a: grafo.getArestas()){
                        pane.getChildren().add(a.getLine());
                    }
                    for(Vertice v: grafo.getVertices()){
                        pane.getChildren().add(v.getVerticeCircle());
                    }
                }
                else{

                }
            }
        });

        /*---------------------Ações do Mouse-----------------------*/

        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(!grafo.verificarPosicaoVertice(e.getX(), e.getY(), 10)){
                    c = grafo.buscarVertice(e.getX(), e.getY()).getVerticeCircle();
                }
            }
        });

        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                c.setCenterX(e.getX());
                c.setCenterY(e.getY());
                for(Aresta a: grafo.buscarVertice(c.getCenterX(), c.getCenterY()).getConnections()){
                        Line l = a.getLine();
                        Vertice v = grafo.buscarVertice(c.getCenterX(), c.getCenterY());
                        if (v == a.getInicio()) {
                            l.setStartX(e.getX());
                            l.setStartY(e.getY());
                        }
                        if (v == a.getFim()) {
                            l.setEndX(e.getX());
                            l.setEndY(e.getY());
                        }
                }
            }
        });

        pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                labelIntersecoes.setText("Interseções: " + grafo.getNumeroDeIntersecoes());
                System.out.println(grafo.getNumeroDeIntersecoes());
                if(grafo.getNumeroDeIntersecoes() == 0){
                    final Stage NewGraph = new Stage();
                    NewGraph.initModality(Modality.NONE);
                    Pane warning = new Pane();
                    BorderPane passou = new BorderPane();
                    warning.setAccessibleText("ola");
                    HBox hBoxA = new HBox();
                    Text t1 = new Text("Você Passou");
                    hBoxA.getChildren().add(t1);
                    hBoxA.setAlignment(Pos.CENTER);
                    passou.setCenter(hBoxA);
                    Scene NewScene = new Scene(passou, 200, 100);
                    NewGraph.setScene(NewScene);
                    NewGraph.show();
                    btnNext.setStyle("-fx-border-color: GREEN");
                }
            }
        });

        /*------------------------Áreas da tela---------------------------*/

        VBox vBox = new VBox();
        vBox.getChildren().setAll(btnNext, btnShuffle, btnSair, labelIntersecoes);
        vBox.setAlignment(Pos.TOP_CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setLeft(vBox);

        Scene scene = new Scene(borderPane, 854, 480);
        scene.getStylesheets().add("controlStyle.css");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
