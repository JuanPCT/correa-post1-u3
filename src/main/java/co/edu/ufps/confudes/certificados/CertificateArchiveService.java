package co.edu.ufps.confudes.certificados;

import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.springframework.stereotype.Service;

@Service
public class CertificateArchiveService {
    public byte[] zip(List<String> certificateIds) {
        try (var bytes = new ByteArrayOutputStream(); var zip = new ZipOutputStream(bytes, StandardCharsets.UTF_8)) {
            int index = 1;
            for (String id : certificateIds) {
                zip.putNextEntry(new ZipEntry("certificado-" + index++ + ".txt"));
                zip.write(("Identificador del certificado: " + id).getBytes(StandardCharsets.UTF_8));
                zip.closeEntry();
            }
            zip.finish();
            return bytes.toByteArray();
        } catch (IOException exception) {
            throw new UncheckedIOException("No fue posible crear el archivo de certificados", exception);
        }
    }
}
