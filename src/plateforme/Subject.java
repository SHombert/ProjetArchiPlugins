package plateforme;

public interface Subject {
    public void addSubscriber(Observer observer);
    public void removeSubscriber(Observer observer);
	void notifySubscribers(String name, String status);
}