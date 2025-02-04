package telran.chat.server.mediation;

/**
 * This interface represents Blocking Queue for usage
 * as communication buffer between Producers and Consumers
 */
public interface BlkQueue<T> {
    void push(T message) throws InterruptedException;
    T pop();
}
