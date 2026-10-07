package co.edu.ufps.confudes.certificados;

import org.springframework.stereotype.Service;

/** Facade: unifica validación, política, QR y renderizado tras un único método de aplicación. */
@Service
public class GenerateCertificateFacade {
    private final CertificateCollaborators collaborators;
    private final CertificatePipeline pipeline;
    public GenerateCertificateFacade(CertificateCollaborators collaborators, CertificatePipeline pipeline) {
        this.collaborators = collaborators;
        this.pipeline = pipeline;
    }
    public Certificado generate(String id, String student, String course, String language) {
        if (id == null || id.isBlank() || student == null || student.isBlank() || course == null || course.isBlank()) {
            throw new IllegalArgumentException("Id, estudiante y curso son obligatorios");
        }
        CertificateCollaborators.CertificatePolicy policy = collaborators.policy();
        if (policy.maximumPageSize() <= 0) throw new IllegalStateException("Política inválida");
        return pipeline.build(id, student, course, language == null ? "es" : language,
                true, true, "en".equalsIgnoreCase(language));
    }
}
