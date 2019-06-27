    import javafx.application.Application;
    import javafx.stage.Stage;

    public class ApplicationModel extends Application {

        ApplicationModel model; // Model
        View view; // View + Controller

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) throws Exception {

            this.model = new ApplicationModel();

            // View + Controller
            this.view = new View(model, stage);
        }
    }


