class ResourceWithFinalizer implements AutoCloseable
{

    String resource_name;

    ResourceWithFinalizer(String resource_name)
    {
        this.resource_name=resource_name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Resource "+this.resource_name+" finalize called!");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing!");
//        System.gc();
    }
}

public class ResourceMain
{
    public static void main(String[] args) {
        try (ResourceWithFinalizer resourceWithFinalizer=new ResourceWithFinalizer("Resource 1"); ){
            System.out.println("Using resource :"+resourceWithFinalizer.resource_name);
        } catch (Exception e)
        {

        }
        finally
        {
            System.gc();
        }

    }
}
