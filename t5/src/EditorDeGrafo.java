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
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;


import java.util.ArrayList;



public class EditorDeGrafo extends Application{

    private Color color = Color.RED;
    //private boolean option = true;
    private boolean arestaExiste = false;
    private int tamanho = 10;
    Circle c;
    Line l;


    @Override
    public void start(Stage stage){
        Grafo grafo = new Grafo();
        Pane pane = new Pane();
        ArrayList<Color> cores = new ArrayList<>();
        int[] tamanhos = new int[3];


        Label labelVertices = new Label("Vertices: " + grafo.getNumeroDeVertices());
        Label labelArestas = new Label("Arestas: " + grafo.getNumeroDeArestas());
        Label labelIntersecoes = new Label("Interseções: " + grafo.getNumeroDeIntersecoes());


        cores.add(Color.RED);
        cores.add(Color.ORANGERED);
        cores.add(Color.CRIMSON);

        ChoiceBox<String> cbCores = new ChoiceBox<>();
        cbCores.getItems().addAll("Vermelho", "Laranja", "Rosa");
        cbCores.setValue("Vermelho");

        cbCores.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                color = cores.get(newValue.intValue());
            }
        });

        cbCores.setTooltip(new Tooltip("Selecione a cor"));


        tamanhos[0] = 10;
        tamanhos[1] = 15;
        tamanhos[2] = 20;

        ChoiceBox<String> cbTamanhos = new ChoiceBox<>();
        cbTamanhos.getItems().addAll("Pequeno", "Médio", "Grande");
        cbTamanhos.setValue("Pequeno");

        cbTamanhos.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                tamanho = tamanhos[newValue.intValue()];
            }
        });

        cbTamanhos.setTooltip(new Tooltip("Selecione o tamanho"));




        ChoiceBox<String> cbTipo = new ChoiceBox<>();
        cbTipo.getItems().addAll("Vértice", "Aresta");
        cbTipo.setValue("Vértice");


        cbTipo.setTooltip(new Tooltip("Selecione a ferramenta"));

        /*--------------Botões do Menu de Ferramentas-------------*/
        /*
        Button btnGrande = new Button("Grande");

        Button btnMedio = new Button("Médio");

        Button btnPequeno = new Button("Pequeno");

        Button btnVertice = new Button("Vértice");
        btnVertice.getStyleClass().add("btnVertice");

        Button btnAresta = new Button("Aresta");
        btnAresta.getStyleClass().add("btnAresta");

        Button btnLaranja = new Button("Laranja");
        btnLaranja.getStyleClass().add("btnLaranja");

        Button btnVermelho = new Button("Vermelho");
        btnVermelho.getStyleClass().add("btnVermelho");

        Button btnRosa = new Button("Rosa");
        btnRosa.getStyleClass().add("btnRosa");
        */
        /*----------------------Botões do Menu---------------------*/

        Button btnNovo = new Button("Novo Grafo");

        Button btnSalvar = new Button("Salvar");

        Button btnSair = new Button("Sair");

        /*----------------------Ação dos Botões--------------------*/
        /*
        btnGrande.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                tamanho = 20;
            }
        });

        btnMedio.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                tamanho = 15;
            }
        });

        btnPequeno.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                tamanho = 10;
            }
        });

        btnVertice.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                option = true;
            }
        });

        btnAresta.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                option = false;
            }
        });
        */
        btnSair.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        btnNovo.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //grafo.resetN();
                Grafo grafo = new Grafo();
                start(stage);
                labelVertices.setText("Vértices: " + grafo.getNumeroDeVertices());
                labelArestas.setText("Vértices: " + grafo.getNumeroDeArestas());
                labelIntersecoes.setText("Interseções: " + grafo.getNumeroDeIntersecoes());
            }
        });

        btnSalvar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                grafo.criarSVG();
            }
        });
    /*
        btnLaranja.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                color = Color.ORANGERED;
            }
        });
        btnVermelho.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                color = Color.RED;
            }
        });
        btnRosa.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                color = Color.CRIMSON;
            }
        });
    */
        /*---------------------Ações do Mouse-----------------------*/

        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (cbTipo.getValue() == "Vértice"){
                    if(grafo.verificarPosicaoVertice(e.getX(), e.getY(), tamanho)){
                        c = new Circle(e.getX(), e.getY(), tamanho, color);
                        grafo.addVertice(c);
                        pane.getChildren().add(c);
                        labelVertices.setText("Vértices: " + grafo.getNumeroDeVertices());
                    }
                }
                else {
                    if (grafo.verificarPosicaoAresta(e.getX(), e.getY())){
                        double xIni = grafo.buscarVertice(e.getX(), e.getY()).getVerticeX();
                        double yIni = grafo.buscarVertice(e.getX(), e.getY()).getVerticeY();
                        l = new Line(xIni, yIni, e.getX(), e.getY());
                        l.setStrokeWidth(tamanho);
                        l.setStroke(color);
                        l.setStrokeLineCap(StrokeLineCap.ROUND);
                        pane.getChildren().add(l);
                        arestaExiste = true;
                    }
                    else{
                        arestaExiste = false;
                    }
                }
            }
        });

        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (cbTipo.getValue() == "Aresta" && arestaExiste == true){
                    l.setEndX(e.getX());
                    l.setEndY(e.getY());
                }
            }
        });

        pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (cbTipo.getValue() == "Aresta"){
                    if(grafo.verificarPosicaoAresta(e.getX(), e.getY())){
                        l.setEndX(grafo.buscarVertice(e.getX(), e.getY()).getVerticeX());
                        l.setEndY(grafo.buscarVertice(e.getX(), e.getY()).getVerticeY());
                        if (!(l.getStartX() == l.getEndX() && l.getStartY() == l.getEndY())){
                            grafo.addAresta(grafo.buscarVertice(l.getStartX(), l.getStartY()), grafo.buscarVertice(l.getEndX(), l.getEndY()), l);
                            labelArestas.setText("Arestas: " + grafo.getNumeroDeArestas());
                            labelIntersecoes.setText("Interseções: " + grafo.getNumeroDeIntersecoes());
                            arestaExiste = false;
                        }
                        else{
                            pane.getChildren().remove(l);
                            arestaExiste = false;
                        }
                    }
                    else if(arestaExiste){
                        pane.getChildren().remove(l);
                        arestaExiste = false;
                    }
                }
            }
        });

        /*------------------------Áreas da tela---------------------------*/

        VBox vBox = new VBox();
        vBox.getChildren().setAll(btnNovo, btnSalvar, btnSair, cbTipo, cbCores, cbTamanhos, labelVertices, labelArestas, labelIntersecoes);
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
