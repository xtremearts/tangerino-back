package br.com.tangerino.tangerino.service;

import br.com.tangerino.tangerino.configurations.BusinessException;
import br.com.tangerino.tangerino.configurations.FileStorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class ImagensService extends AppService {

    private final Path fileStorageLocation;

    public ImagensService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
    }

    void salvarArquivo(MultipartFile arquivo, String nomeArquivo) throws IOException {

        try {
            Path targetLocation = fileStorageLocation.resolve(nomeArquivo);
            arquivo.transferTo(targetLocation);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public byte[] obterArquivoPorId(String noArquivo) {
        try {
            Path targetLocation = fileStorageLocation.resolve(noArquivo);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(targetLocation.toFile()));

            return resource.getContentAsByteArray();
        } catch (IOException e) {
            throw new BusinessException(e.getMessage());
        }

    }

}
