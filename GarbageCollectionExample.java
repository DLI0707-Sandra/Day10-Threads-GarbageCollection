public class GarbageCollectionExample
{

    String  objectNumber;
    public GarbageCollectionExample(String  objectNumber)
    {
        this.objectNumber=objectNumber;
        System.out.println("Inside constructor for object "+this+"!");
    }

    @Override
    public String toString()
    {
        return objectNumber;
    }

    @Override
    protected void finalize() throws Throwable
    {
        System.out.println("Finalize called for object "+this);
    }

    public static void main(String[] args)
    {
        GarbageCollectionExample object1=new GarbageCollectionExample("1");
        GarbageCollectionExample object2=new GarbageCollectionExample("2");
        object2=null;
        GarbageCollectionExample object3=new GarbageCollectionExample("3");
        object3=object1;
        object1=null;
        System.gc();

    }
}
