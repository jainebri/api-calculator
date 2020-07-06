package es.sanitas.test4.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	/**
	 * 
	 * @return
	 */
	@Bean
	public Docket calculatorPIVersion1() {
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.regex("/api/v1/.*"))
					.build().apiInfo(
								calculatorAPIInfo())
					.useDefaultResponseMessages(false)
					.groupName("api-calculator");
	}

	/**
	 * 
	 * @return
	 */
	private ApiInfo calculatorAPIInfo() {
		return new ApiInfoBuilder().title("API Calculator")
					.description("API que contiene la funcionalidad necesaria para soportar la ejecución de operaciones aritméticas. "
								+ "Es parte de una prueba técnica para el proyecto de Sanitas.")
					.version("1.0").build();

	}
}
