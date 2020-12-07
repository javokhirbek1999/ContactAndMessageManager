import java.util.ArrayList;
import java.util.Scanner;

class Message{
    private String text;
    private String recipient;
    private int id;

    public Message(String text, String recipient, int id) {
        this.text = text;
        this.recipient = recipient;
        this.id = id;
    }

    public void getDetails(){
        System.out.println("Contact Name: " + recipient + " \nMessage: " + text+ "" + "\nID:" + id);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
class Contact{
    private String name;
    private String number;
    private String email;
    private ArrayList<Message> messages;

    public Contact(String name, String number, String email, ArrayList<Message> messages) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.messages = messages;
    }

    public Contact(String name, String number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.messages = new ArrayList<>();
    }

    public void getDetails(){
        System.out.println("Name: " + this.name + "" + "\nNumber: " + this.number + "" + "\nEmail: " + this.email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
public class ContactAndMessageManager {
    private static ArrayList<Contact> contacts;
    private static Scanner scanner;
    private static int id = 0;

    public static void main(String[] args) {
        contacts = new ArrayList<>();
        System.out.println("Welcome !!!");
        showOptions();
    }

    private static void showOptions(){
        System.out.println("Select the available options: " +
                "\n\t1. Manage Contacts" +
                "\n\t2. Messages" +
                "\n\t3. Quit");

        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessage();
                break;
            default:
                break;
        }
    }


    private static void manageContacts(){
        System.out.println("Please select one:" +
                "\n\t1. Show all contacts" +
                "\n\t2. Add a new contact" +
                "\n\t3. Search for a contact" +
                "\n\t4. Delete a contact" +
                "\n\t5. Go back");

        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showOptions();
                break;
        }
    }

    private static void showAllContacts() {
        if (contacts.size()>0){
            for (Contact c:contacts){
                c.getDetails();
                System.out.println("---------------------------");
            }
            showOptions();
        }else {
            System.out.println("No such contact found");
        }
    }

    private static void addNewContact() {
        System.out.println("Adding a new contact" +
                "\nPlease enter the contact name: ");
        String name = scanner.next();
        System.out.println("Enter the contact number: ");
        String number = scanner.next();
        System.out.println("Enter the contact email: ");
        String email = scanner.next();

        if (name.equals("") || number.equals("") || email.equals("")){
            System.out.println("Please enter all of the required information");
            addNewContact();
        } else {
            boolean doesExist = false;
            for (Contact c:contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                }
            }
            if (doesExist){
                System.out.println(name + " already exists in this device");
                addNewContact();
            }else {
                Contact contact = new Contact(name, number, email);
                contacts.add(contact);
                System.out.println(name + " added successfully");
            }
        }

        showOptions();
    }

    private static void searchForContact() {
        System.out.println("Enter the name of the contact: ");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("Please enter the name of the contact:");
            searchForContact();
        } else {
            boolean doesExist = false;
            for (Contact c:contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                    c.getDetails();
                }
            }

            if (!doesExist){
                System.out.println("There is no such contact");
            }
        }
        showOptions();
    }

    private static void deleteContact() {
        System.out.println("Please enter the name of the contact: ");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("Please enter the name: ");
            deleteContact();
        } else {

            boolean doesExist = false;

            for (Contact c:contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                    contacts.remove(c);
                }
            }

            if (!doesExist){
                System.out.println("There is no such contact");
            }
        }

        showOptions();
    }

    private static void manageMessage(){
        System.out.println("Please select one:" +
                "\n\t1. Show all messages" +
                "\n\t2. Send a new message" +
                "\n\t3. Go back");

        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                showAllMessage();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showOptions();
                break;
        }
    }

    private static void showAllMessage() {
        ArrayList<Message> allMessages = new ArrayList<>();
        for (Contact c:contacts){
            allMessages.addAll(c.getMessages());
        }

        if (allMessages.size()>0){
            for (Message m:allMessages){
                m.getDetails();
                System.out.println("----------------------------------------");
            }
        }else {
            System.out.println("You do not have any messages");
        }

        showOptions();
    }

    private static void sendNewMessage() {
        System.out.println("Who do you wan to send a message to ?");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("Enter the name: ");
            sendNewMessage();
        } else {
            boolean doesExist = false;
            for (Contact c:contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                }
            }

            if (doesExist){
                System.out.println("Type the Message: ");
                String text = scanner.nextLine();
                if (text.equals("")){
                    System.out.println("Please enter message: ");
                    sendNewMessage();
                } else {
                    id++;
                    Message newMessage = new Message(text, name, id);
                    for (Contact c:contacts){
                        if (c.getName().equals(name)){
                            ArrayList<Message> newMessages = c.getMessages();
                            newMessages.add(newMessage);
                            c.setMessages(newMessages);
                        }
                    }
                }
            } else {
                System.out.println("There is no such contact");
            }
        }
        showOptions();
    }


}
