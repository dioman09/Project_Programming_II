package Data_Structures;

public class Data_Singleton {

    private static Data_Singleton instance;
    private final Circular_List_Users list_users;
    private final Stacks_Products stacks;
    
    public Data_Singleton() {
        this.list_users = new Circular_List_Users();
        this.stacks = new Stacks_Products();
    }

    public static Data_Singleton getInstance() {
        if (instance == null) {
            instance = new Data_Singleton();
        }
        return instance;
    }

    public Circular_List_Users getList_users() {
        return list_users;
    }

    public Stacks_Products getStacks() {
        return stacks;
    }        
}
