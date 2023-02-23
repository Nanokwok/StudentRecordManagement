import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.LinkedList;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;

public class StudentRecordManagement {
    private LinkedList<Student> studentList = new LinkedList<Student>();
    public StudentRecordManagement() throws ParserConfigurationException, TransformerException {
        File file = new File("Studentxml.xml");
        if (!file.exists()) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("studentList");
            doc.appendChild(rootElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Studentxml.xml"));

            transformer.transform(source, result);
        }
    }

    // add student record
    public void add(int id, String name, String contact) {
        Student record = new Student(name, id, contact);
        studentList.add(record);

        Student[] student = {record};
        StudentXMLRecorder studentXMLRecorder = new StudentXMLRecorder();
        studentXMLRecorder.saveStudentData(student);

        System.out.println(name + ":" + id + " has been added.");
    }

    // find student by id (boolean)
    public boolean find(int idNumber) {
        Student[] studentFromXML = StudentXMLRecorder.loadStudentData();
        for (Student student : studentFromXML) {
            if (student.getIdNumber() == idNumber) {
//                System.out.println("Student found: " + student.getName());
                return true;
            }
        }
        System.out.println("Student not found.");
        return false;
    }

    // delete student
    public void delete(int idNumber) {
        Student[] studentFromXML = StudentXMLRecorder.loadStudentData();
        for (Student student : studentFromXML) {
            if (student.getIdNumber() == idNumber) {
                studentList.remove(student);
                StudentXMLRecorder studentXMLRecorder = new StudentXMLRecorder();
                studentXMLRecorder.deleteStudentData(student);
                System.out.println("Student deleted successfully");
                return;
            }
        }
        System.out.println("Student not found");
    }

    // Create array of student objects
    public Student[] getStudentList() {
        Student[] studentArr = StudentXMLRecorder.loadStudentData();
        return studentArr;
    }

    // get student name from student id
    public String getonestudent(int idNumber) {
        Student[] studentFromXML = StudentXMLRecorder.loadStudentData();
        for (Student student : studentFromXML) {
            if (student.getIdNumber() == idNumber) {
                return student.toString();
            }
        }
        return "Student("+ idNumber +") not found";
    }
}
