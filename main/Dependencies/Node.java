package RPS.Dependencies;

public class Node
{
    public String name;
    public Node next, prev;

    public Node(String data)
    {
        name = data;
        next = prev = null;
    }
}