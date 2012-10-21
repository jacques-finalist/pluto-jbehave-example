package sample.code.integration;

import static com.google.common.collect.Lists.newArrayList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

import java.util.List;

import org.jbehave.core.io.StoryFinder;

public class LoginStories extends AbstractWebStories {

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(
                this.getClass()).getFile(), newArrayList("**/stories/login.story"), null);
    }

}
