import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    public void setBtnAction(Button btn, HttpJSONService http, ObservableList<TableData> data, Label lastCheck, Label lastGet, Label firstGet, Label numVehicles, ObservableList<PieChart.Data> pieChartData, BarChart bc){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
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
                    linhaData.forEach((linha, numVehicles) ->{
                        moving.getData().add(new XYChart.Data(linha, numVehicles));
                    });
                    bc.getData().add(moving);
                }
            }
        });
    }
}
