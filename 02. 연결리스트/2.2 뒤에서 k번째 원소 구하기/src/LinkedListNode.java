import java.util.HashSet;

class Node {
    String data;
    Node next = null;
    static int size=0;

    public Node() {
        size++;
        this.data = null;
        this.next = null;
    }

    public Node (String d) {
        size++;
        this.data = d;
        this.next = null;
    }

    static int index = 0;

    Node solve1(Node head, int k) {
        int cnt = 0;
        while(head != null) {
            if(cnt == size-k) {
                return head;
            }
            cnt++;
            head = head.next;
        }

        return head;
    }

    Node solve2(Node head, int k) {
        index = 0;

        if(head == null) {
            return null;
        }
        Node node = solve2(head.next, k);
        index++;
        if(index==k) {
            return head;
        }
        return node;
    }

    Node nthTolast(Node head, int k) {
        Node p1 = head;
        Node p2 = head;

        for(int i=0; i<k-1; i++) {
            if(p2 == null) return null;
            p2 = p2.next;
        }

        while(p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    void insertNode(Node node, String d) {
        Node end = new Node(d); // 추가할 새로운 노드
        Node n = node; // 순회할 노드 복사
        while(n.next != null) { // 노드 마지막까지 순회
            n = n.next;
        }
        n.next = end; // 노드 마지막에 새로운 노드 추가
    }

    Node deleteNode(Node head, String d) {
        Node n = head;
        if(n.data == d) {
            return head.next; // head를 움직인다. (지우지 않아서 메모리는 그대로 참조)
        }

        while(n.next != null) {
            if(n.next.data == d) {
                n.next = n.next.next;
                return head;
            }
            n = n.next;
        }
        return head;
    }

    void printNode(Node node) {
        Node n = node;
        while(true) {
            System.out.print(n.data + " ");
            if(n.next == null) break;
            n = n.next;
        }
    }

}

public class LinkedListNode {
    public static void main(String[] args) {
        Node node = new Node("0");
        node.insertNode(node, "1");
        node.insertNode(node, "2");
        node.insertNode(node, "3");
        node.insertNode(node, "4");

        System.out.print("Node: ");
        node.printNode(node);

        System.out.println();
        for(int i=1; i<=node.size; i++) {
            System.out.println("k: " + i + ", result: " + node.solve1(node, i).data);
        }


    }
}