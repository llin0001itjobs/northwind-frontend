package org.llin.demo.northwind.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ConfigurationProperties(prefix = "")
@Validated
@Getter
@Setter
@NoArgsConstructor
public class PropertyDefaultProperties {

	@NotNull
	private App app = new App();

	@NotNull
	private Server server = new Server();

	@NotNull
	private Spring spring = new Spring();

	private Logging logging = new Logging();

	private Management management = new Management();

	private Github github = new Github();

	// =============================================================
	// App section
	// =============================================================
	@Data
	public static class App {
		@NotNull
		private Api api = new Api();

		private Data data = new Data();

		@NotNull
		private Mail mail = new Mail();

		private Security security = new Security();
				
		private View view = new View();

		private Integer employeeChunkSize;
		private String imageBasePath;

		@lombok.Data
		public static class Security {
			private String rememberMeSecret;						
		}
		
		@lombok.Data
		public static class Mail {
			private Subject subject = new Subject();
			private Text text = new Text();

			@lombok.Data
			public static class Subject {
				private String verified;
				private String registered;
			}

			@lombok.Data
			public static class Text {
				private String verified;
				private String registered;
			}
		}

		@lombok.Data
		public static class Api {
			private Dogs dogs = new Dogs();

			private Pkg pkg = new Pkg();

			private Boolean usage;

			@lombok.Data
			public static class Dogs {
				private Images images = new Images();

				@lombok.Data
				public static class Images {
					private String random;
				}
			}

			@lombok.Data
			public static class Pkg {
				private String northwind;
				private List<String> excluded;
				private List<Integer> sample;
			}
		}

		@lombok.Data
		public static class Data {
			private String apiUri;
			private String regexApiUri;
		}

		@lombok.Data
		public static class View {
			private Ellipsis ellipsis = new Ellipsis();

			@lombok.Data
			public static class Ellipsis {
				private Integer limit;
			}
		}
	}

	// =============================================================
	// Server section
	// =============================================================
	@Data
	public static class Server {
		private Servlet servlet = new Servlet();
		private Integer port;

		@Data
		public static class Servlet {
			private String contextPath;
		}
	}

	// =============================================================
	// Spring section (now includes spring.main from test profile)
	// =============================================================
	@Data
	public static class Spring {
		private Profiles profiles = new Profiles();
		private Main main = new Main(); // spring.main.allow-bean-definition-overriding
		private Mail mail = new Mail(); // spring.mail
		private Security security = new Security();
		private Thymeleaf thymeleaf = new Thymeleaf();
		private Mvc mvc = new Mvc();
		private Resources resources = new Resources();
		private Cloud cloud = new Cloud();
				
		@Data
		public static class Profiles {
			private String active;
		}

		@Data
		public static class Main {
			private Boolean allowBeanDefinitionOverriding;
		}

		@Data
		public static class Mail {
			private String host;
			private Integer port;
			private String username;
			private String password;
			private Map<String, String> properties = new HashMap<>();
		}

		@Data
		public static class Security {
			private Boolean debug;
			private User user = new User();
			private OAuth2 oauth2 = new OAuth2();

			@Data
			public static class User {
				private String name;
				private String password;
			}

			@Data
			public static class OAuth2 {
				private Client client = new Client();
				private Provider provider = new Provider();

				@Data
				public static class Client {
					private Registration registration = new Registration();

					@Data
					public static class Registration {
						private Github github = new Github();
						private Google google = new Google();
						private Facebook facebook = new Facebook();

						@Data
						public static class Github {
							private String clientId;
							private String clientSecret;
							private List<String> scope;
							private String authorizationGrantType;
							private String redirectUri;
							private String clientName;
						}

						@Data
						public static class Google {
							private String clientId;
							private String clientSecret;
							private List<String> scope;
							private String redirectUri;
						}

						@Data
						public static class Facebook {
							private String clientId;
							private String clientSecret;
							private List<String> scope;
							private String redirectUri;
							private String authorizationGrantType;
							private String clientName;
						}
					}
				}

				@Data
				public static class Provider {
					private Github github = new Github();

					@Data
					public static class Github {
						private String authorizationUri;
						private String tokenUri;
						private String userInfoUri;
						private String userNameAttribute;
					}
				}
			}
		}

		@Data
		public static class Thymeleaf {
			private String prefix;
			private String suffix;
			private String mode;
			private String encoding;
		}

		@Data
		public static class Mvc {
			private String staticPathPattern;
		}

		@Data
		public static class Resources {
			private List<String> staticLocations;
		}

		@Data
		public static class Cloud {
			private Azure azure = new Azure();

			@Data
			public static class Azure {

				private Keyvault keyvault = new Keyvault();

				@Data
				public static class Keyvault {
					private Secret secret = new Secret();

					@Data
					public static class Secret {
						private Boolean enabled;
						private String propertySourceEnabled;
					}
				}

			}

		}
		
	}

	// =============================================================
	// Logging section (fully supports logging.level.*, logging.pattern.console,
	// etc.)
	// =============================================================
	@Data
	public static class Logging {
		private Map<String, String> log4j = new HashMap<>();
		private Map<String, String> level = new HashMap<>();

		// Supports: logging.pattern.console=...
		private Pattern pattern = new Pattern();

		@Data
		public static class Pattern {
			private String console;
		}
	}

	// =============================================================
	// Management section (fully supports management.endpoints.web.exposure.include,
	// etc.)
	// =============================================================
	@Data
	public static class Management {
		private Endpoints endpoints = new Endpoints();		
		private Health health = new Health();
		private Info info = new Info();

		@Data
		public static class Endpoints {
			private Web web = new Web();

			@Data
			public static class Web {
				private Exposure exposure = new Exposure();

				@Data
				public static class Exposure {
					private List<String> include;
				}
			}
		}

		@Data
		public static class Health {
			private Mail mail = new Mail();

			@Data
			public static class Mail {
				private Boolean enabled;
			}
		}
		
		@Data
		public static class Info {
			private Env env = new Env();

			@Data
			public static class Env {
				private Boolean enabled;
			}
		}
	}

	@Data
	public static class Github {
		private String clientId;
		private String clientSecret;
	}
}