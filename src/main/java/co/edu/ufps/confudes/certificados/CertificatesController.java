package co.edu.ufps.confudes.certificados;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certificados")
public class CertificatesController {
    private final GenerateCertificateFacade facade;
    private final BulkDownloadProxy downloadProxy;
    public CertificatesController(GenerateCertificateFacade facade, BulkDownloadProxy downloadProxy) {
        this.facade = facade;
        this.downloadProxy = downloadProxy;
    }
    @PostMapping
    public Certificado create(@RequestBody GenerateRequest request) {
        return facade.generate(request.id(), request.estudiante(), request.curso(), request.idioma());
    }
    @PostMapping("/descarga-masiva")
    @PreAuthorize("hasAuthority('ROLE_ADMIN_CERTIFICADOS')")
    public ResponseEntity<byte[]> download(@RequestBody List<String> ids, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/zip")
                .body(downloadProxy.download(ids, authentication));
    }
    public record GenerateRequest(String id, String estudiante, String curso, String idioma) { }
}
