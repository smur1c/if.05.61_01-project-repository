import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class JobsScheduler {
    private LinkedList<Job> jobs = new LinkedList<Job>();

    private boolean containsJob(String jobName) {
        for (Job j : this.jobs) {
            if(j.getName().equals(jobName)) {
                return true;
            }
        }

        return false;
    }

    private Job getJob(String jobName) {
        for(Job j : this.jobs) {
            if(j.getName().equals(jobName)) {
                return j;
            }
        }

        return null;
    }

    public void registerJob(String jobName) {
        if(!containsJob(jobName)) {
            this.jobs.add(new Job(jobName));
        }
    }


    public void sort() {

    }

    public String getList() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.jobs.size(); i++) {
            sb.append(this.jobs.get(i));
        }
        return sb.toString();
    }

    public void registerJob(String dependentJob, String independentJob) {
        Job independent = new Job(independentJob);
        Job dependent = new Job(dependentJob);

        if(!containsJob(independentJob) && !containsJob(dependentJob)) {
            this.jobs.add(independent);
            this.jobs.add(dependent);
        }
        else if(!containsJob(dependentJob)) {
            int idx = this.jobs.indexOf(getJob(independent.getName()));
            this.jobs.add(idx + 1,dependent);
        }
        else if(!containsJob(independentJob)) {
            int idx = this.jobs.indexOf(getJob(dependent.getName()));

            if(idx == 0) {
                this.jobs.push(independent);
            }
            else {
                this.jobs.add(idx - 1, independent);
            }
        }
    }
}
