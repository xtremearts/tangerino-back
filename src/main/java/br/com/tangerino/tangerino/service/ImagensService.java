package br.com.tangerino.tangerino.service;

import br.com.tangerino.tangerino.configurations.BusinessException;
import br.com.tangerino.tangerino.configurations.FileStorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Objects;

@Slf4j
@Service
public class ImagensService extends AppService {

    private final Path fileStorageLocation;

    public ImagensService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
    }

    void salvarArquivo(MultipartFile arquivo, String nomeArquivo) throws IOException {

        if (Objects.equals(arquivo.getContentType(), MediaType.IMAGE_JPEG_VALUE)) nomeArquivo += ".jpg";
        if (Objects.equals(arquivo.getContentType(), MediaType.IMAGE_PNG_VALUE)) nomeArquivo += ".png";

        try {
            Path targetLocation = fileStorageLocation.resolve(nomeArquivo);
            arquivo.transferTo(targetLocation);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public byte[] obterArquivoPorId() {
        String nomeArquivo = "24.jpg";
        try {
            Path targetLocation = fileStorageLocation.resolve(nomeArquivo);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(targetLocation.toFile()));
            return Base64.getEncoder().encode(resource.getContentAsByteArray());
//            return resource.getContentAsByteArray();
        } catch (IOException e) {
            throw new BusinessException(e.getMessage());
        }

    }
}
