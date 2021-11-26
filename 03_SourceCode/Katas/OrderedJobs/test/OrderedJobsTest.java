import org.junit.Assert;
import org.junit.Test;

public class OrderedJobsTest {

    @Test
    public void itShouldReturnA_GivenA(){
        //arrange
        var sut = new JobsScheduler();
        sut.registerJob("A");
        //act
        sut.sort();
        //assert
        Assert.assertEquals("A", sut.getList());
    }

    @Test
    public void itShouldReturnAB_GivenAB(){
        //arrange
        var sut = new JobsScheduler();
        sut.registerJob("A");
        sut.registerJob("B");
        //act
        sut.sort();
        //assert
        Assert.assertEquals("AB", sut.getList());
    }

    @Test
    public void itShouldReturnAB_GivenBDependsOnA(){
        //arrange
        var sut = new JobsScheduler();
        sut.registerJob("B", "A");
        //act
        sut.sort();
        //assert
        Assert.assertEquals("AB", sut.getList());
    }

    @Test
    public void itShouldReturnABC_GivenCDependsOnBAndBDependsOnA(){
        //arrange
        var sut = new JobsScheduler();
        sut.registerJob("B", "A");
        sut.registerJob("C", "B");
        //act
        sut.sort();
        //assert
        Assert.assertEquals("ABC", sut.getList());
    }

    @Test
    public void itShouldReturnAB_GivenABB(){
        //arrange
        var sut = new JobsScheduler();
        sut.registerJob("A");
        sut.registerJob("B");
        sut.registerJob("B");
        //act
        sut.sort();
        //assert
        Assert.assertEquals("AB", sut.getList());
    }

    @Test
    public void itShouldReturnABC_GivenCBA(){
        //arrange
        var sut = new JobsScheduler();
        sut.registerJob("C", "B");
        sut.registerJob("B", "A");
        //act
        sut.sort();
        //assert
        Assert.assertEquals("ABC", sut.getList());
    }

    @Test
    public void itShouldReturnBA_GivenBA(){
        //arrange
        var sut = new JobsScheduler();
        sut.registerJob("B");
        sut.registerJob("A");
        //act
        sut.sort();
        //assert
        Assert.assertEquals("BA", sut.getList());
    }
}
