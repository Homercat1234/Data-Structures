import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * A generic Breadth First Iterator.
 * 
 * @author Michael A. Smith
 * @version 1.0
 * @since 5/22/2022
 */
class BreadthFirstIterator<V> implements Iterator<V> {
    LinkedList<V> queue = new LinkedList<V>();
    ArrayList<V> visited = new ArrayList<V>();
    Graph<V, ?> graph;

    BreadthFirstIterator(Graph<V, ?> graph) {
        this.graph = graph;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public V next() {
        V next = queue.poll();
        ArrayList<V> adjacent = graph.getAdjacencyLevel(next);
        for (V vertex : adjacent)
            if (!visited.contains(vertex)) {
                queue.add(vertex);
                visited.add(vertex);
            }
        return next;
    }

    public String search(V first) {
        String str = "";
        queue.add(first);
        visited.add(first);
        while (hasNext())
            str += next() + " ";
        return str.substring(0, str.length() - 1);
    }
}
