package RPS.Dependencies;

import java.util.ArrayList;

public class Link 
{
    private Node head;
    private ArrayList<Node> list;
    
    public Link()
    {
        head = null;
        list = new ArrayList<>();
    }

    public Node createNode(String data)
    {
        Node newNode = new Node(data);
        if (head == null)
        {
            head = newNode;
        }
        list.add(newNode);
        return newNode;
    }

    public void linkNextNode(Node nodeA, Node nodeB)
    {
        nodeA.next = nodeB;
    }

    public void linkPrevNode(Node nodeA, Node nodeB)
    {
        nodeA.prev = nodeB;
    }

    public Node findNode(String name)
    {
        Node result = null;

        for (Node node : list)
        {
            if (name.equals(node.name))
            {
                result = node;
                break;
            }
        }

        return result;
    }

    public String returnNodeList()
    {
        String info = "List is empty!";

        for (int i = 0; i < list.size(); i++)
        {
            Node node = list.get(i);

            if (i == 0)
            {
                info = "<html><body>";
            }

            if (node.prev == null)
            {
                info += "<span style='color:red'>Prev: </span>Null";
            }
            else
            {
                info += "<span style='color:red'>Prev: </span>" + node.prev.name;
            }

            info += " <span style='color:orange'>Node: </span>" + node.name;

            if (node.next == null)
            {
                info += " <span style='color:green'>Next: </span>Null";
            }
            else
            {
                info += " <span style='color:green'>Next: </span>" + node.next.name;
            }

            if (i < list.size() - 1)
            {
                info += "<br>";
            }
            if (i == list.size() - 1)
            {
                info += "</body></html>";
            }
            
        }
        return info;
    }
}
