package OrangeHRM;

import java.nio.file.Paths;

public class MyPlaywrightOptions implements OptionsFactory{
@Override
    public Options getOptions() {

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        String baseUrl = System.getProperty("baseUrl", "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        String apiBaseUrl = System.getProperty("apiBaseUrl", "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        Browser.NewContextOptions ctx=new Browser.NewContextOptions()
                .setBaseURL(baseUrl)
               // .setExtraHTTPHeaders(Map.of("Authorization", "Bearer " + System.getProperty("apiToken", "")));
                .setRecordVideoDir(Paths.get("target/videos/"));

        return new Options()
                .setHeadless(headless)
                .setContextOptions(ctx)
                .setBaseUrl(baseUrl)
                .setApiRequestOptions(api)
                .setApiBaseUrl(apiBaseUrl);

    }
}

