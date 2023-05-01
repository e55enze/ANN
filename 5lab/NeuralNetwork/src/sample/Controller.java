package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Canvas canvas;
    @FXML private Button buttonCheck;
    @FXML public  ImageView imageView;
    @FXML private TextField tfResult;
    @FXML private TextField tfTarget;
    @FXML private Button buttonTrain;
    @FXML private Rectangle frameRect;
    GraphicsContext gc;
    CheckPicture CheckPicture;
    Image image;
    MLP mlp;

    @FXML
    void initialize() {
        frameRect.setFill(Color.TRANSPARENT);
        CheckPicture = new CheckPicture();
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,280,280);

        canvas.setOnMouseDragged(e -> {
            image = canvas.snapshot(null, null);
            image = gc.getCanvas().snapshot(null, null);
            imageView.setImage(image);
            gc.setFill(Color.BLACK);
            gc.fillOval(e.getX(), e.getY(), 35, 35);
        });

        buttonCheck.setOnAction(event-> {
            image = gc.getCanvas().snapshot(null, null);
            int  result = 0;
            String target = tfTarget.getText();

            if(target == null || target.equals("")){
                target = "";
            }
            try {
                 result = CheckPicture.getResult(image, mlp, target);
            } catch (IOException e) {
                e.printStackTrace();
            }
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.WHITE);
            gc.fillRect(0,0,280,280);
            tfResult.setText(result+"");
            Image image2 = CheckPicture.img;
            imageView.setImage(image2);
        });

        buttonTrain.setOnAction(event -> {
            Train train = new Train();
            try {
                mlp  = train.goTrain();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}