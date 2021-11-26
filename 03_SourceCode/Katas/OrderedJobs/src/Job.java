public class Job{
    private String name;

    private Job dependsOn;

    public Job(String name, Job dependsOn){
        this.name = name;
        this.dependsOn = dependsOn;
    }
    public Job(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Job getDependency() {
        return dependsOn;
    }

    public boolean isDependent(){

        return false;
    }
}
