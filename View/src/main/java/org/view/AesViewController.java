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
import org.model.TextBuffer;

import java.util.Arrays;
import java.util.Base64;

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

    TextBuffer plaintext;

    TextBuffer encodedText;


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
        String text = new String(plaintextFile.getBytes());
        plaintextArea.setText(text);
        plaintext = new TextBuffer(plaintextFile.getBytes());
        System.out.println(bytesToHex(plaintext.getBytes()));
    }

    public void loadEncryptedtextFile(MouseEvent event) throws Exception {
        FileReader crypttextFile = new FileReader(crypttextFileInput.getText());
        crypttextFile.read();
        byte[] byteArray = crypttextFile.getBytes();
        String text = new String(byteArray);
        crypttextArea.setText(text);
        encodedText = new TextBuffer(Base64.getDecoder().decode(byteArray));

    }

    public void inputPlaintext(MouseEvent event){
        String plaintextInput = plaintextArea.getText();
        byte[] byteArray = plaintextInput.getBytes();
        plaintext = new TextBuffer(byteArray);
    }

    public void inputCryptedtext(MouseEvent event){

        String crypttextInput = crypttextArea.getText();
        byte[] byteArray = crypttextInput.getBytes();
        encodedText = new TextBuffer(Base64.getDecoder().decode(byteArray));
        System.out.println(bytesToHex(encodedText.getBytes()));
    }

    public void encoding(MouseEvent event){
        int textLength = plaintext.getBytes().length;
        TextBuffer result = new TextBuffer(new byte[textLength]);

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
                    result.getBytes()[encodedIndex] = temp[p][r];
                    encodedIndex++;
                }
            }
        }
        encodedText = new TextBuffer(Base64.getEncoder().encode(result.getBytes()));
        System.out.println(bytesToHex(encodedText.getBytes()));
        String text = new String(encodedText.getBytes());
        crypttextArea.setText(text);

    }

    public void decoding(MouseEvent event){
        int textLength = encodedText.getBytes().length;
        TextBuffer result = new TextBuffer(encodedText.getBytes());
        plaintext = new TextBuffer(new byte[textLength]);
        int blockCounter = textLength/16;
        byte[][] matrix = new byte[4][4];
        int plainIndex = 0;
        int encodedIndex = 0;
        for (int i = 0; i<blockCounter; i++){
            for (int j = 0; j<4; j++){
                for (int k = 0; k<4; k++){
                    matrix[j][k] = result.getBytes()[encodedIndex];
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
        System.out.println(bytesToHex(plaintext.getBytes()));
        int addedBytes = plaintext.getBytes()[textLength-1];
        byte[] byteArray = Arrays.copyOf(plaintext.getBytes(), textLength-addedBytes);
        String text = new String(byteArray);
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
