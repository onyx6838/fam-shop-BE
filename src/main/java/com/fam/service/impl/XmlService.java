package com.fam.service.impl;

import com.fam.dto.payment.DistrictDto;
import com.fam.dto.payment.PrecinctDto;
import com.fam.service.IXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author giangdm
 */
@Service
public class XmlService implements IXmlService {
    @Value("${custom.config.provinces.resource}")
    private String xmlProvincesPath;

    @Autowired
    private DocumentBuilderFactory documentBuilderFactory;

    @Override
    public List<DistrictDto> getDistrictFromXML() {
        List<DistrictDto> districtList = new ArrayList<>();
        try {
            NodeList list = getNodeListFromDoc();

            Stream<Node> nodeStream = IntStream.range(0, list.getLength()).mapToObj(list::item);
            nodeStream.filter(node -> ((Element) node).getAttribute("type").equals("district")).forEach(element ->
                    districtList.add(new DistrictDto(
                            Integer.parseInt(((Element) element).getAttribute("id"))
                            , ((Element) element).getAttribute("value")
                    ))
            );

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return districtList;
    }

    @Override
    public List<PrecinctDto> getPrecinctByDistrictIDFromXML(int districtID) {
        List<PrecinctDto> precinctList = new ArrayList<>();

        try {

            NodeList list = getNodeListFromDoc();

            Stream<Node> nodeStream = IntStream.range(0, list.getLength()).mapToObj(list::item);

            NodeList findByDistrictId = nodeStream.filter(node -> ((Element) node).getAttribute("type").equals("district")
                    && Integer.parseInt(((Element) node).getAttribute("id")) == districtID).findFirst().get().getChildNodes();

            Stream<Node> childNodeStream = IntStream.range(0, findByDistrictId.getLength()).mapToObj(findByDistrictId::item);
            childNodeStream.forEach(ax ->
            {
                if (ax.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) ax;
                    precinctList.add(new PrecinctDto(
                            Integer.parseInt(element.getAttribute("id")),
                            element.getAttribute("value"),
                            districtID
                    ));
                }
            });

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return precinctList;
    }

    private NodeList getNodeListFromDoc() throws ParserConfigurationException, SAXException, IOException {
        documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();

        Document doc = db.parse(new File(xmlProvincesPath));
        doc.getDocumentElement().normalize();

        return doc.getElementsByTagName("Item");
    }
}
