import java.util.HashMap;
import java.util.ArrayList;

/**
 * A generic Graph class that takes two generics. The first is the vertexes's
 * type and the second is the weight or distance between two vertexes' type.
 * 
 * @author Michael A. Smith
 * @version 1.0
 * @since 5/22/2022
 */
class Graph<V, D> {
    private HashMap<V, ArrayList<Edge<V, D>>> adjList = new HashMap<V, ArrayList<Edge<V, D>>>();

    /**
     * A blank constructor for Graph.
     * 
     * @return void
     * @since 1.0
     */
    public Graph() {
        ;
    }

    /**
     * A constructor for Graph that takes an edge and adds it to the graph.
     * 
     * @param edge An edge of type V,D that is to be added to the newly created
     *             graph.
     * @return void
     * @since 1.0
     */
    public Graph(Edge<V, D> edge) {
        addEdge(edge);
    }

    /**
     * A constructor for Graph that takes a source, destination, and distance and
     * adds an edge with those values to the graph.
     * 
     * @param source      The source of the edge.
     * @param destination The destination of the edge.
     * @param distance    The distance between the two vertexes.
     * @return void
     * @since 1.0
     */
    public Graph(V source, V destination, D distance) {
        addEdge(source, destination, distance);
    }

    /**
     * A method that takes a vertex and checks to see if that vertex exists inside
     * the graph.
     * 
     * @param vertex The vertex that is to be checked.
     * @return True if the vertex is in the graph; false if not.
     * @since 1.0
     */
    public boolean isVertex(V vertex) {
        return adjList.containsKey(vertex);
    }

    /**
     * A method that takes two vertex and checks to see if those vertexes form an
     * edge within the graph.
     * 
     * @param source      The first vertex to be checked.
     * @param destination The second vertex to be checked.
     * @return True if they form an edge in the graph; false if not.
     * @since 1.0
     */
    public boolean isEdge(V source, V destination) {
        return isVertex(source) && isVertex(destination);
    }

    /**
     * A method that takes two vertex and a weight/distance and checks to see if
     * those vertexes and the weight/distance form an edge within the graph.
     * 
     * @param source      The first vertex to be checked.
     * @param destination The second vertex to be checked.
     * @param distance    The weight or distance to be checked.
     * @return True if all three values form an edge in the graph; false if not.
     * @since 1.0
     */
    public boolean isEdge(V source, V destination, D distance) {
        if (getEdge(source, destination) == null)
            return false;
        if (!getEdgeWeight(source, destination).equals(distance))
            return false;
        return true;
    }

    /**
     * A method that takes a given edge checks to see if those vertexes and checks
     * if that edge is in the graph.
     * 
     * @param edge The edge that is to be checked.
     * @return true If the edge is in the graph; false if not.
     * @since 1.0
     */
    public boolean isEdge(Edge<V, D> edge) {
        return isEdge(edge.source, edge.destination, edge.distance);
    }

    /**
     * A method that returns the adjList (HashMap).
     * 
     * @return The adjList of this graph.
     * @since 1.0
     */
    public HashMap<V, ArrayList<Edge<V, D>>> getAdjList() {
        return adjList;
    }

    /**
     * A method that gets all adjacent vertexes at a given vertex.
     * 
     * @param vertex The vertex to be searched at.
     * @return An ArrayList of type V containing all adjacent vertexes at the given
     *         vertex.
     * @since 1.0
     */
    public ArrayList<V> getAdjacencyLevel(V vertex) {
        ArrayList<V> list = new ArrayList<V>();
        for (Edge<V, ?> edge : adjList.get(vertex))
            list.add(edge.destination);
        return list;
    }

    /**
     * A method that gets all adjacent vertexes at a given index.
     * 
     * @param index The index to be searched at.
     * @return An ArrayList of type V containing all adjacent vertexes at the given
     *         index.
     * @since 1.0
     */
    public ArrayList<V> getAdjacencyLevel(int index) {
        ArrayList<V> list = new ArrayList<V>();
        for (Edge<V, ?> edge : adjList.get(index))
            list.add(edge.destination);
        return list;
    }

