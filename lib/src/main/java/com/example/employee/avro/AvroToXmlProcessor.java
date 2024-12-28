package com.example.employee.avro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class AvroToXmlProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // Paths for Avro schema and Avro file
        String avroFile = "D:/Naren_old_laptop/Spring-Project/Employee/src/main/java/com/example/Employee/avrooo/TestAvro 3.avro";
        String avroSchemaFile = "D:/Naren_old_laptop/Spring-Project/Employee/src/main/java/com/example/Employee/avrooo/avroschema.avsc";

        // Read Avro schema
        Schema schema = new Schema.Parser().parse(new File(avroSchemaFile));

        // Read Avro data
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(new File(avroFile), datumReader);

        int fileCount = 0;
        // Convert Avro records to XML elements
        while (dataFileReader.hasNext()) {
            GenericRecord record = dataFileReader.next();
            String xmlPayload = record.get("payload").toString();  // Assuming payload is the key

            // Build XML from Avro data
            String outputFileName = "D:/Naren_old_laptop/Spring-Project/Employee/src/main/java/com/example/Employee/avrooo/Final" + fileCount + ".xml";
            writeToFile(outputFileName, xmlPayload);

            fileCount++;
        }
    }

    private void writeToFile(String filePath, String xml) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(xml);
            writer.newLine();
        }
    }
}
