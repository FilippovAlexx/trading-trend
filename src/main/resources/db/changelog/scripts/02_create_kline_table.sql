CREATE TABLE kline (
    id BIGSERIAL primary key,
    timestamp BIGINT,
    open DOUBLE PRECISION,
    high DOUBLE PRECISION,
    low DOUBLE PRECISION,
    close DOUBLE PRECISION,
    coin_id BIGINT REFERENCES coin(id)
);