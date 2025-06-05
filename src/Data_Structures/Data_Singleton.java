package Data_Structures;

public class Data_Singleton {

    private static Data_Singleton instance;
    private final Circular_List_Users list_users;

    public Data_Singleton() {
        this.list_users = new Circular_List_Users();
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
    
}
