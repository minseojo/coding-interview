import java.util.HashSet;

class Node {
    String data;
    Node next = null;

    public Node() {
        this.data = null;
        this.next = null;
    }

    public Node (String d) {
        this.data = d;
        this.next = null;
    }

    int[] visited = new int[10];
    Node solve(Node head) {
        Node n = head;
        int num = Integer.parseInt(n.data); //맨 처음은 항상 중복이 아님
        visited[num]++;

        while(n.next != null) {
            num = Integer.parseInt(n.next.data); // 헤드 다음 데이터부터 체크
            if(visited[num]>0) {
                n.next = n.next.next;
                return solve(n);
            }
            visited[num]++;
            n = n.next;
        }

        return head; // 중복이 하나도 없는 경우
    }

    void deleteDups(Node head) {
        HashSet set = new HashSet();
        Node previous = null;

        while(head != null) {
            if(set.contains(head.data)) {
                previous.next = head.next;
            } else {
                set.add(head.data);
                previous = head;
            }
            head = head.next;
        }
    }

    void deleteDups2(Node head) {
        Node current = head;
        // 헤드는 고정시키고 큐런트 하나씩 이동
        // 큐런트가 마지막 null이면 큐런트 처음으로 돌리고 헤드 하나 앞으로
        while(current != null) {
            Node runner = current;
            while(runner.next != null) {
                if(runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else
                    runner = runner.next;
            }
            current = current.next;
        }
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
        node.insertNode(node, "1");
        node.insertNode(node, "1");
        node.insertNode(node, "2");
        System.out.print("Before: ");
        node.printNode(node);


        System.out.println();
        System.out.print(" After: ");
        node.deleteDups2(node);
        node.printNode(node);
    }
}