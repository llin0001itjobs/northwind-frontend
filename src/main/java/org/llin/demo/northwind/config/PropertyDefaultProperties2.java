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

@ConfigurationProperties(prefix = "app")
@Validated
@Getter
@Setter
@NoArgsConstructor
public class PropertyDefaultProperties2 {

	@NotNull
	private App app = new App();

	@NotNull
	private Server server = new Server();

	@NotNull
	private Spring spring = new Spring();

	private Logging logging = new Logging();

	private Management management = new Management();

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
		
		private View view = new View();

		private Integer employeeChunkSize;
		private String imageBasePath;		

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
	// Spring section
	// =============================================================
	@Data
	public static class Spring {
		private Profiles profiles = new Profiles();
		private Datasource datasource = new Datasource();
		private Jpa jpa = new Jpa();
		private Mail mail = new Mail(); // spring.mail
		private Security security = new Security();
		private Thymeleaf thymeleaf = new Thymeleaf();
		private Mvc mvc = new Mvc();
		private Resources resources = new Resources();

		@Data
		public static class Profiles {
			private String active;
		}

		@Data
		public static class Datasource {
			private String driverClassName;
			private String url;
			private String username;
			private String password;
		}

		@Data
		public static class Jpa {
			private Boolean showSql;
			private Hibernate hibernate = new Hibernate();

			@Data
			public static class Hibernate {
				private String ddlAuto;
				private Map<String, String> properties = new HashMap<>();
			}
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
	}

	// =============================================================
	// Logging section (now fully supports your application.properties)
	// =============================================================
	@Data
	public static class Logging {
		private Map<String, String> log4j = new HashMap<>();
		private Map<String, String> level = new HashMap<>();

		// Added to support: logging.pattern.console=...
		private Pattern pattern = new Pattern();

		@Data
		public static class Pattern {
			private String console;
		}
	}

	// =============================================================
	// Management section (now fully supports your application.properties)
	// =============================================================
	@Data
	public static class Management {
		private Endpoints endpoints = new Endpoints();
		private Info info = new Info();

		@Data
		public static class Endpoints {
			private Web web = new Web();

			@Data
			public static class Web {
				// Updated to match the real Spring Boot property path:
				// management.endpoints.web.exposure.include=...
				private Exposure exposure = new Exposure();

				@Data
				public static class Exposure {
					private List<String> include;
				}
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
}