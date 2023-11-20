package br.com.tangerino.tangerino;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class TangerinoApplication {

    private static final String SERVER_PORT = "server.port";

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(TangerinoApplication.class, args);

        Environment env = ctx.getEnvironment();

        log.info("\n\n *** \n\n" + "\tAplicacao iniciada com sucesso!\n" + "\tDisponivel no endereco:\n"
                        + "\tLocal: http://localhost:{}\n",
                env.getProperty(SERVER_PORT));
    }

}
