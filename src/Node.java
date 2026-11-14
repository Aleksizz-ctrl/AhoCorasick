import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
    public Map<Character, Node> children = new HashMap<>();
    public Node failureLink = null;
    public List<String> outputs = new ArrayList<>();
}
