package Models;

public class NodeUser {
    
    private NodeUser next;
    private User information;
    private NodeUser former;  

    public NodeUser(User information) {        
        this.information = information;
        this.next = null;
        this.former = null;
    }

    public NodeUser getNext() {
        return next;
    }

    public void setNext(NodeUser next) {
        this.next = next;
    }

    public User getInformation() {
        return information;
    }

    public void setInformation(User information) {
        this.information = information;
    }

    public NodeUser getFormer() {
        return former;
    }

    public void setFormer(NodeUser former) {
        this.former = former;
    }        
}
