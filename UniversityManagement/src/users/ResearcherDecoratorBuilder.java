package users;

import java.util.Vector;


import researchWorks.*;


public class ResearcherDecoratorBuilder {
    private User decoratedUser;
    private Vector<ResearchPaper> researchPapers;

    public ResearcherDecoratorBuilder(User user) {
        decoratedUser = user;
    }

    public ResearcherDecoratorBuilder withResearchPapers(Vector<ResearchPaper> papers) {
        researchPapers = papers;
        return this;
    }

    public ResearcherDecorator build() {
        ResearcherDecorator decorator = new ResearcherDecorator(decoratedUser);
        decorator.setResearchPapers(researchPapers);
        return decorator;
    }
}


