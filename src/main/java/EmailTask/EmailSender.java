package EmailTask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmailSender {
    public void send(Email email){
        // Inbox with BCC
        email.getBccList().forEach(r -> r.addEmailInInbox(email));

        // Inbox without BCC
        List<Person> allReceivers = new ArrayList<>();
        allReceivers.addAll(email.getToList());
        allReceivers.addAll(email.getCcList());

        Set<Person> uniqueReceivers = new HashSet<>(allReceivers);

        // create an email without bcc
        Email emailWithoutBcc = new Email();
        emailWithoutBcc.setSubject(email.getSubject());
        emailWithoutBcc.setSender(email.getSender());
        emailWithoutBcc.setToList(email.getToList());
        emailWithoutBcc.setCcList(email.getCcList());
        emailWithoutBcc.setBody(email.getBody());

        for (Person r : uniqueReceivers) {
            r.addEmailInInbox(emailWithoutBcc);
        }


        System.out.println("\n\nNew EMAIL was sent\n");
        System.out.println("Subject: " + email.getSubject());
        System.out.println("From: " + email.getSender().getEmail());
        System.out.println("To: " + this.listToString(email.getToList()));
        System.out.println("Cc: " + this.listToString(email.getCcList()));
        System.out.println("Bcc: " + this.listToString(email.getBccList()));
        System.out.println("Body: " + email.getBody() + "\n");
    }

    private String listToString(List<Person> list){
        StringBuilder result = new StringBuilder();
        for(Person p: list){
            result.append(p.getEmail() + "; ");
        }
        return result.toString();
    }
}
