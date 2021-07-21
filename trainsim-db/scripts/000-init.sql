CREATE SCHEMA otp;

CREATE TABLE otp.stops (
    id SERIAL PRIMARY KEY,
    otp_id VARCHAR(64) UNIQUE NOT NULL,
    name TEXT NOT NULL
);

CREATE TABLE otp.routes (
    id SERIAL PRIMARY KEY,
    otp_id VARCHAR(64) UNIQUE NOT NULL,
    name TEXT NOT NULL,
    short_name TEXT NOT NULL,
    mode VARCHAR(64) NOT NULL,
    color CHAR(6)
);

CREATE TABLE otp.itineraries (
    id SERIAL PRIMARY KEY
);

CREATE TABLE otp.legs (
    id SERIAL PRIMARY KEY,
    itinerary_id INT REFERENCES otp.itineraries (id) NOT NULL,
    -- route_id will be null if this is a walking leg.
    route_id INT REFERENCES otp.routes (id),
    sort INT NOT NULL,
    distance DOUBLE PRECISION NOT NULL
);

CREATE TABLE otp.places (
    id SERIAL PRIMARY KEY,
    leg_id INT REFERENCES otp.places (id) NOT NULL,
    stop_id INT REFERENCES otp.stops (id) NOT NULL,
    sort INT NOT NULL,
    arrive_at TIMESTAMP WITH TIME ZONE NOT NULL,
    depart_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TYPE otp.api_stop AS (id TEXT, name TEXT);

CREATE PROCEDURE otp.load_stops(otp_data json)
LANGUAGE SQL
AS $$
INSERT INTO otp.stops (otp_id, name)
SELECT
    id AS otp_id,
    name
FROM json_populate_recordset(null::otp.api_stop, otp_data);
$$;

CREATE TYPE otp.api_route_short AS (
    id TEXT,
    "longName" TEXT,
    "shortName" TEXT,
    mode TEXT,
    color TEXT
);

CREATE PROCEDURE otp.load_routes(otp_data json)
LANGUAGE SQL
AS $$
INSERT INTO otp.routes (otp_id, name, short_name, mode, color)
SELECT
    id AS otp_id,
    "longName" AS name,
    "shortName" AS short_name,
    mode,
    color
FROM json_populate_recordset(null::otp.api_route_short, otp_data);
$$;

CALL otp.load_routes('[
    {
        "id": "1:1",
        "shortName": "1",
        "longName": "Parx Casino to 54th-City",
        "mode": "BUS",
        "agencyName": "SEPTA"
    },
    {
        "id": "1:124",
        "shortName": "124",
        "longName": "Chesterbrook to 13th & Market",
        "mode": "BUS",
        "agencyName": "SEPTA"
    },
    {
        "id": "1:3",
        "shortName": "3",
        "longName": "33rd-Cecil B. Moore to FTC",
        "mode": "BUS",
        "agencyName": "SEPTA"
    },
    {
        "id": "1:2",
        "shortName": "2",
        "longName": "20-Johnston to Pulaski-Hntg Park",
        "mode": "BUS",
        "agencyName": "SEPTA"
    }
]');

-- CALL otp.load_stops('[
--     {
--         "id": "1:2317",
--         "name": "Lancaster Ave & Malin Rd - MBNS",
--         "lat": 40.040428,
--         "lon": -75.541873
--     },
--     {
--         "id": "1:2315",
--         "name": "Springdale Dr & Beldon Blvd - FS",
--         "lat": 40.029261,
--         "lon": -75.605645
--     },
--     {
--         "id": "1:2",
--         "name": "Ridge Av & Wissahickon Transfer Center",
--         "lat": 40.014986,
--         "lon": -75.206826,
--         "stationId": "1:31032",
--         "cluster": "31032"
--     },
--     {
--         "id": "1:2312",
--         "name": "Pottstown Pk & Sunrise Blvd",
--         "lat": 40.038272,
--         "lon": -75.632269
--     },
--     {
--         "id": "1:5",
--         "name": "Roosevelt Blvd & 5th St",
--         "lat": 40.023465,
--         "lon": -75.133487
--     }
-- ]');