    /**
     * A method that gets all adjacent edges at a given vertex.
     * 
     * @param vertex The vertex to be searched at.
     * @return An ArrayList of type Edge<V,D> containing all adjacent edges at the
     *         given vertex.
     * @since 1.0
     */
    public ArrayList<Edge<V, D>> getAdjacencyEdges(V vertex) {
        if (isEmpty())
            return null;
        ArrayList<Edge<V, D>> list = new ArrayList<Edge<V, D>>();
        for (Edge<V, D> edge : adjList.get(vertex))
            list.add(edge);
        return list;
    }

    /**
     * A method that gets all adjacent edges at a given index.
     *
     * @param index The index to be searched at.
     * @return An ArrayList of type Edge<V,D> containing all adjacent edges at the
     *         given index.
     * @since 1.0
     */
    public ArrayList<Edge<V, D>> getAdjacencyEdges(int index) {
        if (isEmpty())
            return null;
        ArrayList<Edge<V, D>> list = new ArrayList<Edge<V, D>>();
        for (Edge<V, D> edge : adjList.get(index))
            list.add(edge);
        return list;
    }

    /**
     * A method that gets all edges in the graph.
     *
     * @return An ArrayList of type Edge<V,D> containing all edges.
     * @since 1.0
     */
    public ArrayList<Edge<V, D>> getEdges() {
        if (isEmpty())
            return null;
        ArrayList<Edge<V, D>> edges = new ArrayList<Edge<V, D>>();
        for (V vertex : getVertexes())
            if (getAdjacencyEdges(vertex) != null)
                for (Edge<V, D> edge : getAdjacencyEdges(vertex))
                    edges.add(edge);
        return edges;
    }

    /**
     * A method that gets vertexes in the graph.
     *
     * @return An ArrayList of type ArrayList<V> containing all vertexes.
     * @since 1.0
     */
    public ArrayList<V> getVertexes() {
        if (isEmpty())
            return null;
        ArrayList<V> vertexes = new ArrayList<V>();
        for (V key : adjList.keySet())
            vertexes.add(key);
        return vertexes;
    }

    /**
     * A method that gets an edge given a source and destination.
     *
     * @param source      The source vertex.
     * @param destination The destination vertex.
     * @return The edge at the given source and destination or null if it does not
     *         exist.
     * @since 1.0
     */
    public Edge<V, D> getEdge(V source, V destination) {
        if (!isEdge(source, destination))
            return null;
        for (Edge<V, D> edge : adjList.get(source))
            if (edge.destination.equals(destination))
                return edge;
        return null;
    }

    /**
     * A method that gets an edge's weight given a source and destination.
     *
     * @param source      The source vertex.
     * @param destination The destination vertex.
     * @return The edge's weight at the given source and destination or null if it
     *         does not exist.
     * @since 1.0
     */
    public D getEdgeWeight(V source, V destination) {
        if (getEdge(source, destination) == null)
            return null;
        return getEdge(source, destination).distance;
    }

    /**
     * A method that adds an empty vertex to the graph.
     *
     * @param vertex The vertex to be added.
     * @return True if the vertex was added, false if not.
     * @since 1.0
     */
    public boolean addVertex(V vertex) {
        if (isVertex(vertex)) {
            return false;
        } else {
            adjList.put(vertex, new ArrayList<Edge<V, D>>());
            return true;
        }
    }

    /**
     * A method that adds and edge to the graph.
     *
     * @param edge The edge to be added.
     * @return void.
     * @since 1.0
     */
    public void addEdge(Edge<V, D> edge) {
        addVertex(edge.source);
        addVertex(edge.destination);
        if (isEdge(edge.source, edge.destination))
            return;

        ArrayList<Edge<V, D>> temp = adjList.get(edge.source);
        temp.add(edge);
        adjList.replace(edge.source, temp);

        temp = adjList.get(edge.destination);
        temp.add(new Edge<V, D>(edge.destination, edge.source, edge.distance));
        adjList.replace(edge.destination, temp);
    }

