package observer;

public class ConsoleObserver implements Observer {
    @Override
    public void update(String event, Object data) {
        if ("healthChanged".equals(event)) {
            System.out.println("Votre santé a changé : " + data);
        }
    }
}
