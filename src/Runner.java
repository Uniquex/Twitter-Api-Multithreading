import java.util.ArrayList;

/**
 * Created by wvitz on 08.01.2017.
 */
public class Runner {

    public static void main(String[] args) {

        ArrayList<TwitterWorker> workers = new ArrayList<>();

        workers.add(new TwitterWorker("#CES2017"));
        workers.add(new TwitterWorker("#CSGO"));

    }
}
