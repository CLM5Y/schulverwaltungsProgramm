import model.JSONConfig;
import service.Menue;
import service.JSONservice;
import org.json.JSONObject;

public class main {
    public static void main(String[] args) {

        Menue.asciiArt();

        while(true) {
            Menue.showMenue();
        }
    }
}
