/**
 * A generic Edge class that takes two generics. The first is the type of the
 * vertexes and second is the type of the distance (weight).
 * 
 * @author Michael A. Smith
 * @version 1.0
 * @since 5/22/2022
 */
class Edge<V, D> {
    V source, destination;
    D distance;

    /**
     * A constructor for edge that takes in a source, destination, and distance
     *
     * @param source      The source vertex.
     * @param destination The destination vertex
     * @param object      The destance (weight).
     * @return void.
     * @since 1.0
     */
    public Edge(V source, V destination, D distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    /**
     * A method that returns this edge as a string.
     *
     * @return This edge as a string.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "(" + source + ", " + destination + ", " + distance + ")";
    }

    /**
     * A method that compares this edge to an object.
     * 
     * @param object The object that is being compared to.
     * @return Whether or not this is equal to that object.
     * @since 1.0
     */
    public boolean equals(Object object) {
        if (!(object instanceof Edge<?, ?>))
            return false;
        Edge<V, D> edge = (Edge<V, D>) object;
        if (!edge.source.equals(this.source))
            return false;
        if (!edge.destination.equals(this.destination))
            return false;
        if (!edge.distance.equals(this.distance))
            return false;
        return true;
    }
}