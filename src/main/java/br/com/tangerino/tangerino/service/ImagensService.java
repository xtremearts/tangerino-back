package br.com.tangerino.tangerino.service;

import br.com.tangerino.tangerino.configurations.FileStorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        if(Objects.equals(arquivo.getContentType(), MediaType.IMAGE_JPEG_VALUE)) nomeArquivo += ".jpg";
        if(Objects.equals(arquivo.getContentType(), MediaType.IMAGE_PNG_VALUE)) nomeArquivo += ".png";

        try {
            Path targetLocation = fileStorageLocation.resolve(nomeArquivo);
            arquivo.transferTo(targetLocation);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
