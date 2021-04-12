package EmailTask;

import java.util.ArrayList;
import java.util.List;

public class Email {
    private Person sender;
    private String subject;
    private String body;
    private List<Person> toList = new ArrayList<>();
    private List<Person> ccList = new ArrayList<>();
    private List<Person> bccList = new ArrayList<>();

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Person> getToList() {
        return toList;
    }

    public void setToList(List<Person> toList) {
        this.toList = toList;
    }

    public List<Person> getCcList() {
        return ccList;
    }

    public void setCcList(List<Person> ccList) {
        this.ccList = ccList;
    }

    public List<Person> getBccList() {
        return bccList;
    }

    public void setBccList(List<Person> bccList) {
        this.bccList = bccList;
    }

    public void addTo(Person to) {
        this.toList.add(to);
    }

    public void addCc(Person cc) {
        this.ccList.add(cc);
    }
    public void addBcc(Person bcc) {
        this.bccList.add(bcc);
    }

    @Override
    public String toString() {
        if (this.bccList.size() == 0) {
            return "Email {\n" +
                    " from: " + sender.getEmail() +
                    ";\n subject: '" + subject + '\'' +
                    ";\n body: '" + body + '\'' +
                    ";\n toList: " + this.listToString(toList) +
                    "\n ccList: " + this.listToString(ccList) +
                    "\n}";
        }
        return "Email {\n" +
                " from: " + sender.getEmail() +
                ";\n subject: '" + subject + '\'' +
                ";\n body: '" + body + '\'' +
                ";\n toList: " + this.listToString(toList) +
                "\n ccList: " + this.listToString(ccList) +
                "\n bccList: " + this.listToString(bccList) +
                "\n}";
    }

    private String listToString(List<Person> list){
        StringBuilder result = new StringBuilder();
        for(Person p: list){
            result.append(p.getEmail() + "; ");
        }
        return result.toString();
    }
}
