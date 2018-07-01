import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    public void setBtnAction(HttpJSONService http, ObservableList<TableData> data, Label lastCheck, Label lastGet, Label firstGet, Label numVehicles, ObservableList<PieChart.Data> pieChartData, BarChart bc){
        Map json = null;
        try {
            json = http.sendGet("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Connection failed");
            alert.setContentText("Please check your Internet connection!");
            alert.showAndWait();
        }
        updateValues(json, data, lastCheck, lastGet, firstGet, numVehicles, pieChartData, bc);
    }

    public void setBtnAction(ObservableList<TableData> data, Label lastCheck, Label lastGet, Label firstGet, Label numVehicles, ObservableList<PieChart.Data> pieChartData, BarChart bc, Stage stage){
        Map json = null;
        File jsonFile = getArchive(stage);
        try {
            FileReader fileReader = new FileReader(jsonFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            JSONParsing jsonParsing = new JSONParsing();
            json = jsonParsing.parseJSON(bufferedReader.readLine());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Something gone wrong");
            alert.setContentText("Try again with another archive");
            alert.showAndWait();
        }
        updateValues(json, data, lastCheck, lastGet, firstGet, numVehicles, pieChartData, bc);
    }

    private void updateValues (Map json, ObservableList<TableData> data, Label lastCheck, Label lastGet, Label firstGet, Label numVehicles, ObservableList<PieChart.Data> pieChartData, BarChart bc){
        data.clear();
        if (json != null){
            List JSonData = (List) json.get("DATA");
            LinkedList<TableData> tempData = new LinkedList<TableData>();
            JSonData.forEach(row -> {
                TableData tData = new TableData(((List) row).get(0).toString(),
                        ((List) row).get(1).toString(),
                        ((List) row).get(2).toString(),
                        Float.parseFloat(((List) row).get(3).toString()),
                        Float.parseFloat(((List) row).get(4).toString()),
                        Float.parseFloat(((List) row).get(5).toString()));
                data.add(tData);
                tempData.add(tData);
            });
            lastCheck.setText(" Última leitura de dados: " + new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
            numVehicles.setText(" Número de veículos: " + tempData.size());
            lastGet.setText(" Data-hora menos recente: " + tempData.getFirst().getDataHora());
            firstGet.setText(" Data-hora mais recente: " + tempData.getLast().getDataHora());
            pieChartData.get(0).setPieValue(tempData.stream().filter(x -> x.getVelocidade() == 0).collect(Collectors.toList()).size());
            pieChartData.get(1).setPieValue(tempData.stream().filter(x -> x.getVelocidade() > 0).collect(Collectors.toList()).size());

            Map<String, Long> linhaData = tempData.stream().filter(x -> x.getVelocidade() > 0 && !x.getLinha().isEmpty()).collect(Collectors.groupingBy(TableData::getLinha, Collectors.counting()));

            XYChart.Series moving = new XYChart.Series();
            moving.setName("Veículos em Movimento");
            linhaData.forEach((linha, num) ->{
                moving.getData().add(new XYChart.Data(linha, num));
            });
            bc.getData().clear();
            bc.getData().add(moving);
        }
    }

    private File getArchive(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir arquivo .json");
        File jsonFile = fileChooser.showOpenDialog(stage);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
        return jsonFile;
    }
}
