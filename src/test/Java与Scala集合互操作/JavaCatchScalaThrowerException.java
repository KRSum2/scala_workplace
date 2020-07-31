package test.Java与Scala集合互操作;

public class JavaCatchScalaThrowerException {
    public static void main(String[] args){
        ScalaThrower st = new ScalaThrower();
        try{
            st.exceptionThrower();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
