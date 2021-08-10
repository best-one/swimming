package org.swimming.labs.jsp.configuration;


import org.apache.catalina.Context;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 配置 addLifecycleListener
 * @author zouqinghua
 * @date 2019年10月30日  下午9:45:28
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ConditionalOnProperty(name = "tomcat.staticResourceCustomizer.enabled", matchIfMissing = true)
public class TomcatConfiguration {
	/*
	 * 
	 * SpringBoot 1.x方式配置
	  @return
	@Bean
	public EmbeddedServletContainerCustomizer staticResourceCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				if (container instanceof TomcatEmbeddedServletContainerFactory) {
					((TomcatEmbeddedServletContainerFactory) container)
							.addContextCustomizers(new TomcatContextCustomizer() {
								@Override
								public void customize(Context context) {
									context.addLifecycleListener(new StaticResourceConfigurer(context));
								}
							});
				}
			}

		};
	}
	*/
	
	/**
	 * SpringBoot 2.x方式配置
	 * @return
	 */
	@Bean
	public WebServerFactoryCustomizer<ConfigurableTomcatWebServerFactory> webServerFactoryCustomizerBean(){
		
		return new WebServerFactoryCustomizer<ConfigurableTomcatWebServerFactory>() {

			@Override
			public void customize(ConfigurableTomcatWebServerFactory factory) {
				factory.addContextCustomizers(new TomcatContextCustomizer() {
								@Override
								public void customize(Context context) {
									context.addLifecycleListener(new StaticResourceConfigurer(context));
								}
							});
			}
			
		};
	}
}
