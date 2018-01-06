package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField queen;
    @FXML
    private TextArea showid;
    public void show(ActionEvent actionEvent) {
        showid.setText("");
        int queenSize = 0;
        if(queen.getText() != null){
            queenSize = Integer.parseInt(queen.getText());
        }
        new countThread(queenSize,showid).start();
    }


}
