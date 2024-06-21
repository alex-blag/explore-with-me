--

CREATE TABLE IF NOT EXISTS service_apps (
    id      BIGINT          GENERATED ALWAYS AS IDENTITY,
    name    VARCHAR(250)    NOT NULL,

    CONSTRAINT pk_service_app PRIMARY KEY (id),
    CONSTRAINT uq_service_app UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS endpoint_hits (
    id          BIGINT          GENERATED ALWAYS AS IDENTITY,
    app_id      BIGINT          NOT NULL,
    uri         VARCHAR(250)    NOT NULL,
    ip          VARCHAR(15)     NOT NULL,
    time_stamp  TIMESTAMP       NOT NULL,

    CONSTRAINT pk_endpoint_hits PRIMARY KEY (id),
    CONSTRAINT fk_endpoint_hits__service_apps FOREIGN KEY (app_id) REFERENCES service_apps(id) ON DELETE CASCADE
);
