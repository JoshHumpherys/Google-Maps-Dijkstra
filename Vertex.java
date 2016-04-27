import java.util.Iterator;

/**
 * Created by cubemaster on 4/25/16.
 */
public class Vertex implements Iterable<Edge> {
    private String name;
    private double lat;
    private double lon;
    private int index;
    private List<Edge> edges;
    public Vertex(String name, double lat, double lon, int index) {
        this.name = name;
        this.lat = lat;
        this.lon  = lon;
        this.index = index;
        edges = new List<>();
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    // Supplied by Dr. Hunt
    public double distanceToVertex(Vertex vertex){
        final double R = 6371; // km
        double lat2 = vertex.getLatitude();
        double lon2 = vertex.getLongitude();
        double radLat1 = Math.toRadians(lat);
        double radLat2 = Math.toRadians(lat2);
        double radDiffLat = Math.toRadians(lat2-lat);
        double radDiffLon = Math.toRadians(lon2-lon);

        double a = Math.sin(radDiffLat/2) * Math.sin(radDiffLat/2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.sin(radDiffLon/2) * Math.sin(radDiffLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a),  Math.sqrt(1-a));

        return R * c;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lon;
    }

    public int getIndex() {
        return index;
    }

    public Iterator<Edge> iterator() {
        return edges.iterator();
    }

    public String toStringWithEdges() {
        StringBuilder sb = new StringBuilder(toString());
        for(Edge e : edges) {
            sb.append("\n  ");
            sb.append(e);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return name + " " + lat + ", " + lon;
    }
}
