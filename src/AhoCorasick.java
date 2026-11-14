import java.util.*;

public class AhoCorasick {
    private final Node root = new Node();

    public void insert(String pattern) {
        Node current = root;
        for (char ch : pattern.toCharArray()) {
            current = current.children.computeIfAbsent(ch, c -> new Node());
        }
        current.outputs.add(pattern);
    }

    public void buildFailureLinks() {
        Queue<Node> queue = new LinkedList<>();

        for (Node child : root.children.values()) {
            child.failureLink = root;
            queue.add(child);
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Map.Entry<Character, Node> entry : current.children.entrySet()) {
                char ch = entry.getKey();
                Node child = entry.getValue();

                Node fallback = current.failureLink;
                while (fallback != null && !fallback.children.containsKey(ch)) {
                    fallback = fallback.failureLink;
                }

                if (fallback != null) {
                    child.failureLink = fallback.children.get(ch);
                } else {
                    child.failureLink = root;
                }

                child.outputs.addAll(child.failureLink.outputs);
                queue.add(child);
            }
        }
    }

    public Map<String, List<Integer>> search(String text) {
        Map<String, List<Integer>> results = new HashMap<>();
        Node current = root;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            while (current != null && !current.children.containsKey(ch)) {
                current = current.failureLink;
            }

            if (current == null) {
                current = root;
            } else {
                current = current.children.get(ch);
                for (String pattern : current.outputs) {
                    results.computeIfAbsent(pattern, k -> new ArrayList<>()).add(i - pattern.length() + 1);
                }
            }
        }
        return results;
    }
}