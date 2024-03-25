package org.view;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.model.AES;
import org.model.CipherKey;
import org.model.FileReader;
import org.model.textBuffer;

import java.nio.charset.StandardCharsets;

public class AesViewController {

    @FXML
    private Button keyButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button confirmButton;

    @FXML
    private Button plaintextFileButton;

    @FXML
    private Button crypttextFileButton;

    @FXML
    private Button encrypt;

    @FXML
    private Button decrypt;

    @FXML
    private Button plaintextSaveButton;

    @FXML
    private Button crypttextSaveButton;

    @FXML
    private Button confirmPlaintextButton;
    @FXML
    private Button confirmEncryptedtextButton;

    @FXML
    private TextField keyInput;

    @FXML
    private TextField plaintextFileInput;

    @FXML
    private TextField plaintextSaveInput;

    @FXML
    private TextField crypttextFileInput;

    @FXML
    private TextField crypttextSaveInput;

    @FXML
    private TextArea plaintextArea;

    @FXML
    private TextArea crypttextArea;

    private Stage stage;
    private Scene scene;
    private Parent root;

    CipherKey key = new CipherKey();

    textBuffer plaintext;

    textBuffer encodedText;


    public void generateCipherKey(MouseEvent event){
        key.generate(256);
        keyInput.setText(bytesToHex(key.getCipherKey()));
    }

    public void setCipherKey(MouseEvent event){
        key.setCipherKey(keyInput.getText());
    }

    public void loadPlaintextFile(MouseEvent event) throws Exception {
        FileReader plaintextFile = new FileReader(plaintextFileInput.getText());
        plaintextFile.read();
        plaintext = new textBuffer(plaintextFile.getBytes());
        String text = new String(plaintext.getBytes());
        plaintextArea.setText(text);
    }

    public void loadEncryptedtextFile(MouseEvent event) throws Exception {
        FileReader crypttextFile = new FileReader(crypttextFileInput.getText());
        crypttextFile.read();
        encodedText = new textBuffer(crypttextFile.getBytes());
        String text = new String(encodedText.getBytes());
        crypttextArea.setText(text);
    }

    public void inputPlaintext(MouseEvent event){
        String plaintextInput = plaintextArea.getText();
        byte[] byteArray = plaintextInput.getBytes();
        plaintext = new textBuffer(byteArray);
    }

    public void inputCryptedtext(MouseEvent event){

        String crypttextInput = crypttextArea.getText();
        byte[] byteArray = crypttextInput.getBytes();
        encodedText = new textBuffer(byteArray);

    }

    public void encoding(MouseEvent event){
        int textLength = plaintext.getBytes().length;
        encodedText = new textBuffer(new byte[textLength]);
        int blockCounter = textLength/16;
        byte[][] matrix = new byte[4][4];
        int plainIndex = 0;
        int encodedIndex = 0;
        for (int i = 0; i<blockCounter; i++){
            for (int j = 0; j<4; j++){
                for (int k = 0; k<4; k++){
                    matrix[j][k] = plaintext.getBytes()[plainIndex];
                    plainIndex++;

                }
            }
            byte[][] temp = AES.encode(matrix, key.getCipherKey());
            for (int p =0; p<4; p++){
                for (int r = 0; r<4; r++){
                    encodedText.getBytes()[encodedIndex] = temp[p][r];
                    encodedIndex++;
                }
            }
        }

        String text = new String(encodedText.getBytes());
        crypttextArea.setText(text);

    }

    public void decoding(MouseEvent event){
        int textLength = encodedText.getBytes().length;
        plaintext = new textBuffer(new byte[textLength]);
        int blockCounter = textLength/16;
        byte[][] matrix = new byte[4][4];
        int plainIndex = 0;
        int encodedIndex = 0;
        for (int i = 0; i<blockCounter; i++){
            for (int j = 0; j<4; j++){
                for (int k = 0; k<4; k++){
                    matrix[j][k] = encodedText.getBytes()[encodedIndex];
                    encodedIndex++;

                }
            }
            byte[][] temp = AES.decode(matrix, key.getCipherKey());
            for (int p =0; p<4; p++){
                for (int r = 0; r<4; r++){
                    plaintext.getBytes()[plainIndex] = temp[p][r];
                    plainIndex++;
                }
            }
        }

        String text = new String(plaintext.getBytes());
        plaintextArea.setText(text);
    }



    @FXML
    void onExitClick(MouseEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }
}
