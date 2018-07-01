import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableData {
    private SimpleStringProperty dataHora;
    private SimpleStringProperty ordem;
    private SimpleStringProperty linha;
    private SimpleFloatProperty latitude;
    private SimpleFloatProperty longitude;
    private SimpleFloatProperty velocidade;

    public TableData(String dataHora, String ordem, String linha, Float latitude, Float longitude, Float velocidade) {
        this.dataHora = new SimpleStringProperty(dataHora);
        this.ordem = new SimpleStringProperty(ordem);
        this.linha = new SimpleStringProperty(linha);
        this.latitude = new SimpleFloatProperty(latitude);
        this.longitude = new SimpleFloatProperty(longitude);
        this.velocidade = new SimpleFloatProperty(velocidade);
    }

    public SimpleStringProperty dataHoraProperty(){
        return dataHora;
    }
    public String getDataHora() {
        return dataHora.get();
    }
    public void setDataHora(String dataHora) {
        this.dataHora.set(dataHora);
    }
    public SimpleStringProperty ordemProperty(){
        return ordem;
    }
    public String getOrdem() {
        return ordem.get();
    }
    public void setOrdem(String ordem) {
        this.ordem.set(ordem);
    }
    public SimpleStringProperty linhaProperty(){
        return linha;
    }
    public String getLinha() {
        return linha.get();
    }
    public void setLinha(String linha) {
        this.linha.set(linha);
    }
    public SimpleFloatProperty latitudeProperty(){
        return latitude;
    }
    public Float getLatitude() {
        return latitude.get();
    }
    public void setLatitude(Float latitude) {
        this.latitude.set(latitude);
    }
    public SimpleFloatProperty longitudeProperty(){
        return longitude;
    }
    public Float getLongitude() {
        return longitude.get();
    }
    public void setLongitude(Float longitude) {
        this.longitude.set(longitude);
    }
    public SimpleFloatProperty velocidadeProperty(){
        return velocidade;
    }
    public Float getVelocidade() {
        return velocidade.get();
    }
    public void setVelocidade(Float velocidade) {
        this.velocidade.set(velocidade);
    }
}