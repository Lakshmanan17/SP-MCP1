package org.example.ohrm;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

public class FailureScreenshotExtension implements AfterTestExecutionCallback {
    private Page page;

    public FailureScreenshotExtension captureFrom(Page page) {
        this.page = page;
        return this;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        boolean failed = context.getExecutionException().isPresent();
        if (failed && page != null) {
            byte[] png = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            String testName = "Failed:" + context.getDisplayName();
            Allure.addAttachment(testName, new ByteArrayInputStream(png));
        }
    }

}