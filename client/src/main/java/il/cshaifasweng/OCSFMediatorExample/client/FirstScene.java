/**
 * Sample Skeleton for 'firstscene.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class FirstScene {

    @FXML // fx:id="backgroundimg"
    private ImageView backgroundimg; // Value injected by FXMLLoader

    @FXML // fx:id="imageanchor"
    private AnchorPane imageanchor; // Value injected by FXMLLoader

    @FXML // fx:id="vboxbuttons"
    private VBox vboxbuttons; // Value injected by FXMLLoader

    @FXML
    void initialize() {

    }
}
