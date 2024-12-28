package com.example.employee.avro;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.avro.generic.GenericRecord;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;


public class Avro implements Processor {

	@Override
    public void process(Exchange exchange) throws Exception {
       
        GenericRecord record = exchange.getIn().getBody(GenericRecord.class);

        String payload = record.get("payload").toString(); 

        String xmlContent = convertToXml(payload);

        Message message = exchange.getIn();
        message.setBody(xmlContent);
        String filename = generateFilename();
        exchange.getIn().setHeader("CamelFileName", filename);
        System.out.println("processsssing");
    }

    private String convertToXml(String payload) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        System.out.println("xmlwriter");
        return xmlMapper.writeValueAsString(payload);
    }

    private String generateFilename() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        return "payload_" + timestamp;
    }
}
