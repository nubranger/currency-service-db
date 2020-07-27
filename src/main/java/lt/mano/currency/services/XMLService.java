package lt.mano.currency.services;

import lt.mano.currency.model.Rate;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;

@Service
public class XMLService {

    /**
     * Get currency data from xml to list by dates
     */
    public List<Rate> getFxRatesForCurrency(String currency, String fromDate, String toDate) {

        List<Rate> rates = new ArrayList<>();

        try {
            String URL = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/"
                    + "getFxRatesForCurrency"
                    + "?tp=EU"
                    + "&ccy=" + currency
                    + "&dtFrom=" + fromDate
                    + "&dtTo=" + toDate;

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(URL);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("FxRate");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Rate rate = new Rate(
                            element.getElementsByTagName("Dt").item(0).getTextContent().replace('.', '-'),
                            element.getElementsByTagName("Ccy").item(1).getTextContent(),
                            Double.parseDouble(element.getElementsByTagName("Amt").item(1).getTextContent())
                    );
                    rates.add(rate);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return rates;
    }

    /**
     * Get currency data from xml to list
     */
    public List<Rate> getCurrentFxRates() {

        List<Rate> rates = new ArrayList<>();

        try {
            String URL = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/"
                    + "getCurrentFxRates"
                    + "?tp=eu";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(URL);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("FxRate");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Rate rate = new Rate(
                            element.getElementsByTagName("Dt").item(0).getTextContent().replace('.', '-'),
                            element.getElementsByTagName("Ccy").item(1).getTextContent(),
                            Double.parseDouble(element.getElementsByTagName("Amt").item(1).getTextContent())
                    );
                    rates.add(rate);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return rates;
    }

    /**
     * Get currency name list
     */
    public Map<String, String> getCurrencyList() {

        Map<String, String> currencyList = new HashMap<>();

        try {
            String URL = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrencyList?";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(URL);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("CcyNtry");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    String code = elem.getElementsByTagName("Ccy").item(0).getTextContent();
                    String name = elem.getElementsByTagName("CcyNm").item(1).getTextContent();

                    currencyList.put(code, name.toUpperCase());
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return currencyList;
    }
}
