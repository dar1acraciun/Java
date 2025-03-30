package Commands;

import Repository.Repository;

public abstract class Command {
    protected Repository repository;

    public Command(Repository Repository) {
        repository = Repository;
    }

    public abstract void execute() throws Exception;
}
