import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class View extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private HttpJSONService http = new HttpJSONService();

    private TableView<TableData> table = new TableView<TableData>();

    private final ObservableList<TableData> data =
            FXCollections.observableArrayList();


    Controller controller = new Controller();

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(new Group());

        final Label label = new Label("Onibuzinho");
        label.setFont(new Font("Comics Sans", 20));

        Label lastCheck = new Label(" Última leitura de dados:  ");
        Label lastGet = new Label(" Data-hora mais recente:  ");
        Label firstGet = new Label(" Data-hora menos recente:  ");
        Label numVehicles = new Label(" Número de veículos:  ");

        lastCheck.setStyle("-fx-font-size: 15;");
        lastGet.setStyle("-fx-font-size: 15;");
        firstGet.setStyle("-fx-font-size: 15;");
        numVehicles.setStyle("-fx-font-size: 15;");

        lastCheck.setPadding(new Insets(0, 20, 0, 0));
        lastGet.setPadding(new Insets(0, 20, 0, 0));
        firstGet.setPadding(new Insets(0, 20, 0, 0));
        numVehicles.setPadding(new Insets(0, 20, 0, 0));

        TableColumn<TableData,String> dataCol = new TableColumn<TableData,String>("Data");
        dataCol.setCellValueFactory(cellData -> cellData.getValue().dataHoraProperty());

        TableColumn<TableData,String> ordemCol = new TableColumn<TableData,String>("Ordem");
        ordemCol.setCellValueFactory(cellData -> cellData.getValue().ordemProperty());

        TableColumn<TableData,String> linhaCol = new TableColumn<TableData,String>("Linha");
        linhaCol.setCellValueFactory(cellData -> cellData.getValue().linhaProperty());

        TableColumn<TableData,Float> latitudeCol = new TableColumn<TableData,Float>("Latitude");
        latitudeCol.setCellValueFactory(cellData -> cellData.getValue().latitudeProperty().asObject());

        TableColumn<TableData,Float> longitudeCol = new TableColumn<TableData,Float>("Longitude");
        longitudeCol.setCellValueFactory(cellData -> cellData.getValue().longitudeProperty().asObject());

        TableColumn<TableData,Float> velocidadeCol = new TableColumn<TableData,Float>("Velocidade");
        velocidadeCol.setCellValueFactory(cellData -> cellData.getValue().velocidadeProperty().asObject());

        /* PIE CHART */
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Parados", 50),
                        new PieChart.Data("Movimento", 50));
        final PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Situação");

        /* Bar Chart */
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Veículos em Movimento");
        xAxis.setLabel("Linha");
        yAxis.setLabel("Quantidade");

        //XYChart.Series moving = new XYChart.Series();
        //moving.setName("Veículos em Movimento");
        /*
        moving.getData().add(new XYChart.Data("ola", 5));
        moving.getData().add(new XYChart.Data("ola2", 9));
        moving.getData().add(new XYChart.Data("olaza", 40));
        moving.getData().add(new XYChart.Data("asdfa", 52));
        moving.getData().add(new XYChart.Data("sadf", 12));

        bc.getData().add(moving);
        */
        //bc.setStyle("-fx-max-width: 100;");

        dataCol.setCellFactory(column -> {
            return new TableCell<TableData, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        Text text = new Text(item.toString());

                        text.wrappingWidthProperty().bind(widthProperty());
                        text.textProperty().bind(itemProperty());
                        this.setWrapText(true);
                        setGraphic(text);
                    }
                }
            };
        });

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(dataCol, ordemCol, linhaCol, latitudeCol, longitudeCol, velocidadeCol);

        table.setItems(data);

        Button btn = new Button("Atualizar");

        controller.setBtnAction(btn, http, data, lastCheck, lastGet, firstGet, numVehicles, pieChartData, bc);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, table, btn);

        vbox.applyCss();
        vbox.layout();



        HBox hBox = new HBox();
        hBox.getChildren().addAll(lastCheck, lastGet, firstGet, numVehicles);

        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(pieChart, bc);


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        borderPane.setRight(vBox1);
        borderPane.setBottom(hBox);

        stage.setScene(new Scene(borderPane, 1024, 576));
        stage.show();
    }
}
