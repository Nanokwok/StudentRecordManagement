import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class AdminXMLRecorder {
    public static Admin[] loadAdminData() {  // load data from xml file

        Admin[] adminList = new Admin[0];  // create an empty array of Admin

        try {  // try to load data from xml file
            DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();  // create a new instance of DocumentBuilderFactory
            DocumentBuilder dBuild = dbFac.newDocumentBuilder();  // create a new instance of DocumentBuilder
            Document document = dBuild.parse(new File("Adminxml.xml"));  // create a new instance of Document
            NodeList nList = document.getElementsByTagName("admin");  // create a new instance of NodeList
            adminList = new Admin[nList.getLength()];  // create an array of Admin with the length of nList

            for (int i = 0; i < nList.getLength(); i++) {  // loop through the nList
                Node node = nList.item(i);  // create a new instance of Node
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    int password = Integer.parseInt(element.getElementsByTagName("password").item(0).getTextContent());
                    adminList[i] = new Admin(name, password);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminList;
    }

    public void saveAdminData(Admin[] admins) {
        String name = admins[0].getName();
        String adminPass = Integer.toString(admins[0].getPassword());
        try {
            File xmlFile = new File("Adminxml.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            Element root = doc.getDocumentElement();
            Element newStudent = doc.createElement("admin");
            Element nameNode = doc.createElement("name");
            nameNode.setTextContent(name);
            Element password = doc.createElement("password");
            password.setTextContent(adminPass);
            newStudent.appendChild(nameNode);
            newStudent.appendChild(password);
            root.appendChild(newStudent);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAdminData(String name) {
        try {
            File xmlFile = new File("Adminxml.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            Element root = doc.getDocumentElement();
            NodeList nList = doc.getElementsByTagName("admin");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name1 = element.getElementsByTagName("name").item(0).getTextContent();
                    if (name1.equals(name)) {
                        root.removeChild(node);
                    }
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}