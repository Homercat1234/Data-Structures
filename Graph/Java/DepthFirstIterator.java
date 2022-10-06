import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;

/**
 * A generic Depth First Iterator.
 * 
 * @author Michael A. Smith
 * @version 1.0
 * @since 5/22/2022
 */
class DepthFirstIterator<V> implements Iterator<V> {
    Stack<V> stack = new Stack<V>();
    ArrayList<V> visited = new ArrayList<V>();
    Graph<V, ?> graph;

    DepthFirstIterator(Graph<V, ?> graph) {
        this.graph = graph;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public V next() {
        V next = stack.pop();
        visited.add(next);
        ArrayList<V> adjacent = graph.getAdjacencyLevel(next);
        for (V vertex : adjacent)
            if (!visited.contains(vertex) && !stack.contains(vertex))
                stack.push(vertex);
        return next;
    }

    public String search(V first) {
        stack.push(first);
        String str = "";
        while (hasNext())
            str += next() + " ";
        return str.substring(0, str.length() - 1);
    }
}
