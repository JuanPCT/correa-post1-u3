package co.edu.ufps.confudes.certificados;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.junit.jupiter.api.Test;

class StructuralPatternsTest {
    @Test void adapterTranslatesSdkContract() {
        assertEquals("https://example.test/c/42|callback=", new LegacyQrAdapter().crearCodigo("42", "https://example.test/c"));
    }
    @Test void facadeGeneratesCertificateThroughOneEntryPoint() {
        var facade = new GenerateCertificateFacade(new CertificateCollaborators(),
                new CertificatePipeline(new BaseCertificateRenderer(), new LegacyQrAdapter()));
        var certificate = facade.generate("42", "Ana", "Patrones de Diseño", "en");
        assertEquals("Design Patterns", certificate.curso());
        assertEquals("CONFUDES", certificate.marcaAgua());
        assertTrue(certificate.codigoQr().contains("/42"));
    }
    @Test void decoratorsCanBeComposedIndependently() {
        CertificateRenderer renderer = new QrDecorator(
                new WatermarkDecorator(new BaseCertificateRenderer()), "qr:42");
        var certificate = renderer.render("Ana", "Diseño de Software", "es");
        assertEquals("qr:42", certificate.codigoQr());
        assertEquals("CONFUDES", certificate.marcaAgua());
        assertEquals("Diseño de Software", certificate.curso());
    }
    @Test void proxyDeniesBeforeReturningArchiveAndAllowsAuthorizedRole() {
        var proxy = new BulkDownloadProxy(new CertificateArchiveService());
        var student = new TestingAuthenticationToken("student", "pw", List.of(new SimpleGrantedAuthority("ROLE_ESTUDIANTE")));
        var admin = new TestingAuthenticationToken("admin", "pw", List.of(new SimpleGrantedAuthority("ROLE_ADMIN_CERTIFICADOS")));
        assertThrows(SecurityException.class, () -> proxy.download(List.of("1"), student));
        assertEquals("1\n2", new String(proxy.download(List.of("1", "2"), admin),
                java.nio.charset.StandardCharsets.UTF_8));
    }
}
