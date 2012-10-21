package sample.code.integration;

import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.web.selenium.DefaultWebDriverProvider;
import org.jbehave.web.selenium.WebDriverHtmlOutput;
import org.jbehave.web.selenium.WebDriverProvider;
import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;

import sample.code.integration.pages.Pages;
import sample.code.integration.step.LoginSteps;

public abstract class AbstractWebStories extends JUnitStories {

    private final WebDriverProvider driverProvider;
    private final Pages pages;
    private Configuration configuration;


    public AbstractWebStories() {
        super();
        driverProvider = new DefaultWebDriverProvider();
        pages = new Pages(driverProvider);
    }

    @Override
    public Configuration configuration() {
        configuration = new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass().getClassLoader()))
                .useStoryReporterBuilder(
                        new StoryReporterBuilder()
                                .withFailureTrace(true)
                                .withFormats(Format.ANSI_CONSOLE, Format.STATS,
                                        WebDriverHtmlOutput.WEB_DRIVER_HTML, Format.XML));

        return configuration;
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(),
                new WebDriverScreenshotOnFailure(driverProvider, configuration.storyReporterBuilder()),
                new LoginSteps(pages)).createCandidateSteps();
    }

}
