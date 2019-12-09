package src;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import src.pretender.impl.AnyKeySitePretender;

public class Main {

    private static Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("src/main/resources/geckodriver-v0.26.0-win64/geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", path.toString());
        int workForSeconds = 10;
        List<Runnable> workPretenders = new ArrayList<>();
        workPretenders.add(new AnyKeySitePretender(AnyKeySitePretender.SiteUrl.GEEK_TYPER, workForSeconds, AnyKeySitePretender.WindowPosition.CENTER_RIGHT));
        workPretenders.add(new AnyKeySitePretender(AnyKeySitePretender.SiteUrl.HACKER_TYPER, workForSeconds, AnyKeySitePretender.WindowPosition.CENTER_LEFT));
        workPretenders.add(new AnyKeySitePretender(AnyKeySitePretender.SiteUrl.GEEK_TYPER2, workForSeconds, AnyKeySitePretender.WindowPosition.CENTER));
        try {
            workPretenders.forEach(t -> new Thread(t).start());
        } catch (Exception e) {
            logger.info("Work interrupted! Oh holly molly!" + e.getMessage());
        }
    }
}
