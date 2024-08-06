import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class LargeData
{
    String number;
    List<Integer>list=new ArrayList<>();

    public LargeData(String number)
    {
        this.number= number;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this+" finalize called!");
    }

    @Override
    public String toString() {
        return number;
    }

    public static void main(String[] args)
    {

        List<LargeData>largeData=new ArrayList<>();
        for(int i=0;i<100000;i++)
        {
            largeData.add(new LargeData(String.valueOf(i)));
        }

        System.out.println("Memory in use before:"+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()));

        largeData.clear();

        System.gc();

        System.out.println("Memory in use after:"+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()));


    }
}
