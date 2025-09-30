package com.ei.patterns.behavioral.observer.youtube;
import java.util.ArrayList;
import java.util.List;

// Subscriber (Observer Interface) 
interface Subscriber {
    void update(String video); // Method to get updated when a new video is uploaded
}

// Concrete Subscribers 
class YouTubeSubscriber implements Subscriber {
    private String name;

    public YouTubeSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String video) {
        System.out.println(name + " is watching the video: " + video);
    }
}

class EmailSubscriber implements Subscriber {
    private String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void update(String video) {
        System.out.println("Sending email to " + email + ": New video uploaded: " + video);
    }
}

// YouTubeChannel (Subject Interface) 
interface YouTubeChannel {
    void addSubscriber(Subscriber subscriber);
    void removeSubscriber(Subscriber subscriber);
    void notifySubscribers();
}

// Concrete YouTubeChannel Implementation 
class YouTubeChannelImpl implements YouTubeChannel {
    private List<Subscriber> subscribers = new ArrayList<>();
    private String video;

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(video);
        }
    }

    public void uploadNewVideo(String video) {
        this.video = video;
        System.out.println("\nIn Channel New video uploaded: " + video);
        notifySubscribers();
    }
}

// Main Class 
public class Youtube {
    public static void main(String[] args) {
        // Create a YouTube channel
        YouTubeChannelImpl channel = new YouTubeChannelImpl();

        // Create subscribers
        YouTubeSubscriber alice = new YouTubeSubscriber("Alice");
        YouTubeSubscriber bob = new YouTubeSubscriber("Bob");
        EmailSubscriber emailSub = new EmailSubscriber("bob@example.com");
      

        // Add subscribers to channel
        channel.addSubscriber(alice);
        channel.addSubscriber(bob);
        channel.addSubscriber(emailSub);
    

        // Upload a new video
        channel.uploadNewVideo("Java Design Patterns Tutorial");

        // Remove one subscriber and upload another video
        channel.removeSubscriber(bob);
        channel.uploadNewVideo("Observer Pattern in Action");
    }
}
