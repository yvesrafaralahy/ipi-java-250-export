package com.example.demo.service.export;

import com.example.demo.dto.ClientDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class ExportCSVService {

    public void export(Writer printWriter, List<ClientDTO> clients) throws IOException {
        ExporterCSV exporter = new ExporterCSV();
        exporter.addColumnString("Nom", clientDTO -> clientDTO.getNom());
        exporter.addColumnString("Prenom", ClientDTO::getPrenom);
        exporter.addColumnInteger("Age", ClientDTO::getAge);
        exporter.createCSV(printWriter, clients);
    }

    public void exportOld(Writer printWriter, List<ClientDTO> clients) throws IOException {
        printWriter.write("Nom;");
        printWriter.write("Prenom;");
        printWriter.write("Age;");

        for (ClientDTO client : clients) {

            printWriter.write(replace(client.getNom()));
            printWriter.write(";");
            printWriter.write(replace(client.getPrenom()));
            printWriter.write(";");
            printWriter.write(client.getAge());

            printWriter.write("\n");
        }
    }

    private String replace(String value) {
        value = value.replace("\"", "\"\"");
        if (value.contains(";")) {
            value = "\"" + value + "\"";
        }
        return value;
    }
}
