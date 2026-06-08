package ArrayList;

public class MyArrayListTest1 {
    public static void main(String[] args){
        MyArrayList myListName = new MyArrayList();
        myListName.add("Kim");
        myListName.add("Seo");
        myListName.add("Yoon");
        myListName.add("Jung");
        System.out.println(myListName);
        System.out.println("Empty? : " + myListName.isEmpty());
        System.out.println("Contains Seo? : " + myListName.contains("Seo"));
        try{
            myListName.insert(4, "Lee");
            myListName.insert(2, "Andrew");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println(myListName);

        try{
            myListName.remove(4);
            System.out.println(myListName.get(1));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("데이터 개수 : " + myListName.size());
        System.out.println(myListName);

    }
}
