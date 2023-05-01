package sample;

import java.net.URL;
import java.util.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TableView<Map> tableView;
    @FXML private TextField tf_Agent;
    @FXML private Button btn_addAlter;
    @FXML private Button btn_result;
    @FXML private Button btn_addAgent;
    @FXML private Button btn_clear;
    @FXML private TextArea textArea_result;
    String[] alterChars = new String[]{"A","B","C","D","E","F","G","H","I"};
    private final ObservableList<Map> obsList = FXCollections.observableArrayList();
    int rowAgent;
    int colAlter;

    @FXML
    void initialize() {
        rowAgent = 0;
        colAlter = 0;

        btn_addAlter.setOnAction(event -> {
            String mapChar = alterChars[colAlter];
            TableColumn<Map, String> column = new TableColumn<>( mapChar);
            column.setCellValueFactory(new MapValueFactory(mapChar));
            column.setPrefWidth(60);
            tableView.getColumns().add(column);
            tableView.refresh();
            colAlter++;
        });

        btn_addAgent.setOnAction(event -> {
            String alters = tf_Agent.getText() +" ";
            String[] arrAlters = alters.split(" ");
            ObservableList<Map> allData = tableView.getItems();
//            int offset = allData.size();
            Map<String, String> dataRow = new HashMap<>();
            for (int j = 0; j < tableView.getColumns().size(); j++) {
                String mapKey = Character.toString((char) ('A' + j));
//                String mapKey = arrAlters[j];
//                String value1 = mapKey + (offset + 1) + arrAlters[j];
                String value1 = arrAlters[j];
                dataRow.put(mapKey, value1);
            }
            allData.add(dataRow);
            tableView.setItems(allData);
            tableView.refresh();
            tf_Agent.setText("");
            rowAgent++;
        });

        btn_result.setOnAction(event -> {
            Results results = new Results();
            ObservableList<Map> allData = tableView.getItems();
            String data = "";
            for (int i = 0; i < allData.size(); i++) {
                data += allData.get(i) + ";";
                System.out.println(allData.get(i));
            }
            String resultsText = results.calcResult(data,colAlter, rowAgent);
            textArea_result.setText(resultsText);
        });

        btn_clear.setOnAction(event -> {
            rowAgent = 0;
            colAlter = 0;
            textArea_result.setText("");
            tableView.getColumns().clear();
            tableView.getItems().clear();
            tableView.refresh();
        });
    }
}

