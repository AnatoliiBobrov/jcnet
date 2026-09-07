
import java.util.ArrayList;
import java.util.Arrays;
import jcnet.Variable;
public class Main {
    
    public static void main(String[] args) {
        Variable[] ara11 = new Variable[]{new Variable(3F), new Variable(5F)};
        var araL = new ArrayList<Variable>(Arrays.asList(ara11));
        ara11[0].value = 66.0;
        IO.println(araL);
        
    }
}