package co.edu.ufps.confudes.certificados;

import java.util.Map;

public class EnglishTranslationDecorator extends CertificateDecorator {
    private static final Map<String, String> TRANSLATIONS = Map.of(
            "Diseño de Software", "Software Design",
            "Patrones de Diseño", "Design Patterns");
    public EnglishTranslationDecorator(CertificateRenderer delegate) { super(delegate); }
    @Override public Certificado render(String estudiante, String curso, String idioma) {
        Certificado c = delegate.render(estudiante, curso, "en");
        return new Certificado(c.estudiante(), TRANSLATIONS.getOrDefault(c.curso(), c.curso()), "en",
                c.codigoQr(), c.marcaAgua());
    }
}
