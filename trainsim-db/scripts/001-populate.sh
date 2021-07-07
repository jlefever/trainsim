#!/bin/bash
set -e

routes=$(curl -s http://localhost:8080/otp/routers/default/index/routes)
stops=$(curl -s http://localhost:8080/otp/routers/default/index/stops)

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CALL otp.load_routes('$routes');
    CALL otp.load_stops('$stops');
EOSQL