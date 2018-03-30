package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Label curr_element;

    @FXML
    TextField text1;

    @FXML
    TextField text2;

    @FXML
    TextField text3;

    String text1_currState = "";
    String text2_currState = "";
    String text3_currState = "";

    String text1_prevState = "";
    String text2_prevState = "";
    String text3_prevState = "";

    int currState = 0;

    public ArrayList<Integer> arrayList;

    private ArrayList<Integer> initArrList(){
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i < 27; i++){
            res.add(i*3-1);
        }
        return res;
    }

    private int pointer = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arrayList = initArrList();
    }

    public void nextAction(ActionEvent event) {
        if (pointer < arrayList.size() - 1) {
            pointer++;
            curr_element.setText("id: " + (pointer) + "; value: " + arrayList.get(pointer));
        }
    }

    public void previousAction(ActionEvent event) {
        if (pointer > 0) {
            pointer--;
            curr_element.setText("id: " + (pointer) + "; value: " + arrayList.get(pointer));
        }
    }


    public void lookUpAction(ActionEvent actionEvent) {
        pointer = 0;
        curr_element.setText("id: " + (pointer) + "; value: " + arrayList.get(pointer));
    }

    public void changeAction(ActionEvent actionEvent) {
        text1_prevState = text1_currState;
        text2_prevState = text2_currState;
        text3_prevState = text3_currState;

        text1_currState = text1.getText();
        text2_currState = text2.getText();
        text3_currState = text3.getText();

        if (Integer.parseInt(text1_currState)<0 || Integer.parseInt(text1_currState)> arrayList.size()-1 ){
            text1_currState = "0";
        }

        if (Integer.parseInt(text3_currState)> arrayList.size()-1 || Integer.parseInt(text3_currState) <0){
            text3_currState = "" + (arrayList.size()-1);
        }

        System.out.println("1=" + text1_currState);
        System.out.println("2=" + text2_currState);
        System.out.println("3=" + text3_currState);
        System.out.println("currState= " + currState);

        if ((text2.getText().equals("")&& text1.getText().equals("") && text3.getText().equals("") && currState==0) ||
                ((text2.getText().equals("") && currState > 0))){
            System.exit(0);
        }

        if (text1_currState.equals(text1_prevState) && text3_currState.equals(text3_prevState)){
            currState++;
            arrayList.add(0);
            for (int i = arrayList.size()-1; i >= currState;  i--){
                arrayList.set(i,arrayList.get(i-1));
            }
            arrayList.set(currState, Integer.parseInt(text2_currState));
        } else {
            currState=0;
            for (int i = Integer.parseInt(text1_currState); i <= Integer.parseInt(text3_currState); i++){
                arrayList.set(i, Integer.parseInt(text2_currState));
            }
        }
    }
}
