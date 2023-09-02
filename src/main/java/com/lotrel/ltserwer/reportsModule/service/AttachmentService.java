package com.lotrel.ltserwer.reportsModule.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;

@Service
public class AttachmentService {

    public File getFile(String path) throws FileNotFoundException {
        if (Strings.isBlank(path)) {
           throw new FileNotFoundException("Nie można znaleźć pliku.");
        } else {
            final File file = new File(path);
            if (!file.exists()) throw new FileNotFoundException("Nie można znaleźć pliku.");
            return file;
        }
    }

    public OffsetDateTime getCreationTime(File file) throws IOException {
        try {
            final FileTime fileTime = (FileTime) Files.getAttribute(Path.of(file.getPath()), "creationTime");
            return OffsetDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());
        } catch (IOException e) {
            throw new IOException("Nie można pobrać daty utworzenia pliku.");
        }
    }

}
