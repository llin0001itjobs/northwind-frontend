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

@SuppressWarnings("unused")
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

    // =============================================================
    // App section
    // =============================================================
    @lombok.Data
    public static class App {
        @NotNull
        private Mail mail = new Mail();

        @NotNull
        private Api api = new Api();

        private Data data = new Data();

        private View view = new View();

        private Integer employeeChunkSize;
        private String imageBasePath;
        private String entitiesFileJson;
        private Boolean entitiesAddListSubpath;
        private List<String> entitiesCustomSqlKeys;

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

            // "package" is a Java keyword → we use "pkg" (you can keep the original
            // property keys in application.properties; Spring Boot relaxed binding
            // still works if you prefer, or rename the keys to app.api.pkg.*)
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
            private String apiUriId;
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
    @lombok.Data
    public static class Server {
        private Servlet servlet = new Servlet();
        private Integer port;

        @lombok.Data
        public static class Servlet {
            private String contextPath;
        }
    }

    // =============================================================
    // Spring section
    // =============================================================
    @lombok.Data
    public static class Spring {
        private Profiles profiles = new Profiles();
        private Datasource datasource = new Datasource();
        private Jpa jpa = new Jpa();
        private Mail mail = new Mail();           // spring.mail
        private Security security = new Security();
        private Thymeleaf thymeleaf = new Thymeleaf();
        private Mvc mvc = new Mvc();
        private Resources resources = new Resources();

        @lombok.Data
        public static class Profiles {
            private String active;
        }

        @lombok.Data
        public static class Datasource {
            private String driverClassName;
            private String url;
            private String username;
            private String password;
        }

        @lombok.Data
        public static class Jpa {
            private Boolean showSql;
            private Hibernate hibernate = new Hibernate();

            @lombok.Data
            public static class Hibernate {
                private String ddlAuto;
                private Map<String, String> properties = new HashMap<>();
            }
        }

        @lombok.Data
        public static class Mail {
            private String host;
            private Integer port;
            private String username;
            private String password;
            private Map<String, String> properties = new HashMap<>();
        }

        @lombok.Data
        public static class Security {
            private Boolean debug;
            private User user = new User();
            private OAuth2 oauth2 = new OAuth2();

            @lombok.Data
            public static class User {
                private String name;
                private String password;
            }

            @lombok.Data
            public static class OAuth2 {
                private Client client = new Client();
                private Provider provider = new Provider();

                @lombok.Data
                public static class Client {
                    private Registration registration = new Registration();

                    @lombok.Data
                    public static class Registration {
                        private Github github = new Github();
                        private Google google = new Google();
                        private Facebook facebook = new Facebook();

                        @lombok.Data
                        public static class Github {
                            private String clientId;
                            private String clientSecret;
                            private List<String> scope;
                            private String authorizationGrantType;
                            private String redirectUri;
                            private String clientName;
                        }

                        @lombok.Data
                        public static class Google {
                            private String clientId;
                            private String clientSecret;
                            private List<String> scope;
                            private String redirectUri;
                        }

                        @lombok.Data
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

                @lombok.Data
                public static class Provider {
                    private Github github = new Github();

                    @lombok.Data
                    public static class Github {
                        private String authorizationUri;
                        private String tokenUri;
                        private String userInfoUri;
                        private String userNameAttribute;
                    }
                }
            }
        }

        @lombok.Data
        public static class Thymeleaf {
            private String prefix;
            private String suffix;
            private String mode;
            private String encoding;
        }

        @lombok.Data
        public static class Mvc {
            private String staticPathPattern;
        }

        @lombok.Data
        public static class Resources {
            private List<String> staticLocations;
        }
    }

    // =============================================================
    // Logging section
    // =============================================================
    @lombok.Data
    public static class Logging {
        private Map<String, String> log4j = new HashMap<>();
        private Map<String, String> level = new HashMap<>();
    }

    // =============================================================
    // Management section
    // =============================================================
    @lombok.Data
    public static class Management {
        private Endpoints endpoints = new Endpoints();
        private Info info = new Info();

        @lombok.Data
        public static class Endpoints {
            private Web web = new Web();

            @lombok.Data
            public static class Web {
                private List<String> include;
            }
        }

        @lombok.Data
        public static class Info {
            private Env env = new Env();

            @lombok.Data
            public static class Env {
                private Boolean enabled;
            }
        }
    }
}