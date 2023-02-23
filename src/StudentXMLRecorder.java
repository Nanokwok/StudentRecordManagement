import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class StudentXMLRecorder {
    public static Student[] loadStudentData() {

        Student[] studentList = new Student[0];  // Create an empty array of Student objects

        try {  // Create a DocumentBuilderFactory
            DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();  // Create a DocumentBuilder
            DocumentBuilder dBuild = dbFac.newDocumentBuilder();  // Parse the input file to get a Document object
            Document document = dBuild.parse(new File("Studentxml.xml"));  // Get a NodeList of all elements in the document with a given tag name

            NodeList nList = document.getElementsByTagName("student");  // Create an array of Student objects
            studentList = new Student[nList.getLength()];  // Loop through the list of nodes

            for (int i = 0; i < nList.getLength(); i++) {  // Get the next node
                Node node = nList.item(i);  // Check that the node is an Element node
                if (node.getNodeType() == Node.ELEMENT_NODE) {  // Cast the node to an Element
                    Element element = (Element) node;  // Get the value of the <name> element

                    String name, contact;  // Get the value of the <studentID> element
                    int id;  // Get the value of the <contact> element

                    name = element.getElementsByTagName("name").item(0).getTextContent();  // Get the value of the <studentID> element
                    id = Integer.parseInt(element.getElementsByTagName("studentID").item(0).getTextContent());  // Get the value of the <contact> element
                    contact = element.getElementsByTagName("contact").item(0).getTextContent();  // Create a new Student object and add it to the array
                    studentList[i] = new Student(name, id, contact);  // End of loop
                }
            }
        } catch (Exception e) {  // Print out the exception that occurred
            e.printStackTrace();  // End of try block
        }
        return studentList;  // Return the array of Student objects
    }
    public void saveStudentData(Student[] students) {  // Print out the name of the first student in the array
        System.out.println("student" + students[0].getName());  // Get the name of the first student in the array
        String name = students[0].getName();  // Get the contact number of the first student in the array
        String contact = students[0].getContactNumber();  // Get the ID number of the first student in the array
        String studentID = Integer.toString(students[0].getIdNumber());  // Try block to catch any exceptions that may occur

        try {  // Create a File object for the input file

            File xmlFile = new File("Studentxml.xml");  // Create a DocumentBuilderFactory

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();  // Create a DocumentBuilder
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  // Parse the input file to get a Document object
            Document doc = dBuilder.parse(xmlFile);  // Get the root element

            Element root = doc.getDocumentElement();  // Create a new <student> element

            Element newStudent = doc.createElement("student");  // Create a new <name> element and add it to the <student> element

            Element nameNode = doc.createElement("name");  // Set the text content of the <name> element
            nameNode.setTextContent(name);  // Add the <name> element to the <student> element

            Element idNode = doc.createElement("studentID");  // Set the text content of the <studentID> element
            idNode.setTextContent(studentID);  // Add the <studentID> element to the <student> element

            Element contactNode = doc.createElement("contact");  // Set the text content of the <contact> element
            contactNode.setTextContent(contact);  // Add the <contact> element to the <student> element

            newStudent.appendChild(nameNode);  // Add the <student> element to the root element
            newStudent.appendChild(idNode);  // Add the <student> element to the root element
            newStudent.appendChild(contactNode);  // Add the <student> element to the root element

            root.appendChild(newStudent);  // Create a TransformerFactory object

            TransformerFactory transformerFactory = TransformerFactory.newInstance();  // Create a Transformer object
            Transformer transformer = transformerFactory.newTransformer();  // Set the output properties of the Transformer object
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");  // Create a DOMSource object

            DOMSource source = new DOMSource(doc);  // Create a StreamResult object
            StreamResult result = new StreamResult(xmlFile);  // Transform the DOM tree into an XML file
            transformer.transform(source, result);  // End of try block

            System.out.println("saved student data");  // End of try block
        } catch (Exception e) {  // Print out the exception that occurred
            e.printStackTrace();  // End of try block
        }
    }

    public void deleteStudentData(Student student) {  // Print out the name of the first student in the array
        try {  // Create a File object for the input file
            File xmlFile = new File("Studentxml.xml");  // Create a DocumentBuilderFactory

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();  // Create a DocumentBuilder
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  // Parse the input file to get a Document object
            Document doc = dBuilder.parse(xmlFile);  // Get the root element

            Element root = doc.getDocumentElement();  // Create a new <student> element

            NodeList nList = doc.getElementsByTagName("student");  // Loop through the list of nodes
            for (int i = 0; i < nList.getLength(); i++) {  // Get the next node
                Node node = nList.item(i);  // Check that the node is an Element node
                if (node.getNodeType() == Node.ELEMENT_NODE) {  // Cast the node to an Element
                    Element element = (Element) node;  // Get the value of the <name> element
                    String name = element.getElementsByTagName("name").item(0).getTextContent();  // Get the value of the <studentID> element
                    if (name.equals(student.getName())) {  // Get the value of the <contact> element
                        root.removeChild(element);  // Create a TransformerFactory object
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();  // Create a Transformer object
            Transformer transformer = transformerFactory.newTransformer();  // Set the output properties of the Transformer object
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");  // Create a DOMSource object

            DOMSource source = new DOMSource(doc);  // Create a StreamResult object
            StreamResult result = new StreamResult(xmlFile);  // Transform the DOM tree into an XML file
            transformer.transform(source, result);  // End of try block

            System.out.println("Deleted student " + student.getName());  // End of try block
        } catch (Exception e) {  // Print out the exception that occurred
            e.printStackTrace();  // End of try block
        }
    }
}
