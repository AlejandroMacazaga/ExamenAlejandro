package com.example.examenalejandro;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser {
    private URL url;

    public XMLParser(String url) {
        try {
            this.url = new URL(url);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public Tiempo[] read() {
        // Instanciamos el fabricador de DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Tiempo[] listaT = new Tiempo[3];
        Tiempo tHoy = null;
        Tiempo tMañana = null;
        Tiempo tPasado = null;
        try {
            // Creamos el parser
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Leemos todo el XML
            Document d = builder.parse(this.getInputStream());
            Element root = d.getDocumentElement();
            NodeList lista = root.getChildNodes();

            // recorremos la lista
            for(int i = 0; i < lista.getLength(); i++) {
                // obtenemos el dia actual
                Node dia = lista.item(i);
                String nombreDia = dia.getNodeName();
                if(nombreDia.equals("day1")) {
                    NodeList datosDia = dia.getChildNodes();
                    String date = "";
                    String maxima = "0";
                    String minima = "0";
                    String descripcion = "";
                    for(int e = 0; e < datosDia.getLength(); e++) {
                        Node dato = datosDia.item(i);
                        if(dato.getNodeName().equals("date")) {
                            date = dato.getTextContent();
                        }
                        if(dato.getNodeName().equals("temperature_max")) {
                            maxima = dato.getTextContent();
                        }
                        if(dato.getNodeName().equals("temperature_min")) {
                            minima = dato.getTextContent();
                        }
                        if(dato.getNodeName().equals("text")) {
                            descripcion = dato.getTextContent();
                        }

                    }
                    tHoy = new Tiempo(date, Integer.parseInt(minima), Integer.parseInt(maxima), descripcion);

                }
                if(nombreDia.equals("day2")) {
                    NodeList datosDia = dia.getChildNodes();
                    String date = "";
                    String maxima = "0";
                    String minima = "0";
                    String descripcion = "";
                    for(int e = 0; e < datosDia.getLength(); e++) {
                        Node dato = datosDia.item(i);
                        if(dato.getNodeName().equals("date")) {
                            date = dato.getTextContent();
                        }
                        if(dato.getNodeName().equals("temperature_max")) {
                            maxima = dato.getTextContent();
                        }
                        if(dato.getNodeName().equals("temperature_min")) {
                            minima = dato.getTextContent();
                        }
                        if(dato.getNodeName().equals("text")) {
                            descripcion = dato.getTextContent();
                        }

                    }
                    tMañana = new Tiempo(date, Integer.parseInt(minima), Integer.parseInt(maxima), descripcion);
                }
                if(nombreDia.equals("day3")) {
                    NodeList datosDia = dia.getChildNodes();
                    String date = "";
                    String maxima = "0";
                    String minima = "0";
                    String descripcion = "";
                    for(int e = 0; e < datosDia.getLength(); e++) {
                        Node dato = datosDia.item(i);
                        if(dato.getNodeName().equals("date")) {
                            date = dato.getTextContent();
                        }
                        if(dato.getNodeName().equals("temperature_max")) {
                            maxima = dato.getTextContent();
                        }
                        if(dato.getNodeName().equals("temperature_min")) {
                            minima = dato.getTextContent();
                        }
                        if(dato.getNodeName().equals("text")) {
                            descripcion = dato.getTextContent();
                        }

                    }
                    tPasado = new Tiempo(date, Integer.parseInt(minima), Integer.parseInt(maxima), descripcion);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        listaT[0] = tHoy;
        listaT[1] = tMañana;
        listaT[2] = tPasado;
        return listaT;
    }

    private InputStream getInputStream() {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
