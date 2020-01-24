rootProject.name = "microservices"

include(
    "dal:api",
    "dal:core",
    "dal:web",
    "backus:api",
    "backus:core",
    "web-ui",
    "web"
)

project(":backus:api").name = "backus-api"
