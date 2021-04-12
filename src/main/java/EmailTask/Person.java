package EmailTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Person {
    private String email;
    private String name;
    private List<Person> friends = new ArrayList<>(100);
    private List<Email> inbox = new ArrayList<>(1000);

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    public List<Email> getInbox() {
        return inbox;
    }

    public void addEmailInInbox(Email inbox) {
        this.inbox.add(inbox);
    }

    public void showInbox(){
        System.out.println("\n" + this.getName() + "'s inbox:\n");
        inbox.forEach(m -> System.out.println(m.toString()));
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getFriends() {
        return friends;
    }

    public Person(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail(){
        return this.email;
    }

    public void addFriend(Person friend){
        friends.add(friend);
        System.out.println(friend.name + " was added as a friend to " + this.name);
    }

    public void removeFriend(Person friend){
        if (friends.remove(friend)) {
            System.out.println(friend.name + " was successfully removed from " + this.name + "'s friend list");
        } else {
            System.out.println(friend.name + " isn't a friend of " + this.name);
        }
    }

    public void getFriendsInfo() {
        friends.forEach(f -> System.out.println(f.toString()));
    }

    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", friends=" + friends +
                '}';
    }

    public void sendEmail(String subject, List<Person> to, List<Person> cc, List<Person> bcc, String body ) {
        // Filter lists
        List<Person> filteredTO = new ArrayList<>();
        for (Person person: to) {
            if (this.friends.contains(person)) {
                filteredTO.add(person);
            }
        }

        Predicate<Person> byFriend = person -> this.friends.contains(person);
        List<Person> filteredCC = cc.stream().filter(byFriend).collect(Collectors.toList());
        List<Person> filteredBCC = bcc.stream().filter(byFriend).collect(Collectors.toList());

        // email object
        Email email = new Email();

        email.setSubject(subject);
        email.setSender(this);
        email.setToList(filteredTO);
        email.setCcList(filteredCC);
        email.setBccList(filteredBCC);
        email.setBody(body);

        // email sender object
        EmailSender sender = new EmailSender();
        sender.send(email);
    }
}

class Test {
    public static void main(String[] args) {
        Person Alla = new Person("a@gmail.com", "Alla");
        Person AllaA = new Person("aa@gmail.com", "AllaA");
        Person AllaB = new Person("ab@gmail.com", "AllaB");
        Person AllaC = new Person("ac@gmail.com", "AllaC");
        Person AllaD = new Person("ad@gmail.com", "AllaD");
        Person AllaE = new Person("ae@gmail.com", "AllaE");
        Person AllaF = new Person("af@gmail.com", "AllaF");

        Alla.addFriend(AllaD);
        Alla.addFriend(AllaF);
        Alla.addFriend(AllaB);

//        Alla.getFriendsInfo();
//        Alla.removeFriend(AllaD);
//        Alla.getFriendsInfo();

        Alla.sendEmail("Subject Test",
                Arrays.asList(AllaD, AllaC, AllaB),
                Arrays.asList(AllaD, AllaC),
                Arrays.asList(AllaE, AllaD),
                "Body Test");

        AllaD.showInbox();
        AllaB.showInbox();
    }
}
