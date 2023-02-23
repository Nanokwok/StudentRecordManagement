public class Student {
    private String name;  // name of student
    private int idNumber;  // id number of student
    private String contactNumber;  // contact number of student

    // constructor
    public Student(String name, int idNumber, String contactNumber) {
        setIdNumber(idNumber);
        setContactNumber(contactNumber);
        this.name = name;
    }

    // getters and setters
    public String getName() {
        return name;
    }


    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        if (idNumber >= 10000000 && idNumber <= 99999999) {
            this.idNumber = idNumber;
        } else {
            this.idNumber = 0;
        }
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {

        if (contactNumber.startsWith("02") && contactNumber.length() == 9) {
            this.contactNumber = contactNumber;

        } else if (contactNumber.charAt(0) == '0' && contactNumber.length() == 10) {
            this.contactNumber = contactNumber;
        } else {
            this.contactNumber = "0000000000";
        }
    }

    // toString method
    @Override
    public String toString() {
        return "STUDENT: " +
                "Name : '" + name + '\'' +
                " I IDNumber : " + idNumber +
                " I ContactNumber : " + contactNumber +
                "";
    }
}

