package uz.pdp.springadvanced;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CommandPromptProvider implements PromptProvider {
    @Override
    public AttributedString getPrompt() {
        return new AttributedString("pdp-shell =>", AttributedStyle.BOLD.background(AttributedStyle.CYAN));
    }
}
