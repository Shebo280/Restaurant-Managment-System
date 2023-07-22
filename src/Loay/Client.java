package Loay;
public class Client {
    private String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNum;
    private final String password;

    public Client(String id, String firstName, String lastName, String email, String phoneNum, String password) {
        if(firstName.equals(""))    throw new IllegalArgumentException("First Name can't be empty");
        if(lastName.equals(""))     throw new IllegalArgumentException("Last Name can't be empty");
        if(email.equals(""))        throw new IllegalArgumentException("Email can't be empty");
        if(phoneNum.equals(""))     throw new IllegalArgumentException("Phone Number can't be empty");
        if(password.equals(""))     throw new IllegalArgumentException("Password can't be empty");

        this.id= id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
    }

    public Client(String firstName, String lastName, String email, String phoneNum, String password) {
        if(firstName.equals(""))    throw new IllegalArgumentException("First Name can't be empty");
        if(lastName.equals(""))     throw new IllegalArgumentException("Last Name can't be empty");
        if(email.equals(""))        throw new IllegalArgumentException("Email can't be empty");
        if(phoneNum.equals(""))     throw new IllegalArgumentException("Phone Number can't be empty");
        if(password.equals(""))     throw new IllegalArgumentException("Password can't be empty");

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return String.format("ID: %s%nName: %s %s%nEmail address : %s%nPhone Number: %s%nPassword: %s%n", getId(), getFirstName(), getLastName(), getEmail(), getPhoneNum(), getPassword());
    }
}
