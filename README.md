# Certificados ConfUDES · Unidad 3

Aplicación Spring Boot que genera certificados combinables y protege su descarga masiva. Java 17, Spring Boot 3.5.16.

## Ejecutar

```bash
mvn spring-boot:run
mvn test
```

`POST /api/certificados` recibe `{"id":"42","estudiante":"Ana","curso":"Patrones de Diseño","idioma":"en"}`. `POST /api/certificados/descarga-masiva` recibe una lista de identificadores y requiere el rol autenticado `ADMIN_CERTIFICADOS`; el control se aplica en el endpoint y en el Proxy antes de generar el archivo. Para la demo local, los usuarios HTTP Basic son `cert-admin` y `student`; se pueden cambiar los valores de demostración con `APP_ADMIN_PASSWORD` y `APP_STUDENT_PASSWORD`.

## Decisiones de diseño

| Necesidad | Patrón | Participantes | Alternativa descartada |
|---|---|---|---|
| SDK QR con contrato incompatible | Adapter | `QrVerifier`, `LegacyQrAdapter`, `LegacyQrSdk` | Cambiar el SDK externo no está bajo control de ConfUDES; propagar su mapa por todo el dominio contamina los clientes. |
| Coordinar política y generación | Facade | `GenerateCertificateFacade`, política, pipeline | Hacer que el Controller invoque cuatro colaboradores expone detalles y acopla el transporte HTTP al flujo de aplicación. |
| Marca, QR y traducción combinables | Decorator | `CertificateRenderer` y decoradores | Subclases por combinación multiplicarían tipos; una opción booleana fija impediría añadir capacidades de forma independiente. |
| Restringir descargas masivas | Proxy | `BulkDownloadProxy`, `CertificateArchiveService` | La autorización en el cliente o Controller se puede omitir desde otro consumidor; el proxy protege el acceso al servicio costoso. |

El Decorator varía capacidades de un certificado; el Proxy controla el acceso a un servicio; el Facade simplifica una operación coordinada; el Adapter convierte un contrato ajeno. La descarga de ejemplo genera un ZIP con un manifiesto por certificado, para concentrar la entrega en el control de acceso y el ensamblado. Un Composite tendría sentido para árboles de certificados agrupados, pero no se requiere jerarquía de elementos aquí. Flyweight podría reducir memoria si se almacenaran masivamente estilos compartidos; esta aplicación no mantiene esa carga.

## Verificación

`StructuralPatternsTest` cubre traducción del SDK, orquestación, composición independiente y autorización. El repositorio queda listo para ejecutar con `mvn test` en un entorno que pueda descargar las dependencias Maven.
