package Commands;

import Model.Image;
import Repository.Repository;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.clique.MaximalCliqueFinder;
import org.graph4j.util.Clique;

import java.util.Arrays;
import java.util.List;

public class FindMaxCliq extends Command {

    private Graph graph;

    public FindMaxCliq(Repository repository) {
        super(repository);
        graph = GraphBuilder.empty()
                .estimatedNumVertices(repository.getImages().size())
                .buildGraph();
    }

    public void createGraph() {

        int label = 0;
        for (Image image : repository.getImages()) {
            graph.addLabeledVertex(label++, image);

        }
        for (Image image : repository.getImages()) {

            for (String tag : image.tags()) {
                List<Image> neighbors = getNeighbors(image, tag);
                neighbors.stream().filter(image2 -> !image.equals(image2)).forEach(image2 -> {
                    graph.addEdge(image2, image);
                });
            }
        }
        System.out.println(graph.toString());


    }

    private List<Image> getNeighbors(Image image, String tag) {

        List<Image> neighbors = repository.getImages().stream().filter(image1 -> {
            return Arrays.asList(image1.tags()).contains(tag);
        }).toList();
        return neighbors;

    }

    @Override
    public void execute() {
        createGraph();
        MaximalCliqueFinder finder = new MaximalCliqueFinder(graph);
        Clique clique = finder.getMaximalClique();
        System.out.println(clique.toString());
    }
}