    /**
     * A method that adds and edge to the graph given a source, destination, and
     * distance.
     *
     * @param source      The source vertex.
     * @param destination The destination vertex.
     * @param distance    The distance (weight) of the edge
     * @return void.
     * @since 1.0
     */
    public void addEdge(V source, V destination, D distance) {
        addEdge(new Edge<V, D>(source, destination, distance));
    }

    /**
     * A method that adds and removes an edge from the graph given a source,
     * destination.
     *
     * @param source      The source vertex.
     * @param destination The destination vertex.
     * @return void.
     * @since 1.0
     */
    public void removeEdge(V source, V destination) {
        if (!isEdge(source, destination))
            return;
        ArrayList<Edge<V, D>> temp = adjList.get(source);
        for (int i = 0; i < temp.size(); i++)
            if (temp.get(i).destination.equals(destination)) {
                temp.remove(i);
                break;
            }
        adjList.replace(source, temp);

        temp = adjList.get(destination);
        for (int i = 0; i < temp.size(); i++)
            if (temp.get(i).destination.equals(source)) {
                temp.remove(i);
                break;
            }
        adjList.replace(destination, temp);
    }

    /**
     * A method that adds and removes an edge from the graph given an edge.
     *
     * @param edge The edge to be removed.
     * @return void.
     * @since 1.0
     */
    public void removeEdge(Edge<V, D> edge) {
        removeEdge(edge.source, edge.destination);
    }

    /**
     * A method that removes a vertex from the graph
     *
     * @param vertex The vertex to be removed.
     * @return void.
     * @since 1.0
     */
    public void removeVertex(V vertex) {
        if (!isVertex(vertex))
            return;
        ArrayList<Edge<V, D>> temp = adjList.get(vertex);
        int size = temp.size();
        for (int i = 0; i < size; i++) {
            removeEdge(temp.get(0));
        }
        adjList.remove(vertex);
    }

    /**
     * A method that converts this graph to a string
     *
     * @return void.
     * @since 1.0
     */
    @Override
    public String toString() {
        if (isEmpty())
            return "Empty Graph.";
        String str = new String();
        for (V vertex : getVertexes()) {
            str += (vertex + ": ");
            for (Edge<V, D> edge : getAdjacencyEdges(vertex))
                str += "(" + edge.destination + ", " + edge.distance + ") ";
            str = str.substring(0, str.length() - 1) + "\n";
        }
        return (str.substring(0, str.length() - 1));
    }

    /**
     * A method that checks to see if the graph is empty
     *
     * @return whether or not the graph is empty.
     * @since 1.0
     */
    public boolean isEmpty() {
        if (adjList.keySet() == null)
            return true;
        ArrayList<Edge<V, D>> edges = new ArrayList<Edge<V, D>>();
        for (V vertex : adjList.keySet())
            for (Edge<V, D> edge : adjList.get(vertex))
                edges.add(edge);
        if (adjList.keySet().size() == 0 && edges.size() == 0)
            return true;
        return false;
    }

    /**
     * A method that compares this graph to another object to see if they are equal
     *
     * @param object The object to be compared to.
     * @return Whether or not they are equal.
     * @since 1.0
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Graph<?, ?>))
            return false;
        Graph<V, D> graph = (Graph<V, D>) object;
        if (graph.size() != size())
            return false;
        for (V key : getVertexes()) {
            if (!(isVertex(key) && graph.isVertex(key)
                    && getAdjacencyLevel(key).size() == graph.getAdjacencyLevel(key).size()))
                return false;
            for (Edge<V, D> edge : adjList.get(key))
                if (!graph.adjList.get(key).contains(edge))
                    return false;
        }
        return true;
    }

    /**
     * A method that gets the size of this graph
     *
     * @return The size of the graph.
     * @since 1.0
     */
    public int size() {
        if (getEdges() == null)
            return 0;
        return getEdges().size();
    }
}