package users;

import java.util.Vector;

import researchWorks.*;

import java.util.Vector;

class ResearcherDecoratorBuilder {
    private User decoratedUser;
    private Vector<ResearchProject> researchProjects;
    private Vector<ResearchPaper> researchPapers;

    public ResearcherDecoratorBuilder(User user) {
        decoratedUser = user;
    }

    public  ResearcherDecoratorBuilder withResearchProjects(Vector<ResearchProject> projects) {
        researchProjects = projects;
        return this;
    }

    public ResearcherDecoratorBuilder withResearchPapers(Vector<ResearchPaper> papers) {
        researchPapers = papers;
        return this;
    }

    public ResearcherDecorator build() {
        ResearcherDecorator decorator = new ResearcherDecorator(decoratedUser);
        decorator.setResearchProjects(researchProjects);
        decorator.setResearchPapers(researchPapers);
        return decorator;
    }
}


