import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.LinkedList;


public class AdminRecordManagement {
    private LinkedList<Admin> adminList = new LinkedList<Admin>();  // create a linked list of admin

    public AdminRecordManagement() throws ParserConfigurationException, TransformerException {  // constructor
        File file = new File("Adminxml.xml");  // create a file
        if (!file.exists()) {  // if the file does not exist
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();  // create a document factory
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();  // create a document builder
            Document doc = docBuilder.newDocument();  // create a document
            Element rootElement = doc.createElement("adminList");  // create a root element
            doc.appendChild(rootElement);  // append the root element to the document
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Adminxml.xml"));

            transformer.transform(source, result);
        }
    }

    public void add(String name, int password) {  // add a new admin
        Admin admin = new Admin(name, password);  // create a new admin
        adminList.add(admin);  // add the new admin to the linked list

        Admin[] admins = {admin};  // create an array of admin
        AdminXMLRecorder adminXMLRecorder = new AdminXMLRecorder();  // create a new admin xml recorder
        adminXMLRecorder.saveAdminData(admins);  // save the new admin to the xml file
    }

    public void delete(String name) {  // delete an admin
        adminList.removeIf(admin -> admin.getName().equals(name));  // remove the admin from the linked list
        AdminXMLRecorder adminXMLRecorder = new AdminXMLRecorder();  // create a new admin xml recorder
        adminXMLRecorder.deleteAdminData(name);  // delete the admin from the xml file
    }

    public boolean verifyAdmin(String name, int pass) {  // verify the admin
        Admin[] adminArr = AdminXMLRecorder.loadAdminData();  // load the admin data from the xml file
        boolean found = false;  // set the boolean to false
        for (Admin admin : adminArr) {  // loop through the admin array
            if (admin.getName().equals(name) && admin.getPassword() == pass) {  // if the admin name and password match
//                System.out.println("Admin Verified");  // print out the message
                found = true;  // set the boolean to true

                return found;  // return the boolean
            }
        }
        // if the admin name and password do not match
//        System.out.println("Admin Not Verified");
        return found;  // return the boolean
    }

    public Admin[] getAdminList() {  // get the admin list
        Admin[] adminArr = AdminXMLRecorder.loadAdminData();  // load the admin data from the xml file
        return adminArr;  // return the admin array
        }

    public String findpassbyname(String name) {  // find the password by name
        Admin[] adminArr = AdminXMLRecorder.loadAdminData();  // load the admin data from the xml file
        for (Admin admin : adminArr) {  // loop through the admin array
            if (admin.getName().equals(name)) {  // if the admin name matches
                return "Name: " + name + "  Password: " + admin.getPassword();  // return the name and password
            }
        }
        // if the admin name does not match
        return "Admin Not Found";
    }
    public boolean checkonlyname(String name) {  // check that have this name in xml
        Admin[] adminArr = AdminXMLRecorder.loadAdminData();  // load the admin data from the xml file
        boolean found = false;  // set the boolean to false
        for (Admin admin : adminArr) {  // loop through the admin array
            if (admin.getName().equals(name)) {  // if the admin name matches
                found = true;  // set the boolean to true
                return found;  // return the boolean
            }
        }
        // if the admin name does not match
        return found;
    }
}



