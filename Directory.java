import java.util.*;

class TelephoneNumber implements Comparable<TelephoneNumber> {

    private String countryCode;
    private String localNumber;

    public TelephoneNumber(String countryCode, String localNumber) {
        this.countryCode = countryCode;
        this.localNumber = localNumber;

    }

    @Override
    public int compareTo(TelephoneNumber other) {
        int countryCodeComparison = this.countryCode.compareTo(other.countryCode);
        if (countryCodeComparison == 0) {
            return this.localNumber.compareTo(other.localNumber);

        } else {
            return countryCodeComparison;
        }
    }

    @Override
    public String toString() {
        return countryCode + "-" + localNumber;
    }

}

abstract class TelephoneEntry {

    protected TelephoneNumber telephoneNumber;

    public TelephoneEntry(TelephoneNumber telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    abstract String description();

    @Override
    public String toString() {
        return description();
    }
}

class Person extends TelephoneEntry {

    private String name;
    private String lastname;
    private String address;

    public Person(String name, String lastname, String address, TelephoneNumber telephoneNumber) {
        super(telephoneNumber);
        this.name = name;
        this.lastname = lastname;
        this.address = address;

    }

    @Override
    String description() {
        return "Person: " + name + " " + lastname + ", Address: " + address + ", Telephone: " + telephoneNumber;
    }

}

class Company extends TelephoneEntry {

    private String name;
    private String address;

    public Company(String name, String address, TelephoneNumber telephoneNumber) {
        super(telephoneNumber);
        this.name = name;
        this.address = address;

    }

    @Override
    String description() {
        return "Company: " + name + ", Address: " + address + ", Telephone: " + telephoneNumber;
    }

}

public class Directory {

    public static void main(String[] args) {
        TelephoneNumber firstphone = new TelephoneNumber("+48", "12345678");
        Person person1 = new Person("moneeb", "ali karrar", "polytechnika", firstphone);
        TelephoneNumber secondphone = new TelephoneNumber("+48", "23456789");
        Person person2 = new Person("moh", "ali karrar", "UAE", secondphone);
        TelephoneNumber thirdphone = new TelephoneNumber("+48", "34567890");
        Company company1 = new Company("maz", "ABUDABI", thirdphone);
        TelephoneNumber fourthphone = new TelephoneNumber("+48", "09876543");
        Company company2 = new Company("mus", "KSA", secondphone);

        TreeMap<TelephoneNumber, TelephoneEntry> directory = new TreeMap<>();
        directory.put(firstphone, person1);
        directory.put(secondphone, person2);
        directory.put(thirdphone, company1);
        directory.put(fourthphone, company2);
        Iterator<Map.Entry<TelephoneNumber, TelephoneEntry>> iterator = directory.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<TelephoneNumber, TelephoneEntry> entry = iterator.next();
            System.out.println(entry.getValue().description());
        }

    }
}
