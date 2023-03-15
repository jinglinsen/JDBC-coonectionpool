package javalearn.third;

public class ConnectionPoolBusyException extends RuntimeException{

    //  "是一个"   is-a    has-a   use-a

    public ConnectionPoolBusyException(){}
    public ConnectionPoolBusyException(String message){
        super(message);
    }
}
