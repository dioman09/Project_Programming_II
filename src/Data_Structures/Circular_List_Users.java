package Data_Structures;

import Models.Node_User;
import Models.User;
import javax.swing.JOptionPane;

public class Circular_List_Users {
    
    private Node_User head;

    public Circular_List_Users() {
        this.head = null;
    }

    public Node_User getHead() {
        return head;
    }

    public void setHead(Node_User head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return head == null;
    }
    
    public User searchByEmail(String email) {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "No existe un usuario con ese correo.");
            return null;
        }
        
        Node_User current = head;
        do {
            if (current.getUser().getEmail().equals(email)) {
                return current.getUser();
            }
            current = current.getNext();
        } while (current != head);
        return null;
    }
    
    public void addToEnd(User user) {
        Node_User newNode = new Node_User(user);

        if (isEmpty()) {
            head = newNode;
            head.setNext(head);
            head.setFormer(head);
        } else {
            Node_User last = head.getFormer();

            last.setNext(newNode);
            newNode.setFormer(last);
            newNode.setNext(head);
            head.setFormer(newNode);
        }

    }
}
